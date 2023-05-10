package com.example.EtsyProject.EtsyProject.dao;

import com.example.EtsyProject.EtsyProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    @Query("SELECT U.shopName FROM User U WHERE U.shopName=:shopName")
    Optional<String> findByshopName(@Param("shopName") String shopName);

    @Transactional
    @Modifying
    @Query("UPDATE User U set U.shopName=:shopName WHERE U.email=:email")
    Integer createShop(@Param("shopName") String shopName,
                    @Param("email") String email);

    @Query("SELECT U.shopName, U.name,U.email FROM User U WHERE U.shopName=:shopName")
    List<Object[]> findByShopNameDetails(@Param("shopName") String shopName);

    @Query("SELECT U.shopImage, U.name,U.email,P FROM User U, Products P WHERE U.shopName=:shopName AND P.shopName=:shopName")
    List<Object[]> findProductsByShopName(@Param("shopName") String shopName);
}
