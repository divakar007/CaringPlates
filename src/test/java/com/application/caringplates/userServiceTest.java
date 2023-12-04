package com.application.caringplates;

import com.application.caringplates.controller.UserAuthenticationController;
import com.application.caringplates.dto.UserDTO;
import com.application.caringplates.dto.loginDTO;
import com.application.caringplates.models.User;
import com.application.caringplates.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserAuthenticationControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserAuthenticationController userAuthenticationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin() {
        loginDTO loginDTO = new loginDTO("test@example.com", "password");
        UserDTO userDTO = new UserDTO();
        when(userService.login(loginDTO.getEmail(), loginDTO.getPassword())).thenReturn(userDTO);

        ResponseEntity<?> responseEntity = userAuthenticationController.login(loginDTO);

        assertEquals(ResponseEntity.ok(userDTO), responseEntity);
        verify(userService, times(1)).login(loginDTO.getEmail(), loginDTO.getPassword());
    }

    @Test
    void testLoginFailure() {
        loginDTO loginDTO = new loginDTO("invalid@example.com", "wrongpassword");
        when(userService.login(loginDTO.getEmail(), loginDTO.getPassword())).thenReturn(null);

        ResponseEntity<?> responseEntity = userAuthenticationController.login(loginDTO);

        assertEquals(ResponseEntity.badRequest().body(null), responseEntity);
        verify(userService, times(1)).login(loginDTO.getEmail(), loginDTO.getPassword());
    }

    @Test
    void testUserRegistration() {
        UserDTO userDTO = new UserDTO("John", "Doe", "john.doe@example.com",
                "password", "USER", "1234567890", new Date(), "Male");
        User user = new User(userDTO);
        when(userService.saveUser(userDTO)).thenReturn(user);

        ResponseEntity<User> responseEntity = userAuthenticationController.userRegistration(userDTO);

        assertEquals(ResponseEntity.ok(user), responseEntity);
        verify(userService, times(1)).saveUser(userDTO);
    }

    @Test
    void testUserRegistrationFailure() {
        UserDTO userDTO = new UserDTO("Invalid", "User", "invalid.email@example.com",
                "password", "USER", "9876543210", new Date(), "Female");
        when(userService.saveUser(userDTO)).thenReturn(null);

        ResponseEntity<User> responseEntity = userAuthenticationController.userRegistration(userDTO);

        assertEquals(ResponseEntity.badRequest().body(null), responseEntity);
        verify(userService, times(1)).saveUser(userDTO);
    }
}
