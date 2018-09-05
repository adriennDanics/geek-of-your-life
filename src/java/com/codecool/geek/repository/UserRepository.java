package com.codecool.geek.repository;

import com.codecool.geek.model.customer.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
