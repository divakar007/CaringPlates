package com.application.caringplates.controller;
import com.application.caringplates.dto.PostDTO;
import com.application.caringplates.models.Post;
import com.application.caringplates.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    public PostService postService;

    @PostMapping(value = "/create-post", consumes = "multipart/form-data" )
    public ResponseEntity<Post> userRegistration(@ModelAttribute PostDTO postDTO){
        Post post = postService.savePost(postDTO);
        if(post!=null){
            return ResponseEntity.ok(post);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping(value = "/fetch-posts")
    public ResponseEntity<List<Post>> fetchPosts(){
        List<Post> postsList = postService.postRepository.findAll();
        postsList.stream()
                .filter(post -> post.getBestBefore().after(new Date()) && !post.getClaimed())
                .collect(Collectors.toList());
        return ResponseEntity.ok(postsList);
    }

}
