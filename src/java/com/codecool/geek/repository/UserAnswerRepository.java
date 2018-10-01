package com.codecool.geek.repository;

import com.codecool.geek.model.questionnaire.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
}
