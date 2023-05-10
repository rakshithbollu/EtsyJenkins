package com.example.EtsyProject.EtsyProject.controller;

import com.example.EtsyProject.EtsyProject.entity.Favorite;
import com.example.EtsyProject.EtsyProject.entity.FavoriteId;
import com.example.EtsyProject.EtsyProject.entity.Products;
import com.example.EtsyProject.EtsyProject.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class FavoriteController {

    private FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService){
        this.favoriteService = favoriteService;
    }
    @PostMapping("/favorite")
    public ResponseEntity<Favorite> addFavorite(@RequestBody Favorite favorite){

        return ResponseEntity.ok(favoriteService.addFavorite(favorite));
    }

    @PostMapping("/deletefavorite")
    public ResponseEntity<String> deleteFavorite(@RequestBody FavoriteId favorite){

        return ResponseEntity.ok(favoriteService.deleteFavorite(favorite));
    }

    @GetMapping("/getfavorite")
    public ResponseEntity<List<Products>> getFavorite(@RequestParam Map<String ,String > favData) throws Exception{
        return ResponseEntity.ok(favoriteService.getFavoriteProducts(favData));
    }
}
