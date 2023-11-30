package com.application.caringplates.service;

import com.application.caringplates.dto.UserDTO;
import com.application.caringplates.models.Restaurant;
import com.application.caringplates.models.User;
import com.application.caringplates.repository.RestaurantRepository;
import com.application.caringplates.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public RestaurantRepository restaurantRepository;

    public UserDTO login(String email, String password){
        User user = userRepository.findByEmailId(email);
        if(user.getPassword().equals(password)){
            UserDTO userDTO = new UserDTO(user);
            Restaurant restaurant = restaurantRepository.findRestaurantByUserIs(user);
            if(restaurant!=null){
                userDTO.setRestaurantDTO(restaurant.getDTO());
            }
            return userDTO;
        }
        return null;
    }

    public User saveUser(UserDTO userDTO) {
        User user = new User(userDTO);
        return userRepository.saveAndFlush(user);
    }
    public User findById(Long userId){
        return userRepository.findByUserId(userId);
    }
}
