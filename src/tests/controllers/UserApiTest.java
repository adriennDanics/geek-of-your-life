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

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(userApi).build();

        id = 1L;
        testUser = new User("testuser@test.com", "test");
        testUser.setId(id);
    }

    @Test
    public void testCreateNewUser() throws Exception {
        mockMvc.perform(post("/user")
                .param("password", testUser.getPassword())
                .param("email", testUser.getEmail()))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Success"));;

    }

    //TODO: "/user/profile/{id}"
    @Test
    public void testCreateNewUserProfile() {

    }

    //TODO: "/users"
    @Test
    public void testGetUserList() {

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
