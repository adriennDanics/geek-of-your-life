package com.codecool.geek.service;

import com.codecool.geek.model.questionnaire.Category;
import com.codecool.geek.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> getAllCategoryDetails() {
        return categoryRepository.findAll();
    }

    public String getCategoryNameById(Long categoryId) {
        return categoryRepository.findCategoryById(categoryId);
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findAllById(categoryId);
    }
}
