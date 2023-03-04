package com.example.springbootsample.controller;


import com.example.springbootsample.entity.Product;
import com.example.springbootsample.service.ProductService;
import com.example.springbootsample.service.model.ProductRequestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {
    private static final Logger logger= LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {

        this.productService = productService;
    }
    @GetMapping("/get/{id}")
    public Optional<Product> getProduct(@PathVariable Long id){
        logger.info("this endPointCall {} : " , id);
        return productService.findProduct(id);
    }
    @PostMapping("/add")
    public Product addProduct(@RequestBody @Validated ProductRequestModel p){

        return productService.addProduct(p);
    }
    @PostMapping("/edit")
    public  Product editProduct(@RequestBody Product p){
        return productService.editProduct(p);
    }
}
