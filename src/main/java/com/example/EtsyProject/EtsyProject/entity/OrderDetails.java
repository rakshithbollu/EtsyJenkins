package com.example.EtsyProject.EtsyProject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orderdetails")
@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@ToString
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orderdetailsid")
    private int orderDetailsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productid")
    private Products products;

    @Column(name="quantity")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderid")
    private Orders orderId;

    @Column(name="shopname")
    private String shopName;

    @Column(name="price")
    private double price;

    @Column(name="totalprice")
    private double totalPrice;


    public OrderDetails(Products products, int quantity, Orders orderId, String shopName, double price, double totalPrice) {
        this.products = products;
        this.quantity = quantity;
        this.orderId = orderId;
        this.shopName = shopName;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public OrderDetails(int orderDetailsId, Products products, int quantity, Orders orderId, String shopName, double price, double totalPrice) {
        this.orderDetailsId = orderDetailsId;
        this.products = products;
        this.quantity = quantity;
        this.orderId = orderId;
        this.shopName = shopName;
        this.price = price;
        this.totalPrice = totalPrice;
    }
}
