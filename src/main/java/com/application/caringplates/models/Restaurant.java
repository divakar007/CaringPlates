package com.application.caringplates.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "restaurant")

    public class Restaurant {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "restId")
        private Long restId;

        @Column(name = "name", nullable = false)
        private String name;

        @ManyToOne
        @JoinColumn(name = "userid", referencedColumnName = "userid")
        private User user;

        @Column(name = "address", nullable = false)
        private String address;

        @Column(name = "zipcode", nullable = false)
        private String zipcode;

        @Column(name = "landmark")
        private String landmark;

        @Column(name = "phone", nullable = false)
        private String phone;

        @Column(name = "zeolocation", nullable = false)
        private String zeolocation;
    }
