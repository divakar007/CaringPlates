import com.application.caringplates.controller.PostController;
import com.application.caringplates.dto.PostDTO;
import com.application.caringplates.models.Post;
import com.application.caringplates.service.NotificationService;
import com.application.caringplates.service.PostService;
import com.application.caringplates.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PostControllerTest {

    @Mock
    private PostService postService;

    @Mock
    private UserService userService;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private PostController postController;

    @Test
    void createPost_ValidPostDTO_ReturnsOkResponse() throws IOException, ParseException {
        // Arrange
        PostDTO postDTO = new PostDTO();

        // Stubbing the service method
        when(postService.savePost(postDTO)).thenReturn(new Post());

        // Act
        ResponseEntity<Post> response = postController.createPost(postDTO);

        // Assert
        assertEquals(ResponseEntity.ok().build(), response);
        verify(postService, times(1)).savePost(postDTO);
    }

}
