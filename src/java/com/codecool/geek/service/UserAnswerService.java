package com.codecool.geek.service;

import com.codecool.geek.model.questionnaire.UserAnswer;
import com.codecool.geek.repository.UserAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAnswerService {

    @Autowired
    UserAnswerRepository userAnswerRepository;

    public List<UserAnswer> findByUserIdAndCategoryId(Long userId, Long categoryId) {
        System.out.println(userAnswerRepository.findByUserIdAndCategoryId(userId, categoryId));
        return userAnswerRepository.findByUserIdAndCategoryId(userId, categoryId);
    }
}
