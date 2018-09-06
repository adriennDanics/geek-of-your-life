package com.codecool.geek.repository;

import com.codecool.geek.model.questionnaire.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository  extends JpaRepository<Answer, Long> {
    List<Answer> findAllById(Long answerId);
    List<Answer> findAllByQuestionId(Long questionId);

}

