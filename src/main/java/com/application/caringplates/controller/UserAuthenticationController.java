package com.application.caringplates.controller;

import com.application.caringplates.dto.UserDTO;
import com.application.caringplates.dto.loginDTO;
import com.application.caringplates.models.User;
import com.application.caringplates.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class UserAuthenticationController {

    @Autowired
    public UserService userService;
    @GetMapping("/user")
    public ResponseEntity<String> getCurrentUser() {
        return new ResponseEntity<>("Current user: Divakar", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody loginDTO loginDTO){
        User user =  userService.login(loginDTO.getEmail(), loginDTO.getPassword());
        if(user!=null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/register")
    public ResponseEntity<User> userRegistration(@RequestBody UserDTO userDTO){
        User user = userService.saveUser(userDTO);
        if(user!=null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().body(null);
    }
}
