package com.codecool.geek.repository;

import com.codecool.geek.model.customer.User;
import com.codecool.geek.model.questionnaire.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {

    List<UserAnswer> findByUserIdAndCategoryId(Long userId, Long categoryId);
}
