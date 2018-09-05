package com.codecool.geek.repository;

import com.codecool.geek.model.questionnaire.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAll();
}
