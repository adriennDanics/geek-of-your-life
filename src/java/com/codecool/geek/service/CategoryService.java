package com.codecool.geek.service;

import com.codecool.geek.model.questionnaire.Category;
import com.codecool.geek.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> getAllCategoryDetails() {
        return categoryRepository.findAll();
    }

    public String getCategoryById(Long categoryId) {
        return categoryRepository.findCategoryById(categoryId);
    }

    public List<String> getAllCategories() {
        return categoryRepository.findCategory();
    }
}
