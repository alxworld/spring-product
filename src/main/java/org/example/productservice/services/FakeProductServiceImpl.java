package org.example.productservice.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service("FakeProductService")
public class FakeProductServiceImpl  implements  ProductService {
    @Override
    public String getProductById(Long id) {
        return "On Fake Prod Service => Fetching product with id " + id;
    }

    @Override
    public List<String> getAllProducts() {
        return null;
    }

    @Override
    public void deleteProductById() {

    }

    @Override
    public void addProduct() {

    }

    @Override
    public void updateProductById() {

    }
}
