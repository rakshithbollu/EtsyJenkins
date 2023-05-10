package com.example.EtsyProject.EtsyProject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart")
@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartid")
    private int cartId;

    @Column(name = "email")
    private String email;

    @Column(name = "productid")
    private int productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "shopname")
    private String shopName;

    public Cart(int cartId, String email, int productId, int quantity, double price, String shopName) {
        this.cartId = cartId;
        this.email = email;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.shopName = shopName;
    }

    public Cart(String email, int productId, int quantity, double price, String shopName) {
        this.email = email;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.shopName = shopName;
    }
}
