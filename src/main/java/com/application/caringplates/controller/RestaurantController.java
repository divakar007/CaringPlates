package com.application.caringplates.controller;
import com.application.caringplates.dto.RestaurantDTO;
import com.application.caringplates.models.Restaurant;
import com.application.caringplates.models.User;
import com.application.caringplates.service.RestaurantService;
import com.application.caringplates.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/restaurant")

public class RestaurantController {
    @Autowired
    public RestaurantService restaurantService;

    @Autowired
    public UserService userService;

    @PostMapping(value = "/create-profile")
    public ResponseEntity<Restaurant> restaurantRegistration(@RequestBody RestaurantDTO restaurantDTO){
        Restaurant restaurant = restaurantService.saveRestaurant(restaurantDTO);
        if(restaurant!=null){
            return ResponseEntity.ok(restaurant);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping( value = "/fetch-profile/{userId}")
    public ResponseEntity<Restaurant> fetchProfile(@PathVariable("userId") Long userId){
        User user = userService.findById(userId);
        Restaurant restaurant = restaurantService.fetchUserById(user);
        return ResponseEntity.ok().body(restaurant);
    }
}
