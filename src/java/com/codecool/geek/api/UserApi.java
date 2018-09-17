package com.codecool.geek.api;

import com.codecool.geek.model.customer.User;
import com.codecool.geek.model.customer.UserDetail;
import com.codecool.geek.model.questionnaire.Category;
import com.codecool.geek.service.CategoryService;
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
        return new ResponseEntity<>(users, HttpStatus.OK);
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

        UserDetail user = userDetailService.findByUserId(userId);
        Set<Category> categories = user.getCategories();

        for (Category cat : categories) {
            if (cat.getId().equals(categoryId)) {
                return new ResponseEntity<>(cat, HttpStatus.OK);
            }
        }


        return new ResponseEntity<>("I am a TEAPOT, you're an idiot",HttpStatus.I_AM_A_TEAPOT);


    }


}
