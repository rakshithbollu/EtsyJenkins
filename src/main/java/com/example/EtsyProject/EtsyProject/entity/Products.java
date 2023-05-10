package com.example.EtsyProject.EtsyProject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="products")
@Getter
@Setter
@ToString
@Builder
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="productid")
    private int productId;

    @Column(name="productname")
    private String productName;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private Double price;

    @Column(name="category")
    private String category;

    @Column(name="stock")
    private int stock;

    @Column(name="image_URL")
    private String imageUrl;

    @Column(name="shopname")
    private String shopName;

    @Column(name="currency")
    private String currency;

    @Column(name="salescount")
    private int salesCount;

//    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<OrderDetails> orderDetails = new HashSet<>();

    public Products(){

    }
    public Products(String productName, String description, Double price, String category, int stock, String imageUrl, String shopName, String currency, int salesCount) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.shopName = shopName;
        this.currency = currency;
        this.salesCount = salesCount;
    }

    public Products(int productId, String productName, String description, Double price, String category, int stock, String imageUrl, String shopName, String currency, int salesCount) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.shopName = shopName;
        this.currency = currency;
        this.salesCount = salesCount;
    }
}

