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
        String subjectUser = "Order Confirmed!";
        sendOrderConfirmationEmail(user.getEmailId(),restUser.getEmailId(),subjectUser,postDTO.getItemName(),postDTO.getDescription(),postDTO.getQuantity(),restaurant.getAddress(), String.valueOf( new Random().nextInt(1000000)), user.getFirstName(),user.getPhoneNumber());

    }

    public void sendOrderConfirmationEmail(String toUser,String toRestaurant, String subject, String productName, String description, int quantity, String restaurantAddress, String orderID, String nameOfTheUser, String phoneOfTheUser) {
        try {
            String htmlContent = "";
            MimeMessage message = emailService.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toUser);
            helper.setSubject(subject);

            htmlContent = getHtmlContent(productName, description, quantity, restaurantAddress, orderID);
            helper.setText(htmlContent, true);

            emailService.send(message);
            helper.setTo(toRestaurant);
            htmlContent = getRestaurantHtmlContent(productName,description,quantity,restaurantAddress, nameOfTheUser,phoneOfTheUser);
            helper.setText(htmlContent,true);
            emailService.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String getHtmlContent(String productName, String description, int quantity, String restaurantAddress, String orderID) {
        double restaurantLatitude = 38.8737785; // Replace with the actual latitude of the restaurant
        double restaurantLongitude = -77.2254089; // Replace with the actual longitude of the restaurant

        String googleMapsStaticMapUrl = "https://maps.googleapis.com/maps/api/staticmap?center=%f,%f&zoom=15&size=400x300&markers=%f,%f&key=AIzaSyBkzbZ-oI9-ayoTAOZn8dy8RKG0virffeE";
        googleMapsStaticMapUrl = String.format(googleMapsStaticMapUrl, restaurantLatitude, restaurantLongitude, restaurantLatitude, restaurantLongitude);

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

                        <p>Good news! Your order has been claimed. Here are the details:</p>

                        <h3>Order Details:</h3>
                        <ul>
                            <li><strong>Name:</strong> %s</li>
                            <li><strong>Description:</strong> %s</li>
                            <li><strong>Quantity:</strong> %d</li>
                            <li><strong>Restaurant Location:</strong> %s</li>
                            <li><strong>Order Id: <strong> %s </li>
                        </ul>
                        
                        <p> Here is the location of the restaurant </p>
                        
                        <a href="https://www.google.com/maps/place/Popeyes+Louisiana+Kitchen/@38.8737785,-77.2254089,17z/data=!3m1!5s0x89b64b65a96fa751:0x34868d09037b25af!4m14!1m7!3m6!1s0x89b64ba78e63ad33:0x6d419b735f81f4fd!2sPopeyes+Louisiana+Kitchen!8m2!3d38.8737785!4d-77.2254089!16sg11j0bq0b9p!3m5!1s0x89b64ba78e63ad33:0x6d419b735f81f4fd!8m2!3d38.8737785!4d-77.2254089!16sg11j0bq0b9p?authuser=0&hl=en&entry=ttu"><img src="%s" alt="Restaurant Location" style="max-width:auto ; border-radius: 8px; margin-bottom: 15px;"/></a>                                      \s
                        <p>Please coordinate with the pickup person for the order handover. If you have any questions or concerns, feel free to contact our support team at +1 571 233 2323.</p>

                        <p>Thank you for using Caring Plates!</p>

                        <p>Best regards,<br>
                        Caring Plates<br>
                        Phone : +1 571 591 8936</p>

                    </div>

                </body>
                </html>""".formatted(productName, description, quantity, restaurantAddress, orderID, googleMapsStaticMapUrl);
    }
    private String getRestaurantHtmlContent(String productName, String description, int quantity, String restaurantAddress, String pickupPersonName,String pickupPersonPhone) {
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
                """.formatted(productName, description, quantity, restaurantAddress, pickupPersonName, pickupPersonPhone);
    }
}
