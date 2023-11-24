package com.application.caringplates.controller;
import com.application.caringplates.dto.PostDTO;
import com.application.caringplates.models.Post;
import com.application.caringplates.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    public PostService postService;
    @PostMapping("/create-post")
    public ResponseEntity<Post> userRegistration(@RequestBody PostDTO postDTO){
        Post post = postService.savePost(postDTO);
        if(post!=null){
            return ResponseEntity.ok(post);
        }
        return ResponseEntity.badRequest().body(null);
    }
}
