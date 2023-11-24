package com.application.caringplates.service;
import com.application.caringplates.models.Post;
import com.application.caringplates.dto.PostDTO;
import com.application.caringplates.models.Restaurant;
import com.application.caringplates.models.User;
import com.application.caringplates.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

}
