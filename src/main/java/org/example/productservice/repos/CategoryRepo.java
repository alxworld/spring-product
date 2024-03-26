package org.example.productservice.repos;

import org.example.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
