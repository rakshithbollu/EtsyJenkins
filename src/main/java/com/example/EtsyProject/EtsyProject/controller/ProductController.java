package com.example.EtsyProject.EtsyProject.controller;

import com.example.EtsyProject.EtsyProject.entity.Products;
import com.example.EtsyProject.EtsyProject.service.ProductService;
import com.example.EtsyProject.EtsyProject.service.requests.SearchProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //display the product details based on the products given in the search query
    @PostMapping("/getSearchDetails/{keyword}")
    public ResponseEntity<List<Products>> searchProducts(@RequestBody SearchProductRequest searchProductRequest,
                                                        @PathVariable String keyword){
        List<Products> products = productService.searchProducts(searchProductRequest, keyword);
        System.out.println(products);
        return ResponseEntity.ok(products);
    }

    //display the product details based on the products given in the search query
    @GetMapping("/getProductDetail/{productid}")
    public ResponseEntity<Optional<Products>> getProductDetail(@PathVariable Integer productid) throws Exception{
        return ResponseEntity.ok(productService.getProductDetail(productid));
    }


    @PutMapping("/changecurrency")
    public ResponseEntity<String> changeCurrency(@RequestBody String currency){
        return ResponseEntity.ok(productService.changeCurrency(currency));
    }

    @PostMapping("/saveproduct")
    public ResponseEntity<Object> saveProduct(@RequestBody Products products) throws Exception{
            productService.saveProducts(products);
            return ResponseEntity.ok().body(new HashMap<String,Object>()
            {{
                put("success",true);
            }}
            );
    }

    @PutMapping("/updateproduct")
    public ResponseEntity<Products> updateProduct(@RequestBody Products products) throws Exception{
       try {
           return ResponseEntity.ok().body(productService.updateProducts(products));
       }
       catch(Exception e){
           throw new IOException(e);
       }
    }

    @DeleteMapping("/deleteproduct/{productId}")
    public ResponseEntity<Object> updateProduct(@PathVariable Integer productId) throws Exception{
        try {
            productService.deleteProducts(productId);
            return ResponseEntity.ok().body(new HashMap<String,Boolean>()
                    {{
                                put("success",true);
                            }
                    }
            );
        }
        catch(Exception e){
            throw new IOException(e);
        }
    }

    @GetMapping("/test")
    public String hello(){
        return "hello";
    }
}
