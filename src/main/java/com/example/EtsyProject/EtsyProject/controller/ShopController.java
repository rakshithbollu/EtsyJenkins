package com.example.EtsyProject.EtsyProject.controller;

import com.example.EtsyProject.EtsyProject.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/shop")
public class ShopController {

    private ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService){
        this.shopService= shopService;
    }

    @GetMapping("shopname/{shopName}")
    public ResponseEntity<Map<String, Boolean>> uniqueShopName(@PathVariable String shopName){
        Map<String, Boolean> result = new HashMap<>();
        if(shopService.getShopName(shopName)){
            result.put("success",false);
        }
        else{
            result.put("success",true);
        }
        return ResponseEntity.status(200).body(result);
    }
    @PutMapping("createshopname/{email}")
    public ResponseEntity<Map<String, Boolean>> createShopName(@PathVariable String email,
                                                               @RequestBody Map<String,String> shopData) throws Exception{
        Map<String, Boolean> result = new HashMap<>();
        if(shopService.createShopName(shopData.get("shopName"), email)){
            result.put("success",true);
        }
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("getshopdetails/{shopName}")
    public ResponseEntity<Object> createShopName(@PathVariable String shopName) throws Exception{
        try {
            return ResponseEntity.ok().body(shopService.getShopDetails(shopName));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("server error");
        }
    }
}
