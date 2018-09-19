package com.codecool.geek.api;

import com.codecool.geek.model.questionnaire.Answer;
import com.codecool.geek.model.questionnaire.Category;
import com.codecool.geek.model.questionnaire.Question;
import com.codecool.geek.service.AnswerService;
import com.codecool.geek.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserQuestionnaireApi {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;


    @RequestMapping(value = "/add-question", method = RequestMethod.POST)
    public ResponseEntity<?> addQuestion(@RequestParam("question") String question, @RequestParam("answer") String answer) {

        //questionService.init();


        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/answers", method = RequestMethod.GET)
    public List<Answer> getAnswers() {


        return answerService.getAnswersById(1L);
    }

    public void init() {
        Question q = new Question("sdasddsa");
        questionService.saveQuestion(q);

        Answer a = new Answer(q, "adadasdadasdasd");
        answerService.saveAnswer(a);

        Category category = new Category("ASAS","sfsdfsf","sfsfsfs");

        System.err.println(q + "\n\n" + a);

    }
}
