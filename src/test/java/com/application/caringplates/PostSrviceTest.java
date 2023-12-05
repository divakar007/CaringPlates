package com.application.caringplates;

import com.application.caringplates.dto.PostDTO;
import com.application.caringplates.models.Post;
import com.application.caringplates.models.Restaurant;
import com.application.caringplates.models.User;
import com.application.caringplates.repository.PostRepository;
import com.application.caringplates.service.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserService userService;

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private NotificationService notificationService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private PostService postService;

    @Test
    void savePost_ValidPostDTO_ReturnsSavedPost() {
        // Arrange
        PostDTO postDTO = new PostDTO(/* fill with necessary parameters */);
        User user = new User(/* fill with necessary parameters */);

        // Stubbing the service methods
        when(userService.findById(postDTO.getUserId())).thenReturn(user);
        when(restaurantService.fetchUserById(user)).thenReturn(new Restaurant());
        when(postRepository.saveAndFlush(any(Post.class))).thenReturn(new Post());

        // Act
        Post savedPost = postService.savePost(postDTO);

        // Assert
        verify(userService, times(1)).findById(postDTO.getUserId());
        verify(restaurantService, times(1)).fetchUserById(user);
        verify(postRepository, times(1)).saveAndFlush(any(Post.class));
    }

}
