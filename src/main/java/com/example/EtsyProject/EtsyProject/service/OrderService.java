package com.example.EtsyProject.EtsyProject.service;

import com.example.EtsyProject.EtsyProject.dao.CartRepository;
import com.example.EtsyProject.EtsyProject.dao.OrderDetailsRepository;
import com.example.EtsyProject.EtsyProject.dao.OrderRepository;
import com.example.EtsyProject.EtsyProject.dao.ProductRepository;
import com.example.EtsyProject.EtsyProject.entity.OrderDetails;
import com.example.EtsyProject.EtsyProject.entity.Orders;
import com.example.EtsyProject.EtsyProject.entity.Role;
import com.example.EtsyProject.EtsyProject.entity.User;
import com.example.EtsyProject.EtsyProject.service.Response.OrderResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderDetailsRepository orderDetailsRepository;
    private ProductRepository productRepository;
    private CartRepository cartRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository,
                        OrderDetailsRepository orderDetailsRepository,
                        ProductRepository productRepository,
                        CartRepository cartRepository){
        this .orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public String saveOrder(Orders orders) throws Exception{
        // setting the local order time
        orders.setOrderDate(LocalDateTime.now());
        // creating the new object with local date
        Orders order =  new Orders(orders.getEmail(),orders.getOrderDate(), orders.getTotalPrice(),orders.getOrderDetailsList());

        //saving the object into orders table
        Orders saveOrder = orderRepository.save(order);

        // getting the orderslist from the orders request
        List<OrderDetails> orderDetailsList = orders.getOrderDetailsList();


        for (OrderDetails orderDetails : orderDetailsList) {

            // we are saving the saved order in the orderdetails object
            orderDetails.setOrderId(saveOrder);

            // we are saving the orderdetails info
            orderDetailsRepository.save(orderDetails);
//            Integer salesCount = orderDetailsRepository.getSalesCount(orderDetails.getProductId());
            try {
                productRepository.updateProductStockAndSalesCount(orderDetails.getQuantity(),
                        orderDetails.getProducts().getProductId());
            }
            catch (Exception e){
                throw new EntityNotFoundException("some issue in updating the products stock and sales count");
            }
        }
        try {
            cartRepository.deleteByEmailId(orders.getEmail());
        }
        catch (Exception e){
            throw new EntityNotFoundException("some issue in deleting the cart details");
        }
        return "ordered successfully";
    }

    public List<Map<String, Object>> getOrders(String email){
        List<OrderResponse> result = orderRepository.findByEmailWithDetails(email);
        List<Map<String, Object>> orders = new ArrayList<>();
        for (OrderResponse row : result) {
            int orderId = row.getOrderId();
            LocalDateTime orderDate = row.getOrderDate();
            double totalPrice = row.getTotalPrice();
            int orderDetailsId = row.getOrderDetailsId();
            int quantity = row.getQuantity();
            double price = row.getPrice();
            int productId = row.getProductId();
            String productName = row.getProductName();
            String currency = row.getCurrency();
            String shopName = row.getShopName();
            String imageUrl = row.getImageUrl();

            Map<String, Object> product = new HashMap<>();
            product.put("productId", productId);
            product.put("productName", productName);
            product.put("currency", currency);
            product.put("shopName", shopName);
            product.put("imageUrl", imageUrl);

            Map<String, Object> orderDetails = new HashMap<>();
            orderDetails.put("orderDetailsId", orderDetailsId);
            orderDetails.put("products", product);
            orderDetails.put("quantity", quantity);
            orderDetails.put("totalPrice", totalPrice);
            orderDetails.put("price", price);

            boolean orderFound = false;
            for (Map<String, Object> order : orders) {
                if ((int) order.get("orderId") == orderId) {
                    orderFound = true;
                    List<Map<String, Object>> orderDetailsList = (List<Map<String, Object>>) order.get("orderDetailsList");
                    orderDetailsList.add(orderDetails);
                    break;
                }
            }
            if (!orderFound) {
                Map<String, Object> order = new HashMap<>();
                order.put("orderId", orderId);
                order.put("orderDate", orderDate);
                List<Map<String, Object>> orderDetailsList = new ArrayList<>();
                orderDetailsList.add(orderDetails);
                order.put("orderDetailsList", orderDetailsList);
                orders.add(order);
            }
        }

        return orders;
    }
}
