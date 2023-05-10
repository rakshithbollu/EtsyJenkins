package com.example.EtsyProject.EtsyProject.dao;

import com.example.EtsyProject.EtsyProject.entity.Products;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDao implements ProductRepository{
    private EntityManager entityManager;

    @Autowired
    public ProductDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Products findById(Integer productId) {
        return entityManager.find(Products.class, productId);
    }

    @Override
    public List<Products> queryProducts(String keyword, Double min_price, Double max_price, Integer outOfStock, String sortType) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Products> cq = cb.createQuery(Products.class);
        Root<Products> root = cq.from(Products.class);
        List<Predicate> predicates = new ArrayList<>();

        // Add the LIKE predicate for product name
        predicates.add(cb.like(root.get("productName"), "%" + keyword + "%"));

        // Add the BETWEEN predicate for price
        predicates.add(cb.between(root.get("price"), min_price, max_price));

        // Add the >= predicate for stock
        predicates.add(cb.greaterThanOrEqualTo(root.get("stock"), outOfStock));

        // Set the ORDER BY clause dynamically
        if (sortType.equals("price")) {
            cq.orderBy(cb.asc(root.get("price")));
        } else if (sortType.equals("stock")) {
            cq.orderBy(cb.desc(root.get("stock")));
        } else if (sortType.equals("salescount")){
            cq.orderBy(cb.asc(root.get("saleCount")));
        }
        cq.select(root).where(predicates.toArray(new Predicate[] {}));
        TypedQuery<Products> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    @Transactional
    @Modifying
    public void updateProductStockAndSalesCount(int quantity, int productId) {
        Query query = entityManager.createQuery("UPDATE Products p SET p.stock = (p.stock - :quantity), p.salesCount = (p.salesCount + :quantity) WHERE p.productId = :productId");
        query.setParameter("quantity", quantity);
        query.setParameter("productId", productId);
        query.executeUpdate();
    }

    @Override
    @Transactional
    @Modifying
    public void changeCurrency(String currency) {
        Query query = entityManager.createQuery("UPDATE Products p SET p.currency = :currency");
        query.setParameter("currency",currency);
        query.executeUpdate();
    }

    @Override
    public List<Products> findByShopName(String shopName) {
        TypedQuery<Products> query = entityManager.createQuery("SELECT P FROM Products P WHERE P.shopName=:shopName",Products.class);
        query.setParameter("shopName",shopName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public String save(Products products) {
       entityManager.persist(products);
       return "success";
    }

    @Override
    @Transactional
    @Modifying
    public Products update(Products products) {
        return entityManager.merge(products);
    }

    @Override
    @Transactional
    @Modifying
    public void deleteById(Integer productId) {
        Products product = entityManager.find(Products.class,productId);
        entityManager.remove(product);
    }
}
