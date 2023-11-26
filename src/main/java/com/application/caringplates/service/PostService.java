package com.application.caringplates.service;
import com.application.caringplates.models.Post;
import com.application.caringplates.dto.PostDTO;
import com.application.caringplates.models.User;
import com.application.caringplates.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {
    @Autowired
    public PostRepository postRepository;
    @Autowired
    public UserService userService;

    @Autowired
    public NotificationService notificationService;


    private final String USER_NOTIFICATION_MESSAGE = "Hi %s your claim is successful and it has been notified to the restaurant. Please pickup the order by %s.";
    private final String RESTAURANT_NOTIFICATION_MESSAGE = "Your order is claimed by user %s and it will be picked up shortly.";
    public Post savePost(PostDTO postDTO) {
        Post post = new Post(postDTO);
        User user = userService.findById(postDTO.getUserId());
        //Restaurant restaurant = restaurantService.findBy(postDTO.getRestaurantId());
        post.setUser(user);
       // post.setRestaurant(restaurant);
        return postRepository.saveAndFlush(post);
    }

    public List<Post> fetchAll(){
        return postRepository.findAll();
    }

    public List<Post> fetchAllBeforePickupDateAndNotClaimed(){
        return postRepository.findAllByBestBeforeGreaterThanAndClaimedIsFalse(new Date());
    }
    public List<Post> findAllByBestBeforeBeforeAndClaimedFalse(){
        return postRepository.findAllByBestBeforeBeforeAndClaimedFalse(new Date());
    }

    public void claimPost(User user, PostDTO postDTO){
        postRepository.markPostAsClaimed(postDTO.getPostId());
        String userNotificationMessage = String.format(USER_NOTIFICATION_MESSAGE,user.getFirstName(),postDTO.getBestBefore().toString());
        String restaurantNotificationMessage = String.format(RESTAURANT_NOTIFICATION_MESSAGE,user.getFirstName());

        notificationService.addNotificationForUser(userNotificationMessage,user.getUserId());
        notificationService.addNotificationForRestaurant(restaurantNotificationMessage,postDTO.getRestaurantId());
    }
}
