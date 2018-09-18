package controllers;

import com.codecool.geek.api.UserApi;
import com.codecool.geek.model.customer.User;
import com.codecool.geek.model.customer.UserDetail;
import com.codecool.geek.model.questionnaire.Category;
import com.codecool.geek.service.UserDetailService;
import com.codecool.geek.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserApiTest {
    @Mock
    private UserService userService;

    @Mock
    private UserDetailService userDetailService;

    @InjectMocks
    private UserApi userApi;

    private MockMvc mockMvc;
    private Long id;
    private User testUser;
    private List<User> testUserList = new ArrayList<>();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(userApi).build();

        id = 1L;
        testUser = new User("testuser@test.com", "test");
        testUser.setId(id);
        testUserList.add(testUser);

        User newTestUser = new User("testuser2@test.com", "test2");
        newTestUser.setId(2L);
        testUserList.add(newTestUser);

    }

    //TODO: "/user"
    @Test
    public void testCreateNewUser() {

    }

    //TODO: "/user/profile/{id}"
    @Test
    public void testcreateNewUserProfile() {

    }

    //TODO: "/users"
    @ Test
    public void testGetUserList() throws Exception{
        when(userService.getAllUsers()).thenReturn(testUserList);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].email", is("testuser@test.com")))
                .andExpect(jsonPath("$[0].password", is("test")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].email", is("testuser2@test.com")))
                .andExpect(jsonPath("$[1].password", is("test2")));


        verify(userService, times(1)).getAllUsers();
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testGetUserLoginInfo() throws Exception {
        when(userService.findById(id)).thenReturn(testUser);

        mockMvc.perform(get("/user/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.email").value("testuser@test.com"))
                .andExpect(jsonPath("$.password").value("test"));
        verify(userService, times(1)).findById(1L);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testGetUserInfo() throws Exception {

        UserDetail testUserDetail = new UserDetail(testUser);
        testUserDetail.setFullName("Test test");
        testUserDetail.setNickName("Test");

        when(userDetailService.findByUserId(id)).thenReturn(testUserDetail);

        mockMvc.perform(get("/user/profile/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.fullName").value("Test test"))
                .andExpect(jsonPath("$.nickName").value("Test"));
        verify(userDetailService, times(1)).findByUserId(1L);
        verifyNoMoreInteractions(userService);
    }


    //TODO: "/user/{userId}/categories"
    @Test
    public void testGetCategoriesByUserId() {

    }

    //TODO: "/user/{userId}/{categoryId}"
    @Test
    public void testGetQuestionWithAnswer() {

    }


}
