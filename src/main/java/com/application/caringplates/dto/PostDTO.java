package com.application.caringplates.dto;

import com.application.caringplates.utils.ImageUtil;
import jakarta.persistence.Lob;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Getter
public class PostDTO {

    private Long userId;

    private Long restaurantId;

    @Lob
    private byte[] imageData;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date bestBefore;

    private String itemName;

    private String description;

    private Boolean claimed;

    private Integer quantity;

    private Long postId;

    public PostDTO( MultipartFile imageData, String bestBefore, String itemName, String description, Integer quantity, Long postId, Long restaurantId) throws IOException, ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        this.imageData = ImageUtil.convertImageToByteArray(imageData);
        this.bestBefore = dateFormat.parse(bestBefore);
        this.itemName = itemName;
        this.description = description;
        this.claimed = Boolean.FALSE;
        this.quantity = quantity;
        this.postId = postId;
        this.restaurantId = restaurantId;
    }

    public PostDTO() {

    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }


    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setClaimed(Boolean claimed) {
        this.claimed = claimed;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long setPostId() {
        return postId;
    }

    public Long setRestaurantId() {
        return restaurantId;
    }
}
