package com.example.EtsyProject.EtsyProject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.loader.ast.spi.Loadable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@ToString
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orderid")
    private int orderId;

    @Column(name="email")
    private String email;

    @Column(name="orderdate")
    private LocalDateTime orderDate;

    @Column(name="totalprice")
    private double totalPrice;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetailsList;

    public Orders(int orderId, String email, LocalDateTime orderDate, double totalPrice, List<OrderDetails> orderDetailsList) {
        this.orderId = orderId;
        this.email = email;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderDetailsList = orderDetailsList;
    }

    public Orders(String email, LocalDateTime orderDate, double totalPrice, List<OrderDetails> orderDetailsList) {
        this.email = email;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderDetailsList = orderDetailsList;
    }

    public Orders(String email, double totalPrice, List<OrderDetails> orderDetailsList) {
        this.email = email;
        this.totalPrice = totalPrice;
        this.orderDetailsList = orderDetailsList;
    }
}
