package org.example.productservice.services;

import org.example.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductById(Long id) {
        System.out.println("On Prod Service => Fetching product with id " + id);
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public void deleteProductById() {

    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public void updateProductById() {

    }
}
