package org.example.productservice.thirdpartyclients;

import org.example.productservice.dtos.FakeStoreProductDto;
import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@Component
public class FakeStoreClient {

    private RestTemplateBuilder restTemplateBuilder;

    @Value("${fakestore.api.specific.url}")
    private String SpecificUrlForFakeStore;
    @Value("${fakestore.api.generic.url}")
    private String GenericUrlForFakeStore;

    @Autowired
    public FakeStoreClient(
            RestTemplateBuilder restTemplateBuilder) {
//          //@Value("${fakestore.api.generic.url}") String fakeStoreGenericUrl){
        this.restTemplateBuilder = restTemplateBuilder;
//        this.SpecificUrlForFakeStore = fakeStoreSpecificUrl;
//        this.GenericUrlForFakeStore = fakeStoreGenericUrl;
    }

    public FakeStoreProductDto getProductById(Long id) throws ProductNotFoundException {
        System.out.println("====>>>>>>>>>>>>  Inside Fake Product Service of getProductById");
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.getForEntity(SpecificUrlForFakeStore, FakeStoreProductDto.class, id);

        //System.out.println("On Fake Prod Service => Fetching product with id " + responseEntity.getBody());
        if (responseEntity.getBody() == null) {
            throw new ProductNotFoundException("Product ID # " + id + " not found. ");
        }
        return responseEntity.getBody();
    }


    public FakeStoreProductDto[] getAllProducts() {
        System.out.println("====>>>>>>>>>>>>  Inside Fake Product Service of getAllProducts");
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(GenericUrlForFakeStore, FakeStoreProductDto[].class);
        return responseEntity.getBody();
    }


    public void deleteProductById(Long id) {
        System.out.println("====>>>>>>>>>>>>  Inside DELETE = Fake Product Service of deleteProductById");
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(SpecificUrlForFakeStore, id);

    }

    public FakeStoreProductDto addProduct(FakeStoreProductDto fakeStoreProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.postForEntity(
                        GenericUrlForFakeStore,
                        fakeStoreProductDto,
                        FakeStoreProductDto.class);

        return responseEntity.getBody();

    }


    public Product updateProductById(Long product) {
        return null;
    }

}
