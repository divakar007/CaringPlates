package com.application.caringplates.dto;
import com.application.caringplates.models.Restaurant;
import com.application.caringplates.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

import java.util.Date;
public class PostDTO {

    private Long userId;

    private Long restaurantId;

    private byte[] imageData;

    private Date bestBefore;

    private String title;

    private String description;

    private Boolean claimed;

    private Integer quantity;

    public PostDTO( byte[] imageData, Date bestBefore, String title, String description, Boolean claimed, Integer quantity) {
        this.imageData = imageData;
        this.bestBefore = bestBefore;
        this.title = title;
        this.description = description;
        this.claimed = claimed;
        this.quantity = quantity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Date getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(Date bestBefore) {
        this.bestBefore = bestBefore;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getClaimed() {
        return claimed;
    }

    public void setClaimed(Boolean claimed) {
        this.claimed = claimed;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
