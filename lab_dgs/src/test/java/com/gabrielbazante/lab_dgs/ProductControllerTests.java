package com.gabrielbazante.lab_dgs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielbazante.lab_dgs.controller.ProductController;
import com.gabrielbazante.lab_dgs.model.Product;
import com.gabrielbazante.lab_dgs.service.ProductService;

@WebMvcTest(ProductController.class)
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product A", 10.0));
        products.add(new Product("Product B", 20.0));
        Mockito.when(productService.getAllProducts()).thenReturn(products);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> responseProducts = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Product>>() {});
        assertEquals(2, responseProducts.size());
        assertEquals("Product A", responseProducts.get(0).getName());
        assertEquals(10.0, responseProducts.get(0).getPrice(), 0.01);
        assertEquals("Product B", responseProducts.get(1).getName());
        assertEquals(20.0, responseProducts.get(1).getPrice(), 0.01);

        Mockito.verify(productService, Mockito.times(1)).getAllProducts();
    }
}

