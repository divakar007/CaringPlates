package com.application.caringplates.models;
import jakarta.persistence.*;
import lombok.Data;
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
    @Column(name = "image_data")
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
}