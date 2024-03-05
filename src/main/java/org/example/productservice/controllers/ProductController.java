package org.example.productservice.controllers;


import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Product;
import org.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

/*
Three types of Dependency Injection
1. Constructor injection ( done below )
2. Field injection ( @autowired at  field level -> ProductService productService; )
3. Set injection
@Autowired
public void setProductService(ProductService productService){
        this.productService = productService;
}
===> possibility of null pointer exception during unit testing when set function is not set before
===> 2nd reason to avoid is -> not good readability
 */
    ProductService productService;

    @Autowired
    public ProductController(@Qualifier("FakeProductService") ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        //return "Fetching product with id " + id;
        System.out.println("====>>>>>>>>>>>>  Inside Controller of getProductById");
        return productService.getProductById(id);
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        System.out.println("====>>>>>>>>>>>>  Inside Controller of getAllProducts");
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        System.out.println("====>>>>>>>>>>>>  Inside Controller of deleteProductById");
        productService.deleteProductById(id);
    }

}



/*

To DO list

1. getProductById(id)
2. getAllProducts()
2. getProductByCategory(category_id)
3. updateProductById(id)
4. deleteProduct(id)
5. addProduct()

 */