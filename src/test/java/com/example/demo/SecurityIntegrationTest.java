package com.example.demo;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    private String userToken;
    private String adminToken;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();

        User user = new User();
        user.setName("Test User");
        user.setEmail("user@example.com");
        user.setPassword(passwordEncoder.encode("password"));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);

        User admin = new User();
        admin.setName("Admin User");
        admin.setEmail("admin@example.com");
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setRole(Role.ROLE_ADMIN);
        userRepository.save(admin);

        userToken = jwtUtil.generateToken(user.getEmail(), List.of("ROLE_USER"));
        adminToken = jwtUtil.generateToken(admin.getEmail(), List.of("ROLE_ADMIN"));

    }

    @Test
    void userCanAccessBooks() throws Exception {
        System.out.println("User Token: " + userToken);

        mockMvc.perform(get("/books")
                .header("Authorization", "Bearer " + userToken)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void userCannotAccessAdmin() throws Exception {
        System.out.println("User Token: " + userToken);

        mockMvc.perform(get("/admin/dashboard")
                .header("Authorization", "Bearer " + userToken))
                .andExpect(status().isForbidden());
    }

    @Test
    void adminCanAccessBooks() throws Exception {
        System.out.println("Admin Token: " + adminToken);

        mockMvc.perform(get("/books")
                .header("Authorization", "Bearer " + adminToken))
                .andExpect(status().isOk());
    }

    @Test
    void adminCanAccessAdminEndpoints() throws Exception {
        System.out.println("Admin Token: " + adminToken);
        mockMvc.perform(get("/admin/dashboard")
                .header("Authorization", "Bearer " + adminToken))
                .andExpect(status().isOk());
    }
}
