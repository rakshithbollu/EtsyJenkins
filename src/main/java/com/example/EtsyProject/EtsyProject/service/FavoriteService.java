package com.example.EtsyProject.EtsyProject.service;

import com.example.EtsyProject.EtsyProject.dao.FavoriteRepository;
import com.example.EtsyProject.EtsyProject.entity.Favorite;
import com.example.EtsyProject.EtsyProject.entity.FavoriteId;
import com.example.EtsyProject.EtsyProject.entity.Products;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FavoriteService {
    private FavoriteRepository favoriteRepository;

    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository){
        this.favoriteRepository= favoriteRepository;
    }

    public Favorite addFavorite(Favorite favorite){
        return favoriteRepository.save(favorite);
    }

    public String  deleteFavorite(FavoriteId favorite){
        favoriteRepository.deleteById(favorite);
        return "sucessfully deleted";
    }

    public List<Products> getFavoriteProducts(Map<String, String> favData) throws Exception{
        List<Products> products = favoriteRepository.getFavoriteById(favData.get("favkeyword"), favData.get("email"));
        if (products.isEmpty()){
          throw new EntityNotFoundException("Favorite products are not present for " + favData.get("email") + "please add the fav products" );
        }
        return products;
    }
}
