package com.example.EtsyProject.EtsyProject.dao;

import com.example.EtsyProject.EtsyProject.entity.Favorite;
import com.example.EtsyProject.EtsyProject.entity.FavoriteId;
import com.example.EtsyProject.EtsyProject.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {

    @Query(value = "SELECT P FROM Products P WHERE  P.productName like %:favkeyword% AND P.productId in (select F.productId from Favorite F where F.email=:email)", nativeQuery = false)
    List<Products> getFavoriteById(@Param("favkeyword") String favkeyword, @Param("email") String email);

    @Transactional
    @Modifying
    @Query("DELETE FROM Favorite F where F.productId =:productId")
    Integer deleteByProductId(@Param("productId") Integer productId);
}
