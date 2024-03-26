package org.example.productservice.services;

import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.repos.CategoryRepo;
import org.example.productservice.repos.ProductRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service("SelfProductService")
public class ProductServiceImpl implements ProductService {

    ProductRepo productRepo;
    CategoryRepo categoryRepo;

    public ProductServiceImpl(
            ProductRepo productRepo,
            CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }
    @Override
    public Product getProductById(Long id) {
        System.out.println("On Prod Service => Fetching product with id " + id);
        Optional<Product> product = productRepo.findById(id);
        if(product.isPresent()){
            Category category = product.get().getCategory();
        }
        return product.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public void deleteProductById(Long id) {

    }

    @Override
    public Product addProduct(Product product) {
        System.out.println("Inside addProduct in ProdServiceImpl " + product.getTitle());
//        Optional<Category> categoryOptional =
//                this.categoryRepo.findByName(product.getCategory().getName());
//        if (categoryOptional.isPresent()){
//            product.setCategory(categoryOptional.get());
//        } else {
//            Category category = categoryRepo.save(product.getCategory());
//            product.setCategory(category);
//        }
        return productRepo.save(product);
    }

    @Override
    public Product updateProductById(Long id) {
        return null;
//        System.out.println("Inside updateProductById in ProdServiceImpl " + product.getId());
//        Optional<Product> productOptional = productRepo.findById(product.getId());
//        if(productOptional.isEmpty()){
//            System.out.println("Product Not Found for id # " + product.getId());
//            return null;
//        }
//        //Product product = productOptional.get();
//        Optional<Category> categoryOptional =
//                this.categoryRepo.findByName(product.getCategory().getName());
//        if (categoryOptional.isPresent()){
//            product.setCategory(categoryOptional.get());
//        } else {
//            Category category = categoryRepo.save(product.getCategory());
//            product.setCategory(category);
//        }
//        return productRepo.save(product);
    }
}
