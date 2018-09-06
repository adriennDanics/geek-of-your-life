package com.codecool.geek.api;

import com.codecool.geek.model.customer.User;
import com.codecool.geek.model.customer.UserDetail;
import com.codecool.geek.model.questionnaire.Category;
import com.codecool.geek.service.UserDetailService;
import com.codecool.geek.service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserApi {

    @Autowired
    UserService userService;

    @Autowired
    UserDetailService userDetailService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<?> createNewUser(@RequestParam("email") String email, @RequestParam("password") String password){
        userService.saveUser(new User(email, password));
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/user/profile/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> createNewUser(@PathVariable("id") Long id, @RequestParam("category") String category){
        User user = userService.findById(id);
        UserDetail userDetail = new UserDetail(user);
        userDetail.addCategory(new Category(category));
        userDetailService.saveUserDetail(userDetail);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> getUserList(){
        Map<String, Map<String, String>> users = new HashMap<>();
        List<User> userList = userService.getAllUsers();
        for (User user: userList) {
            Map<String, String> usersInfo = new HashMap<>();
            usersInfo.put("id", String.valueOf(user.getId()));
            usersInfo.put("email", user.getEmail());
            users.put(String.valueOf(user.getId()), usersInfo);
        }
        JSONObject response = new JSONObject(users);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserLoginInfo(@PathVariable("id") Long id){
        Map<String, User> userInfo = new HashMap<>();
        User user = userService.findById(id);
        userInfo.put("user", user);
        JSONObject response = new JSONObject(userInfo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/profile/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserInfo(@PathVariable("id") Long id){
        UserDetail userDetail = userDetailService.findByUserId(id);
        Map<String, UserDetail> userInfo = new HashMap<>();
        userInfo.put("details", userDetail);
        JSONObject response = new JSONObject(userInfo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
