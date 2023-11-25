package com.application.caringplates.service;
import com.application.caringplates.models.Restaurant;
import com.application.caringplates.dto.RestaurantDTO;
import com.application.caringplates.models.User;
import com.application.caringplates.repository.RestaurantRepository;
import jakarta.jws.soap.SOAPBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    @Autowired
    public RestaurantRepository restaurantRepository;
    @Autowired
    public UserService userService;

    public Restaurant saveRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant(restaurantDTO);
        User user = userService.findById(restaurantDTO.getUser());
        restaurant.setUser(user);
        return restaurantRepository.saveAndFlush(restaurant);
    }

    public Restaurant fetchUserById(User user){
        return restaurantRepository.findRestaurantByUserIs(user);
    }
}
