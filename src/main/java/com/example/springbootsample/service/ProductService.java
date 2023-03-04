package com.example.springbootsample.service;


import com.example.springbootsample.entity.Product;
import com.example.springbootsample.repository.ProductRepository;
import com.example.springbootsample.service.model.ProductRequestModel;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable(cacheNames = "product-cache" , key = "#id")
    public Optional<Product> findProduct(Long id){

        return productRepository.findById(id);
    }
    public Product addProduct (ProductRequestModel requestModel){
       var request= Product.builder()
                .name(requestModel.getName())
                .price(requestModel.getPrice())
                .description(requestModel.getDescription()).build();
       return productRepository.save(request);
    }
    @CacheEvict(cacheNames = "product-cache",key = "#p.id")
    public Product editProduct(Product p){

        return productRepository.save(p);
    }

    @CacheEvict(cacheNames = "product-cache",key = "#id")
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
