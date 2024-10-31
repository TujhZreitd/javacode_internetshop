package javacode.javacode_internetshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javacode.javacode_internetshop.model.Order;
import javacode.javacode_internetshop.model.User;
import javacode.javacode_internetshop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    }

    @Test
    void testGetAllUsers_UserSummaryView() throws Exception {
        User firstUser = new User(1, "Egor", "egor@mail.ru", null);
        User secondUser = new User(2, "Anton", "anton@mail.ru", null);
        List<User> users = Arrays.asList(firstUser, secondUser);

        Mockito.when(userService.findAll()).thenReturn(users);

        mockMvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Egor"))
                .andExpect(jsonPath("$[0].email").value("egor@mail.ru"))
                .andExpect(jsonPath("$[0].orders").doesNotExist())
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Anton"))
                .andExpect(jsonPath("$[1].email").value("anton@mail.ru"))
                .andExpect(jsonPath("$[1].orders").doesNotExist());

    }

    @Test
    void testGetUserById_UserDetailsView() throws Exception {
        User user = new User(1, "Egor", "egor@mail.ru", null);
        Order firstOrder = new Order(1, user, "Laptop", new BigDecimal(1200), "SHIPPED");
        Order secondOrder = new Order(2, user, "Phone", new BigDecimal(700), "PAID");
        Order thirdOrder = new Order(3, user, "Monitor", new BigDecimal(900), "SHIPPED");
        user.setOrders(List.of(firstOrder, secondOrder, thirdOrder));

        Mockito.when(userService.findById(1)).thenReturn(user);

        mockMvc.perform(get("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Egor"))
                .andExpect(jsonPath("$.email").value("egor@mail.ru"))
                .andExpect(jsonPath("$.orders[0].id").value(1))
                .andExpect(jsonPath("$.orders[0].name").value("Laptop"))
                .andExpect(jsonPath("$.orders[0].amount").value(1200))
                .andExpect(jsonPath("$.orders[0].status").value("SHIPPED"))
                .andExpect(jsonPath("$.orders[1].id").value(2))
                .andExpect(jsonPath("$.orders[1].name").value("Phone"))
                .andExpect(jsonPath("$.orders[1].amount").value(700))
                .andExpect(jsonPath("$.orders[1].status").value("PAID"))
                .andExpect(jsonPath("$.orders[2].id").value(3))
                .andExpect(jsonPath("$.orders[2].name").value("Monitor"))
                .andExpect(jsonPath("$.orders[2].amount").value(900))
                .andExpect(jsonPath("$.orders[2].status").value("SHIPPED"));
    }

    @Test
    void testDeleteUser() throws Exception {
        Mockito.doNothing().when(userService).deleteById(1);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk());
    }
}