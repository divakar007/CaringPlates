package com.application.caringplates.dto;

public class RestaurantDTO {

    private String name;
    private Long user;
    private String address;
    private String zipcode;
    private String landmark;
    private String phone;
    private String zeolocation;
    private Long restId;

    public RestaurantDTO(String name, String address, String zipcode, String landmark, String phone, String zeolocation) {
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.landmark = landmark;
        this.phone = phone;
        this.zeolocation = zeolocation;
    }
    public RestaurantDTO(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getLatitude() {
        if (zeolocation != null && !zeolocation.isEmpty()) {
            String[] coordinates = zeolocation.split(",");
            if (coordinates.length == 2) {
                return Double.parseDouble(coordinates[0].trim());
            }
        }
        return 0.0; // or throw an exception if invalid format
    }

    public double getLongitude() {
        if (zeolocation != null && !zeolocation.isEmpty()) {
            String[] coordinates = zeolocation.split(",");
            if (coordinates.length == 2) {
                return Double.parseDouble(coordinates[1].trim());
            }
        }
        return 0.0; // or throw an exception if invalid format
    }

    public void setZeolocation(double latitude, double longitude) {
        this.zeolocation = latitude + "," + longitude;
    }

    public void setZeolocation(String zeolocation) {
        this.zeolocation = zeolocation;
    }

    public String getName() {
        return name;
    }

    public Long getUser() {
        return user;
    }

    public String getAddress() {
        return address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getPhone() {
        return phone;
    }

    public String getZeolocation() {
        return zeolocation;
    }

    public Long getRestId() {
        return restId;
    }

    public void setRestId(Long restId) {
        this.restId = restId;
    }
}
