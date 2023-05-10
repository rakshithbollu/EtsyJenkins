package com.example.EtsyProject.EtsyProject.service.Response;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponse {
    private int productID;
    private String productName;
    private String shopName;
    private String imageUrl;
    private int quantity;
    private double price;
    private String currency;
    private int cartId;
    private int stock;
}
