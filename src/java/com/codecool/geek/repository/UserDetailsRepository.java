package com.codecool.geek.repository;

import com.codecool.geek.model.customer.User;
import com.codecool.geek.model.customer.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetail, Long> {

    UserDetail findByUserId(Long userId);
}
