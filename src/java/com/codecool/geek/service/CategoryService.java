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

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public List<Category> getCategoryById(Long categoryId) {
        return categoryRepository.findAllById(categoryId);
    }
}
