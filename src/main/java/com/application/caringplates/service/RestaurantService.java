package com.application.caringplates.service;
import com.application.caringplates.models.Restaurant;
import com.application.caringplates.dto.RestaurantDTO;
import com.application.caringplates.models.User;
import com.application.caringplates.repository.RestaurantRepository;
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
        //Restaurant restaurant = restaurantService.findBy(postDTO.getRestaurantId());
        restaurant.setUser(user);
        // post.setRestaurant(restaurant);
        return restaurantRepository.saveAndFlush(restaurant);
    }

}
