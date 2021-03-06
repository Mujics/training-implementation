package wolox.bootstrap.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import wolox.bootstrap.models.User;
import wolox.bootstrap.repositories.UserRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebAppConfiguration
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void givenAddedUsers_whenGetUsers_thenReturnFullList() throws Exception {
        User user1 = new User();
        user1.setUsername("username");
        user1.setName("name");
        user1.setBirthday(LocalDate.of(1997, 5, 23));
        List expectedUsers = Arrays.asList(user1);
        given(userRepository.findAll()).willReturn(expectedUsers);
        mvc.perform(get("/api/users/view")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(user1.getName())));
    }

    @Test
    public void givenAddedBooks_whenGetBooks_thenReturnFullList() throws Exception {
        User user1 = new User();
        user1.setUsername("username");
        user1.setName("name");
        user1.setBirthday(LocalDate.of(1997, 5, 23));
        List expectedUsers = Arrays.asList(user1);
        given(userRepository.findAll()).willReturn(expectedUsers);
        String book = "{\"author\": \"author\",\"title\": \"title\",\"subtitle\": \"subtitle\"," +
                "\"genre\": \"genre\", \"isbn\": \"isbn\",\"pages\": 5,\"image\": \"image\",\"publisher\": " +
                "\"publisher\",\"year\": \"year\" }";
        given(userRepository.findById(1)).willReturn(Optional.ofNullable(user1));
        mvc.perform(put("/api/users/addBook/1")
                .contentType(MediaType.APPLICATION_JSON).content(book))
                .andExpect(status().isOk());
        mvc.perform(get("/api/users/view/1/library")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("title")));
    }
}
