package com.example.EtsyProject.EtsyProject.dao;

import com.example.EtsyProject.EtsyProject.entity.Cart;
import com.example.EtsyProject.EtsyProject.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Integer> {
    @Query("SELECT SUM(od.price * od.quantity) as totalsalesrevenue FROM OrderDetails od WHERE od.shopName = :shopName")
    Integer findTotalSalesRevenueByShopName(@Param("shopName") String shopName);
}
