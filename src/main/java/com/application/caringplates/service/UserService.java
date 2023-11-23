package com.application.caringplates.service;

import com.application.caringplates.models.User;
import com.application.caringplates.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    public User login(String email, String password){
        User user = userRepository.findByEmailId(email);
        if(user.getPassword().equals(password)){
            return user;
        }
        return null;
    }
}
