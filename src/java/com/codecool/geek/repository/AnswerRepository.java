package com.codecool.geek.repository;

import com.codecool.geek.model.questionnaire.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository  extends JpaRepository<Answer, Long> {
}

