package com.gabrielbazante.lab_dgs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielbazante.lab_dgs.model.Product;
import com.gabrielbazante.lab_dgs.repository.ProductRepository;

@RestController
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value = "/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping(value = "/products/{id}")
    public Optional<Product> findById(@PathVariable Long id) {
        return productRepository.findById(id);
    }

    @PostMapping(value = "/products")
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, Product product) {
        return productRepository.findById(id).map(record -> {
            record.setName(product.getName());
            record.setPrice(product.getPrice());
            Product updated = productRepository.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return productRepository.findById(id).map(record -> {
            productRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
