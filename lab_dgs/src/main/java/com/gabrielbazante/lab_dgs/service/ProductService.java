package com.gabrielbazante.lab_dgs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.gabrielbazante.lab_dgs.exceptions.ProductNotFoundException;
import com.gabrielbazante.lab_dgs.model.Product;
import com.gabrielbazante.lab_dgs.repository.ProductRepository;



@Service
@CacheConfig(cacheNames = "products")
public class ProductService {
 
    @Autowired
    private ProductRepository productRepository;
 
    @Cacheable
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
 
    @Cacheable(key = "#id", unless = "#result == null")
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        throw new ProductNotFoundException("Product not found with id " + id);
    }
 
    @CachePut(key = "#product.id")
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
 
    @CacheEvict(key = "#id")
    public void deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
        }
        throw new ProductNotFoundException("Product not found with id " + id);
    }

    @CachePut(key = "#product.id")
    public Product updateProduct(long l, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if (optionalProduct.isPresent()) {
            Product productFromDb = optionalProduct.get();
            productFromDb.setName(product.getName());
            productFromDb.setPrice(product.getPrice());
            return productRepository.save(productFromDb);
        }
        throw new ProductNotFoundException("Product not found with id " + product.getId());
    }

    
 
}

