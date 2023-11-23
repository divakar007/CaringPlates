package com.application.caringplates.controller;

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
    public ResponseEntity<User> login(@RequestBody loginDTO userDTO){

        return ResponseEntity.ok(userService.login(userDTO.getEmail(), userDTO.getPassword()));
    }
    @GetMapping("/user1")
    public ResponseEntity<String> user(){
        return new ResponseEntity<>("Current user: Divakar", HttpStatus.OK);
    }

}
