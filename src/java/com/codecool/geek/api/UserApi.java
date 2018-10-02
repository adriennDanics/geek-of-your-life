package com.codecool.geek.api;

import com.codecool.geek.helper.HashPassword;
import com.codecool.geek.model.customer.User;
import com.codecool.geek.model.customer.UserDetail;
import com.codecool.geek.model.questionnaire.Category;
import com.codecool.geek.model.questionnaire.UserAnswer;
import com.codecool.geek.service.CategoryService;
import com.codecool.geek.service.UserAnswerService;
import com.codecool.geek.service.UserDetailService;
import com.codecool.geek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class UserApi {

    @Autowired
    UserService userService;

    @Autowired
    UserDetailService userDetailService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    HashPassword hashPassword;

    @Autowired
    UserAnswerService userAnswerService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<?> createNewUser(@RequestBody User user){
        User newUser = new User(user.getEmail(), hashPassword.hashPassword(user.getPassword()));
        userService.saveUser(newUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody User user){
        User loginUser = userService.findByEmail(user.getEmail());
        boolean isUserRight = hashPassword.isPasswordCorrect(user.getPassword(), loginUser.getPassword());
        if(isUserRight) {
            return new ResponseEntity<>(userService.findById(loginUser.getId()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Fail", HttpStatus.OK);
        }
    }

    //TODO: make new userDetail post method to add birthday, fullname, etc.
    @RequestMapping(value = "/user/profile/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> createNewUserProfile(@PathVariable("id") Long id, @RequestParam("category") String category){

        User user = userService.findById(id);
        UserDetail userDetail = new UserDetail(user);
        userDetail.addCategory(new Category(category));
        userDetailService.saveUserDetail(userDetail);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> getUserList(){

        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserLoginInfo(@PathVariable("id") Long id){

        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/profile/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserInfo(@PathVariable("id") Long id){

        UserDetail userDetail = userDetailService.findByUserId(id);
        return new ResponseEntity<>(userDetail, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{userId}/categories", method = RequestMethod.GET)
    public Set<Category> getCategoriesByUserId(@PathVariable("userId") Long userId){

        UserDetail user = userDetailService.findByUserId(userId);
        return user.getCategories();
    }

    @RequestMapping(value = "/user/{userId}/{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<?> getQuestionWithAnswer(@PathVariable("userId") Long userId,
                                      @PathVariable("categoryId") Long categoryId){

        List<UserAnswer> userAnswer = userAnswerService.findByUserIdAndCategoryId(userId, categoryId);

        return new ResponseEntity<>(userAnswer, HttpStatus.OK);
    }

}