package org.example.productservice.controllers;

import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Product;
import org.example.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    ProductController productController;

    @MockBean
    ProductService productService;
    @Test
    void getProductById() throws ProductNotFoundException {

        // Arrange
        Product dummy = new Product();
        dummy.setId(1L);
        dummy.setTitle("DummyTitle");
        dummy.setDescription("DummyDesc");
        when(productService.getProductById(1L)).thenReturn(dummy);

        // Act
        Product p = productController.getProductById(1L);

        // Assert
        assertEquals(1L, p.getId());
    }

    @Test
    void getProductByIdThrowsException() throws ProductNotFoundException {

        // Arrange
//        Product dummy = new Product();
//        dummy.setId(1L);
//        dummy.setTitle("DummyTitle");
//        dummy.setDescription("DummyDesc");
        when(productService.getProductById(1L)).thenThrow(new ProductNotFoundException("Product Not Found."));

        // Assert
        assertThrows(ProductNotFoundException.class, ()-> productService.getProductById(1L));
    }
}