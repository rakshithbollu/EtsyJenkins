package com.example.EtsyProject.EtsyProject.dao;

import com.example.EtsyProject.EtsyProject.entity.Orders;
import com.example.EtsyProject.EtsyProject.service.Response.OrderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Integer> {
    @Query("SELECT new com.example.EtsyProject.EtsyProject.service.Response.OrderResponse(o.orderId, o.orderDate, od.totalPrice, od.orderDetailsId, od.quantity,od.price, p.productId, p.productName, p.currency, p.shopName,p.imageUrl) FROM Orders o JOIN o.orderDetailsList od JOIN od.products p WHERE o.email = :email")
    List<OrderResponse> findByEmailWithDetails(@Param("email") String email);


}
