package com.codecool.geek.service;

import com.codecool.geek.model.customer.UserDetail;
import com.codecool.geek.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    public UserDetail findByUserId(Long id) {
        return userDetailsRepository.findByUserId(id);
    }

    public void saveUserDetail(UserDetail userDetail){
        userDetailsRepository.save(userDetail);
    }
}
