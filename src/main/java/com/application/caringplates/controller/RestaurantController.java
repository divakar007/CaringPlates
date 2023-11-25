package com.application.caringplates.controller;
import com.application.caringplates.dto.PostDTO;
import com.application.caringplates.dto.RestaurantDTO;
import com.application.caringplates.models.Post;
import com.application.caringplates.models.Restaurant;
import com.application.caringplates.service.PostService;
import com.application.caringplates.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/restaurant-prof")

public class RestaurantController {
    @Autowired
    public RestaurantService restaurantService;

    @PostMapping(value = "/create-profile")
    public ResponseEntity<Restaurant> restaurantRegistration(@ModelAttribute RestaurantDTO restaurantDTO){
        Restaurant restaurant = restaurantService.saveRestaurant(restaurantDTO);
        if(restaurant!=null){
            return ResponseEntity.ok(restaurant);
        }
        return ResponseEntity.badRequest().body(null);
    }
}
