package com.example.EtsyProject.EtsyProject.service.Response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private int orderId;
    private LocalDateTime orderDate;
    private double totalPrice;
    private int orderDetailsId;
    private int quantity;
    private double price;
    private int productId;
    private String productName;
    private String currency;
    private String shopName;
    private String imageUrl;
}
