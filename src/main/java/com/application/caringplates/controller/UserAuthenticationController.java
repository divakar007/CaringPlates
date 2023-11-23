package com.application.caringplates.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserAuthenticationController {

    @GetMapping("/user")
    public ResponseEntity<String> getCurrentUser() {
        return new ResponseEntity<>("Current user: Divakar", HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(){
        return new ResponseEntity<>("Current user: Divakar", HttpStatus.OK);
    }
    @GetMapping("/user1")
    public ResponseEntity<String> user(){
        return new ResponseEntity<>("Current user: Divakar", HttpStatus.OK);
    }

}
