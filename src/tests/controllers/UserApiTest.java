package controllers;

import com.codecool.geek.api.UserApi;
import com.codecool.geek.model.customer.Gender;
import com.codecool.geek.model.customer.User;
import com.codecool.geek.model.customer.UserDetail;
import com.codecool.geek.model.questionnaire.Category;
import com.codecool.geek.service.CategoryService;
import com.codecool.geek.service.UserDetailService;
import com.codecool.geek.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserApiTest {

    @Mock
    private UserService userService;

    @Mock
    private UserDetailService userDetailService;

    @Mock
    private CategoryService categoryService;

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

    @Test
    public void testCreateNewUser() throws Exception {

        doNothing().when(userService).saveUser(Matchers.any());

        mockMvc.perform(post("/user")
                .param("password", testUser.getPassword())
                .param("email", testUser.getEmail()))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Success"));


        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);

        verify(userService, times(1)).saveUser(argument.capture());
        verifyNoMoreInteractions(userService);

    }

    @Test
    public void testCreateNewUserProfile() throws Exception {
        UserDetail userDetail = new UserDetail(testUser);
        Category category = new Category("Sci-fi");
        Set<Category> categorySet = new HashSet<>();
        categorySet.add(category);
        userDetail.setCategories(categorySet);

        mockMvc.perform(post("/user/profile/{id}", 1)
                .param("category", category.getCategory()))
                .andExpect(status().isOk())
                .andExpect(content().string("Success"));

        ArgumentCaptor<UserDetail> customerCaptor = ArgumentCaptor.forClass(UserDetail.class);
        verify(userDetailService).saveUserDetail(customerCaptor.capture());

        UserDetail boundCustomer = customerCaptor.getValue();

        assertEquals(categorySet.toString(), boundCustomer.getCategories().toString());
    }

    @ Test
    public void testGetUserList() throws Exception {
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

        Date date = new Date();
        Set<Category> categories = new HashSet();
        categories.add(new Category("fantastic"));

        UserDetail testUserDetail = new UserDetail(testUser);
        testUserDetail.setFullName("Test test");
        testUserDetail.setNickName("Test");
        testUserDetail.setBirthDate(date);
        testUserDetail.setCategories(categories);
        testUserDetail.setGender(Gender.OTHER);
        testUserDetail.setProfileImage("apple.jpg");
        testUserDetail.setShortDescription("A lot of apples");
        testUserDetail.setUser(testUser);

        when(userDetailService.findByUserId(id)).thenReturn(testUserDetail);

        mockMvc.perform(get("/user/profile/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.fullName").value("Test test"))
                .andExpect(jsonPath("$.nickName").value("Test"));

        verify(userDetailService, times(1)).findByUserId(1L);
        verifyNoMoreInteractions(userDetailService);
    }

    
    @Test
    public void testGetCategoriesByUserId() throws Exception {

        Set<Category> categories = new HashSet();
        categories.add(new Category("fantastic"));

        UserDetail testUserDetail = new UserDetail(testUser);
        testUserDetail.setCategories(categories);

        when(userDetailService.findByUserId(id)).thenReturn(testUserDetail);

        mockMvc.perform(get("/user/{userId}/categories", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].category").value("fantastic"));

        verify(userDetailService, times(1)).findByUserId(id);
        verifyNoMoreInteractions(userDetailService);
    }

    //TODO: "/user/{userId}/{categoryId}"
    @Test
    public void testGetQuestionWithAnswer() {

    }


}
