package com.codecool.geek.repository;

import com.codecool.geek.model.questionnaire.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
