package org.example.productservice.services;

import java.util.List;

public interface ProductService {
    String getProductById(Long id);

    List<String> getAllProducts();

    void deleteProductById();
    void addProduct();
    void updateProductById();
}
