package org.example.productservice.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("FakeProductService")
public class FakeProductServiceImpl  implements  ProductService {

    private RestTemplateBuilder restTemplateBuilder;

    private final String PRODUCT_URL = "https://fakestoreapi.com/products/1";

    public FakeProductServiceImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public String getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(PRODUCT_URL, String.class);
        return "On Fake Prod Service => Fetching product with id " + responseEntity.toString();
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
