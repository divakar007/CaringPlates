package com.application.caringplates.models;
import com.application.caringplates.dto.PostDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postID")
    private Long postID;

    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurnatid", referencedColumnName = "restId")
    private Restaurant restaurant;

    @Lob
    @Column(name = "image_data", columnDefinition="LONGBLOB")
    private byte[] imageData;

    @Column(name = "bestbefore", nullable = false)
    private Date bestBefore;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "claimed")
    private Boolean claimed;

    @Column(name = "quantity")
    private Integer quantity;


    public void setPostID(Long postID) {
        this.postID = postID;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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

    public Long getPostID() {
        return postID;
    }

    public User getUser() {
        return user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Post() {
    }

    public Post(PostDTO postDTO) {
        this.imageData = postDTO.getImageData();
        this.bestBefore = postDTO.getBestBefore();
        this.title = postDTO.getItemName();
        this.description = postDTO.getDescription();
        this.claimed = postDTO.getClaimed();
        this.quantity = postDTO.getQuantity();
    }

}