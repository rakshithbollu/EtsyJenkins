package com.example.EtsyProject.EtsyProject.service;

import com.example.EtsyProject.EtsyProject.dao.OrderDetailsRepository;
import com.example.EtsyProject.EtsyProject.dao.ProductRepository;
import com.example.EtsyProject.EtsyProject.dao.UserRepository;
import com.example.EtsyProject.EtsyProject.entity.Products;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.spi.SyncResolver;
import java.io.IOException;
import java.util.*;

@Service
public class ShopService {

    private UserRepository userRepository;
    private ProductRepository productRepository;
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public ShopService(UserRepository userRepository,
                       ProductRepository productRepository,
                       OrderDetailsRepository orderDetailsRepository){
        this.userRepository = userRepository;
        this.productRepository= productRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public Boolean getShopName(String shopName){
        Optional<String> result = userRepository.findByshopName(shopName);
        if (result.isEmpty())
        {
            return false;
        }
        return true;
    }
    public Boolean createShopName(String shopName,String email) throws Exception{
        Integer val = userRepository.createShop(shopName, email);
        try {
            if (val == 1) {
                return true;
            }
            else{
                throw new EntityNotFoundException("shopName is not updated");
            }
        }
        catch (Exception e) {
            throw new EntityNotFoundException("shopName is not updated");
        }
    }

    public Object getShopDetails(String shopName) throws Exception{
        try {
            List<Products> products = productRepository.findByShopName(shopName);
            if (products.isEmpty()) {
                List<Object[]> results = userRepository.findByShopNameDetails(shopName);
                if (!results.isEmpty()){
                    Map<String,Object> shopDetails = new HashMap<>();
                    for (Object[] row : results) {
                        String shopname = (String) row[0];
                        String name = (String) row[1];
                        String email = (String) row[2];
                        shopDetails.put("shopname",shopname);
                        shopDetails.put("name",name);
                        shopDetails.put("email",email);
                    }
                    return new HashMap<String, Object>(){{
                        put("success", true);
                        put("results", shopDetails);
                    }};
                }
                else{
                    throw new EntityNotFoundException("failure");
                }
            }
            else{
                List<Object[]> shopProducts = userRepository.findProductsByShopName(shopName);
                Integer totalsalesrevenue = orderDetailsRepository.findTotalSalesRevenueByShopName(shopName);
                if (!shopProducts.isEmpty()) {
                    List<Map<String, Object>> productDetails = new ArrayList<>();
                    for (Object[] row : shopProducts) {
                        Map<String, Object> shopDetails = new HashMap<>();
                        shopDetails.put("shopImage", (String) row[0]);
                        shopDetails.put("name", (String) row[1]);
                        shopDetails.put("email", (String) row[2]);
                        shopDetails.put("products", (Object) row[3]);
                        productDetails.add(shopDetails);

                    }
                    return new HashMap<>(){{
                        put("success",true);
                        put("results",productDetails);
                        put("totalsalesrevenue",totalsalesrevenue);
                    }};
                }
                else{
                    throw new EntityNotFoundException("failure");
                }
            }
        }
        catch (Exception e){
            throw new IOException(e);
        }
    }
}
