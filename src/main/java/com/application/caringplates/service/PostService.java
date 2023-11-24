package com.application.caringplates.service;
import com.application.caringplates.models.Post;
import com.application.caringplates.dto.PostDTO;
import com.application.caringplates.models.Restaurant;
import com.application.caringplates.models.User;
import com.application.caringplates.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {
    @Autowired
    public PostRepository postRepository;
    @Autowired
    public UserService userService;
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

}
