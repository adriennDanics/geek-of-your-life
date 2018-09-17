package controllers;

import com.codecool.geek.api.UserApi;
import com.codecool.geek.model.customer.User;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserApiTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserApi userApi;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(userApi).build();
    }

    @Test
    public void testGetUserLoginInfo() throws Exception {
        Long id = 1L;
        User testUser = new User("testuser@test.com", "test");
        testUser.setId(id);

        when(userService.findById(id)).thenReturn(testUser);

        mockMvc.perform(get("/user/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.email").value("testuser@test.com"))
                .andExpect(jsonPath("$.password").value("test"));
        verify(userService, times(1)).findById(1L);
        verifyNoMoreInteractions(userService);
    }
}
