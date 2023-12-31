package com.application.caringplates.controller;
import com.application.caringplates.dto.PostDTO;
import com.application.caringplates.models.Post;
import com.application.caringplates.service.NotificationService;
import com.application.caringplates.service.PostService;
import com.application.caringplates.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    public PostService postService;

    @Autowired
    public UserService userService;

    @Autowired
    public NotificationService notificationService;
    @PostMapping(value = "/create-post", consumes = "multipart/form-data" )
    public ResponseEntity<Post> createPost(@ModelAttribute PostDTO postDTO){
        Post post = postService.savePost(postDTO);
        if(post!=null){
            return ResponseEntity.ok(post);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping(value = "/fetch-posts")
    public ResponseEntity<List<Post>> fetchPosts(){
        List<Post> postsList = postService.postRepository.findAll();
        List<Post> filteredPosts = postsList.stream()
                .filter(post -> post.getBestBefore().toInstant().isAfter(Instant.now()) && !post.getClaimed())
                .collect(Collectors.toList());
        return ResponseEntity.ok(filteredPosts);
    }

    @PostMapping(value = "/claim-post/{userId}", consumes = "multipart/form-data")
    public ResponseEntity<?> claimPost(@ModelAttribute PostDTO postDTO, @PathVariable Long userId){
        try{
            postService.claimPost(userService.findById(userId), postDTO);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            return ResponseEntity.ok(false);
        }
    }
    @GetMapping("/rewards/{userId}")
    public ResponseEntity<Integer> getRewardPoints(@PathVariable("userId") long userId){
        Integer points = notificationService.getRewardPoints(userId);
        return ResponseEntity.ok(points);

    }
}
