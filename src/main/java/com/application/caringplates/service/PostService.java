package com.application.caringplates.service;
import com.application.caringplates.models.Post;
import com.application.caringplates.dto.PostDTO;
import com.application.caringplates.models.Restaurant;
import com.application.caringplates.models.User;
import com.application.caringplates.repository.PostRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class PostService {
    @Autowired
    public PostRepository postRepository;
    @Autowired
    public UserService userService;
    @Autowired
    public RestaurantService restaurantService;

    @Autowired
    public NotificationService notificationService;

    @Autowired
    EmailService emailService;


    private final String USER_NOTIFICATION_MESSAGE = "Hi %s your claim is successful and it has been notified to the restaurant. Please pickup the order by %s.";
    private final String RESTAURANT_NOTIFICATION_MESSAGE = "Your order is claimed by user %s and it will be picked up shortly.";

    public Post savePost(PostDTO postDTO) {
        Post post = new Post(postDTO);
        User user = userService.findById(postDTO.getUserId());
        Restaurant restaurant = restaurantService.fetchUserById(user);
        post.setUser(user);
        post.setRestaurant(restaurant);
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
        User restUser = userService.findById(postDTO.getUserId());
        Restaurant restaurant = restaurantService.fetchUserById(restUser);
        notificationService.createNotification(user.getUserId(),postDTO.getPostId(),userNotificationMessage);
        notificationService.createNotification(postDTO.getUserId(),postDTO.getPostId(),restaurantNotificationMessage);
        String subject = "";
        sendOrderConfirmationEmail(user.getEmailId(),subject,postDTO.getItemName(),postDTO.getDescription(),postDTO.getQuantity(),"https://www.google.com/maps/place/Bansari+Indian+Cuisine/@38.8787513,-77.2305862,17z/data=!3m1!4b1!4m6!3m5!1s0x89b64b4df843cf2f:0x8c5720ed7a6e20f9!8m2!3d38.8787513!4d-77.2280113!16s%2Fg%2F11jcl_07b7?entry=ttu", String.valueOf( new Random().nextInt(1000000)), user.getFirstName(),user.getPhoneNumber());
        sendOrderConfirmationEmail(restUser.getEmailId(),subject,postDTO.getItemName(),postDTO.getDescription(),postDTO.getQuantity(),"https://www.google.com/maps/place/Bansari+Indian+Cuisine/@38.8787513,-77.2305862,17z/data=!3m1!4b1!4m6!3m5!1s0x89b64b4df843cf2f:0x8c5720ed7a6e20f9!8m2!3d38.8787513!4d-77.2280113!16s%2Fg%2F11jcl_07b7?entry=ttu", String.valueOf( new Random().nextInt(1000000)), user.getFirstName(),user.getPhoneNumber());

    }

    public void sendOrderConfirmationEmail(String to, String subject, String productName, String description, int quantity, String restaurantAddress, String orderID, String nameOfTheUser, String phoneOfTheUser) {
        try {
            MimeMessage message = emailService.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);

            String htmlContent = getHtmlContent(productName, description, quantity, restaurantAddress, orderID, nameOfTheUser);
            helper.setText(htmlContent, true);

            emailService.send(message);
            htmlContent = getRestaurantHtmlContent(productName,description,quantity,restaurantAddress, nameOfTheUser,phoneOfTheUser);
            emailService.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String getHtmlContent(String productName, String description, int quantity, String restaurantAddress, String orderID, String nameOfTheUser) {
        String htmlContent = """
                    <!DOCTYPE html>
                    <html lang="en">
                    <head>
                        <meta charset="UTF-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <title>Order Claim Confirmation</title>
                    </head>
                    <body style="font-family: Arial, sans-serif;">

                        <div style="max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; border-radius: 8px;">

                            <h2 style="color: #333;">Order Claim Confirmation</h2>

                            <p>Congratulations! Your order has been successfully claimed. We appreciate your business and hope you enjoy your meal.</p>

                            <h3>Order Details:</h3>
                            <ul>
                                <li><strong>Name:</strong> %s</li>
                                <li><strong>Description:</strong> %s</li>
                                <li><strong>Quantity:</strong> %d</li>
                                <li><strong>Restaurant Location:</strong> %s</li>
                            </ul>

                            <img src="%s" alt="Restaurant Location" style="max-width: 100%; border-radius: 8px; margin-bottom: 15px;">

                            <p>Please review the details above to ensure accuracy. If you have any questions or concerns regarding your order, feel free to reach out to our customer support team at %s.</p>

                            <p>Thank you for choosing Caring Plates! We appreciate your trust in our service.</p>

                            <p>Best regards,<br>
                            Caring Plates<br>
                            phone: +1 571 591 8792</p>        
                        </div>

                    </body>
                    </html>
                """.formatted(productName, description, quantity, restaurantAddress, orderID);
        return htmlContent;
    }
    private String getRestaurantHtmlContent(String productName, String description, int quantity, String restaurantAddress, String supportContact, String pickupPersonName) {
        // Replace placeholders with actual values
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta http-equiv="X-UA-Compatible" content="IE=edge">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Order Claim Confirmation</title>
                </head>
                <body style="font-family: Arial, sans-serif;">

                    <div style="max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; border-radius: 8px;">

                        <h2 style="color: #333;">Order Claim Confirmation</h2>

                        <p>Good news! An order has been claimed. Here are the details:</p>

                        <h3>Order Details:</h3>
                        <ul>
                            <li><strong>Name:</strong> %s</li>
                            <li><strong>Description:</strong> %s</li>
                            <li><strong>Quantity:</strong> %d</li>
                            <li><strong>Restaurant Location:</strong> %s</li>
                        </ul>

                        <h3>Pickup Person Details:</h3>
                        <ul>
                            <li><strong>Name:</strong> %s</li>
                            <li><strong>Contact:</strong> %s</li>
                        </ul>

                        <p>Please coordinate with the pickup person for the order handover. If you have any questions or concerns, feel free to contact our support team at +1 571 233 2323.</p>

                        <p>Thank you for using Caring Plates!</p>

                        <p>Best regards,<br>
                        Caring Plates<br>
                        Phone : +1 571 591 8936</p>

                    </div>

                </body>
                </html>
                """.formatted(productName, description, quantity, restaurantAddress, pickupPersonName, supportContact);
    }
}
