package com.codecool.geek.service;

import com.codecool.geek.model.questionnaire.Answer;
import com.codecool.geek.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    public void saveAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    public List<Answer> getAnswersById(Long answerId) {
        return answerRepository.findAllById(answerId);
    }

    public List<Answer> getAnswersByQuestionId(Long questionId) {
        return answerRepository.findAllByQuestionId(questionId);
    }
}
