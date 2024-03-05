package org.example.productservice.services;

import org.example.productservice.dtos.FakeStoreProductDto;
import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Service("FakeProductService")
public class FakeProductServiceImpl  implements  ProductService {

    private RestTemplateBuilder restTemplateBuilder;

    private final String SPECIFIC_PRODUCT_URL = "https://fakestoreapi.com/products/{id}";
    private final String ALL_PRODUCTS_URL = "https://fakestoreapi.com/products";

    public FakeProductServiceImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        System.out.println("====>>>>>>>>>>>>  Inside Fake Product Service of getProductById");
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.getForEntity(SPECIFIC_PRODUCT_URL, FakeStoreProductDto.class, id);

        //System.out.println("On Fake Prod Service => Fetching product with id " + responseEntity.getBody());
        if (responseEntity.getBody() == null) {
            throw new ProductNotFoundException("Product ID # " + id + " not found. ");
        }
        return getProductFromFakeStoreProductDto(responseEntity.getBody());
    }

    @Override
    public List<Product> getAllProducts() {
        System.out.println("====>>>>>>>>>>>>  Inside Fake Product Service of getAllProducts");
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(ALL_PRODUCTS_URL, FakeStoreProductDto[].class);
        List<Product> productList = new LinkedList<>();
        for(FakeStoreProductDto fakeStoreProductDto: responseEntity.getBody()){
            productList.add(getProductFromFakeStoreProductDto(fakeStoreProductDto));
        }
        //return getProductFromFakeStoreProductDto(responseEntity.getBody());
        return productList;
    }

    @Override
    public void deleteProductById(Long id) {
        System.out.println("====>>>>>>>>>>>>  Inside DELETE = Fake Product Service of deleteProductById");
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(SPECIFIC_PRODUCT_URL, id);

    }

    public Product addProduct(Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.postForEntity(
                        ALL_PRODUCTS_URL,
                        getFakeStoreProductDtoFromProduct(product),
                        FakeStoreProductDto.class);

        return getProductFromFakeStoreProductDto(responseEntity.getBody());

    }

    @Override
    public void updateProductById() {

    }

    private Product getProductFromFakeStoreProductDto(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        // For Category object in Product
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    // Reverse

    private FakeStoreProductDto getFakeStoreProductDtoFromProduct(Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        return fakeStoreProductDto;
    }
}
