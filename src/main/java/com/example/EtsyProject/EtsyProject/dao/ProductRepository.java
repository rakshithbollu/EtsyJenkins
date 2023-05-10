package com.example.EtsyProject.EtsyProject.dao;

import com.example.EtsyProject.EtsyProject.entity.Products;
import com.example.EtsyProject.EtsyProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository  {

    Products findById(Integer productId);
    List<Products> queryProducts(String keyword,
                                 Double min_price,
                                 Double max_price,
                                 Integer outOfStock,
                                 String sortType);

    void updateProductStockAndSalesCount(int quantity,int productId);

    void changeCurrency(String currency);
    List<Products> findByShopName(String shopName);

    String save(Products products);
    Products update(Products products);
    void deleteById(Integer productId);
}

