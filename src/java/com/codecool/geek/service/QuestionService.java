package com.codecool.geek.service;

import com.codecool.geek.model.questionnaire.Answer;
import com.codecool.geek.model.questionnaire.Question;
import com.codecool.geek.repository.AnswerRepository;
import com.codecool.geek.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    AnswerService answerService;

    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    public List<Question> getAllQuestion() {
        init();
        return questionRepository.findAll();
    }


    public void init() {
        Question q = new Question("sdasddsa");
        saveQuestion(q);

        Answer a = new Answer(q, "adadasdadasdasd");
        answerService.saveAnswer(a);


        System.err.println(q + "\n\n" + a);

    }
}
