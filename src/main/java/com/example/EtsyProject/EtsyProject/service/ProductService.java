package com.example.EtsyProject.EtsyProject.service;

import com.example.EtsyProject.EtsyProject.dao.CartRepository;
import com.example.EtsyProject.EtsyProject.dao.FavoriteRepository;
import com.example.EtsyProject.EtsyProject.dao.ProductRepository;
import com.example.EtsyProject.EtsyProject.entity.Cart;
import com.example.EtsyProject.EtsyProject.entity.Favorite;
import com.example.EtsyProject.EtsyProject.entity.Products;
import com.example.EtsyProject.EtsyProject.service.requests.SearchProductRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CartRepository cartRepository;
    private FavoriteRepository favoriteRepository;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          CartRepository cartRepository,
                          FavoriteRepository favoriteRepository) {
        this.productRepository = productRepository;
        this.cartRepository=cartRepository;
        this.favoriteRepository= favoriteRepository;
    }

    public List<Products> searchProducts(SearchProductRequest searchProductRequest, String keyword){
        if (keyword.equalsIgnoreCase("undefined")){
            keyword = "";
        }

        List<Products> products =  productRepository.queryProducts(keyword,
                searchProductRequest.getMin_price(),
                searchProductRequest.getMax_price(),
                searchProductRequest.getOutOfStock(),
                searchProductRequest.getSortType());
        return products;
    }

    public Optional<Products> getProductDetail(Integer productid) throws Exception{
        Optional<Products> result = Optional.ofNullable(productRepository.findById(productid));
        result.orElseThrow(() -> new EntityNotFoundException("Product ID Not found: " + productid));
        return result;
    }

    public String changeCurrency(String currency){
        productRepository.changeCurrency(currency);
        return "success";
    }
    public void saveProducts(Products products) throws Exception{
        try {
            productRepository.save(products);
        }
        catch (Exception e){
            throw new IOException(e);
        }
    }

    public Products updateProducts(Products products) throws Exception{
        try {
            return productRepository.update(products);
        }
        catch (Exception e){
            throw new IOException(e);
        }
    }

    public String deleteProducts(Integer productId) throws Exception{
        try {
            productRepository.deleteById(productId);
            cartRepository.deleteByProductId(productId);
            favoriteRepository.deleteByProductId(productId);
            return "Deleted Succesfully";
        }
        catch (Exception e){
            throw new IOException(e);
        }
    }
}
