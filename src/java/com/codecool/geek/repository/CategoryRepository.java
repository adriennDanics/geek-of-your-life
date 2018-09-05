package com.codecool.geek.repository;

import com.codecool.geek.model.questionnaire.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAll();
    String findCategoryById(Long categoryId);
    List<Category> findAllById(Long id);
    List<String> findCategory();
}
