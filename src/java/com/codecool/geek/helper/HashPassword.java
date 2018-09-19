package com.codecool.geek.helper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class HashPassword {

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean isPasswordCorrect(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
