package com.application.caringplates;


import com.application.caringplates.controller.RestaurantController;
        import com.application.caringplates.dto.RestaurantDTO;
        import com.application.caringplates.models.Restaurant;
        import com.application.caringplates.models.User;
        import com.application.caringplates.service.RestaurantService;
        import com.application.caringplates.service.UserService;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.MockitoAnnotations;
        import org.springframework.http.ResponseEntity;

        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.mockito.Mockito.*;

class RestaurantControllerTest {

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private UserService userService;

    @InjectMocks
    private RestaurantController restaurantController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRestaurantRegistration() {
        RestaurantDTO restaurantDTO = new RestaurantDTO("Restaurant Name", "Description", "Location","","","");
        Restaurant restaurant = new Restaurant(restaurantDTO);
        when(restaurantService.saveRestaurant(restaurantDTO)).thenReturn(restaurant);

        ResponseEntity<Restaurant> responseEntity = restaurantController.restaurantRegistration(restaurantDTO);

        assertEquals(ResponseEntity.ok(restaurant), responseEntity);
        verify(restaurantService, times(1)).saveRestaurant(restaurantDTO);
    }

    @Test
    void testRestaurantRegistrationFailure() {
        RestaurantDTO restaurantDTO = new RestaurantDTO("Invalid Restaurant", "Invalid Description", "Invalid Location","","","");
        when(restaurantService.saveRestaurant(restaurantDTO)).thenReturn(null);

        ResponseEntity<Restaurant> responseEntity = restaurantController.restaurantRegistration(restaurantDTO);

        assertEquals(ResponseEntity.badRequest().body(null), responseEntity);
        verify(restaurantService, times(1)).saveRestaurant(restaurantDTO);
    }

    @Test
    void testFetchProfile() {
        Long userId = 1L;
        User user = new User();
        when(userService.findById(userId)).thenReturn(user);

        Restaurant restaurant = new Restaurant();
        when(restaurantService.fetchUserById(user)).thenReturn(restaurant);

        ResponseEntity<Restaurant> responseEntity = restaurantController.fetchProfile(userId);

        assertEquals(ResponseEntity.ok().body(restaurant), responseEntity);
        verify(userService, times(1)).findById(userId);
        verify(restaurantService, times(1)).fetchUserById(user);
    }
}
