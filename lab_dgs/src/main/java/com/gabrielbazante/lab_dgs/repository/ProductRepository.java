package com.gabrielbazante.lab_dgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gabrielbazante.lab_dgs.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
}
