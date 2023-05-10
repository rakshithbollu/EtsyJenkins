package com.example.EtsyProject.EtsyProject.controller;

import com.example.EtsyProject.EtsyProject.entity.Orders;
import com.example.EtsyProject.EtsyProject.service.OrderService;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<String> saveOrders(@RequestBody Orders orders) throws Exception{
        return ResponseEntity.ok(orderService.saveOrder(orders));
    }

    @GetMapping("/orders/{email}")
    public ResponseEntity<List<Map<String, Object>>> getOrders(@PathVariable String email){
        return ResponseEntity.ok(orderService.getOrders(email));
    }
}
