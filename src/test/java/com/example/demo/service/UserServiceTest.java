package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void testSaveUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("John");

        User userEntity = new User();
        userEntity.setName("John");

        // 1️⃣ mock mapping UserDTO -> User
        when(modelMapper.map(userDTO, User.class)).thenReturn(userEntity);

        // 2️⃣ mock repository save (must return User, not DTO!)
        when(userRepository.save(userEntity)).thenReturn(userEntity);

        // 3️⃣ mock mapping User -> UserDTO
        when(modelMapper.map(userEntity, UserDTO.class)).thenReturn(userDTO);

        UserDTO savedUser = userService.saveUser(userDTO);

        assertNotNull(savedUser);
        assertEquals("John", savedUser.getName());
    }


    @Test
    void testGetAllUsers() {
        User userEntity = new User();
        userEntity.setName("Alice");

        UserDTO userDTO = new UserDTO();
        userDTO.setName("Alice");

        // mock repo + mapper
        when(userRepository.findAll()).thenReturn(Arrays.asList(userEntity));
        when(modelMapper.map(userEntity, UserDTO.class)).thenReturn(userDTO);

        List<UserDTO> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals("Alice", users.get(0).getName()); // ✅ no more NPE
    }
}
