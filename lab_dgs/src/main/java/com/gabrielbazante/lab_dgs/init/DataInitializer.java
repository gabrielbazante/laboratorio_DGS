package com.gabrielbazante.lab_dgs.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.gabrielbazante.lab_dgs.model.Product;
import com.gabrielbazante.lab_dgs.repository.ProductRepository;

@Component
public class DataInitializer implements CommandLineRunner{

    private final ProductRepository productRepository;

    public DataInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        Product product1 = new Product("Camiseta", 29.90);
        Product product2 = new Product("Calça Jeans", 79.90);
        Product product3 = new Product("Tênis", 99.90);
        Product product4 = new Product("Bermuda", 49.90);
        Product product5 = new Product("Bermuda Azul", 149.90);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
    }

    
    
}
