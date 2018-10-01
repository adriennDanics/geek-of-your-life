package com.codecool.geek.service;

import com.codecool.geek.repository.UserAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAnswerService {

    @Autowired
    UserAnswerRepository userAnswerRepository;
}
