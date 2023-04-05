package com.gabrielbazante.lab_dgs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gabrielbazante.lab_dgs.exceptions.ProductNotFoundException;
import com.gabrielbazante.lab_dgs.model.Product;
import com.gabrielbazante.lab_dgs.repository.ProductRepository;
import com.gabrielbazante.lab_dgs.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
    
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> productsExpected = new ArrayList<>();
        productsExpected.add(new Product("Product A", 10.0));
        productsExpected.add(new Product("Product B", 20.0));
        
        Mockito.when(productRepository.findAll()).thenReturn(productsExpected);

        List<Product> actualProducts = productService.getAllProducts();
        assertEquals(productsExpected, actualProducts);

    }

    @Test
    public void testGetProductById() throws Exception {
        Product productExpected = new Product("Product A", 10.0);
        productExpected.setId(1L);
        
        Mockito.when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(productExpected));

        Product actualProduct = productService.getProductById(1L);
        assertEquals(productExpected, actualProduct);

    }

    @Test
    public void testCreateProduct() throws Exception {
        Product productExpected = new Product("Product A", 10.0);
        productExpected.setId(1L);
        
        Mockito.when(productRepository.save(productExpected)).thenReturn(productExpected);

        Product actualProduct = productService.saveProduct(productExpected);
        assertEquals(productExpected, actualProduct);
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product productExpected = new Product("Product A", 10.0);
        productExpected.setId(1L);
        
        Mockito.when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(productExpected));
        Mockito.when(productRepository.save(productExpected)).thenReturn(productExpected);

        Product actualProduct = productService.updateProduct(1L, productExpected);
        assertEquals(productExpected, actualProduct);
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Product productExpected = new Product("Product A", 10.0);
        productExpected.setId(1L);

        Mockito.when(productRepository.findById(1L)).thenThrow(new ProductNotFoundException());
        assertThrowsExactly(ProductNotFoundException.class, () -> productService.deleteProduct(1L));
    }
    
}

