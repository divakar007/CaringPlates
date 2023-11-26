package com.application.caringplates.models;

import com.application.caringplates.dto.RestaurantDTO;
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

    public Long getRestId() {
        return restId;
    }

    public void setRestId(Long restId) {
        this.restId = restId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZeolocation() {
        return zeolocation;
    }

    public void setZeolocation(String zeolocation) {
        this.zeolocation = zeolocation;
    }

    public Restaurant() {
    }

    public Restaurant(RestaurantDTO restaurantDTO) {
        this.name = restaurantDTO.getName();
        this.address = restaurantDTO.getAddress();
        this.zipcode = restaurantDTO.getZipcode();
        this.landmark = restaurantDTO.getLandmark();
        this.phone = restaurantDTO.getPhone();
        this.zeolocation = restaurantDTO.getZeolocation();
    }
    public RestaurantDTO getDTO(){
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setAddress(this.address);
        restaurantDTO.setLandmark(this.landmark);
        restaurantDTO.setName(this.name);
        restaurantDTO.setPhone(this.phone);
        restaurantDTO.setZeolocation(this.zeolocation);
        restaurantDTO.setUser(this.user.getUserId());
        restaurantDTO.setZipcode(this.zipcode);
        restaurantDTO.setRestId(this.restId);
        return restaurantDTO;
    }
}
