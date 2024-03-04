package org.example.productservice.controllers;

import org.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String getProductById(@PathVariable("id") Long id){
        //return "Fetching product with id " + id;
        return productService.getProductById(id);
    }

    @GetMapping("/")
    public List<String> getAllProducts(){
        return Collections.emptyList();
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