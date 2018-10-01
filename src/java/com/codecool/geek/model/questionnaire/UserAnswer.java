package com.codecool.geek.model.questionnaire;

import com.codecool.geek.model.customer.User;

import javax.persistence.*;

@Entity
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Answer answer;

    @ManyToOne
    private Question question;

    @ManyToOne
    private Category category;

    public UserAnswer(User user, Answer answer, Question question, Category category) {
        this.user = user;
        this.answer = answer;
        this.question = question;
        this.category = category;
    }

    private UserAnswer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
