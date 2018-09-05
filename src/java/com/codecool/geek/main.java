package com.codecool.geek;

import com.codecool.geek.model.questionnaire.Answer;
import com.codecool.geek.model.questionnaire.Question;
import com.codecool.geek.service.AnswerService;
import com.codecool.geek.service.QuestionService;

class main {
    public static void main(String[] args) {

        Question q = new Question("sdasddsa");
        Answer a = new Answer(q, "adadasdadasdasd");

        System.out.println(q + "\n\n" + a);



        QuestionService questionService = new QuestionService();
        questionService.saveQuestion(q);
        AnswerService answerService = new AnswerService();
        answerService.saveAnswer(a);
    }
}
