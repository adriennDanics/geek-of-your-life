package com.codecool.geek.api;

import com.codecool.geek.model.questionnaire.Answer;
import com.codecool.geek.model.questionnaire.Question;
import com.codecool.geek.service.AnswerService;
import com.codecool.geek.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserQuestionnaireApi {

    @Autowired
    QuestionService questionService;

    @RequestMapping(value = "/add-question", method = RequestMethod.POST)
    public String addQuestion() {

        questionService.init();


        return "success";
    }
}
