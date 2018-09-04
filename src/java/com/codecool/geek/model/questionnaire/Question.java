package com.codecool.geek.model.questionnaire;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String question;

    @OneToMany(mappedBy = "")
    private Set<Answer> answers = new HashSet<>();

    public Question(String question, Set<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public Question() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer){
        answers.add(answer);
    }
}
