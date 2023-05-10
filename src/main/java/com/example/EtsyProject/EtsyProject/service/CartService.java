package com.example.EtsyProject.EtsyProject.service;

import com.example.EtsyProject.EtsyProject.dao.CartRepository;
import com.example.EtsyProject.EtsyProject.entity.Cart;
import com.example.EtsyProject.EtsyProject.service.Response.CartResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public String addCartDetails(Cart cart) {
        Optional<Cart> cartRes = Optional.ofNullable(cartRepository.findCartDetails(cart.getEmail(), cart.getProductId()));
        if (cartRes.isPresent()) {
            cartRepository.updateQuantity(cart.getEmail(), cart.getQuantity(), cart.getProductId());
        }
        else{
            cartRepository.save(cart);
        }
        return "success";
    }

    public Optional<List<CartResponse>> getCartItems(String email) throws Exception{
        Optional<List<CartResponse>> cartList= Optional.ofNullable(cartRepository.findCartByEmail(email));
        cartList.orElseThrow( () ->
                new EntityNotFoundException(
                        "No favorite products found for " + email + "please add the items in list"));

    return cartList;
    }

    public String deleteCartItems(Integer cartId){
        cartRepository.deleteById(cartId);
        return "deleted successfully";
    }
}

