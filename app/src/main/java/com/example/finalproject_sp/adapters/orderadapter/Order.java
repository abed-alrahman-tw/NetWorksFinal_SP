package com.example.finalproject_sp.adapters.orderadapter;

public class Order {
    String image;
    String customerName;
    String serviceType;
    String orderNumber;
    String locationDetails;
    String time;
    String lat;
    String longitude;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Order(String image, String customerName, String serviceType, String orderNumber,
                 String locationDetails, String time, String lat, String longitude) {
        this.image = image;
        this.customerName = customerName;
        this.serviceType = serviceType;
        this.orderNumber = orderNumber;
        this.locationDetails = locationDetails;
        this.time = time;
        this.lat = lat;
        this.longitude = longitude;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocationDetails() {
        return locationDetails;
    }

    public void setLocationDetails(String locationDetails) {
        this.locationDetails = locationDetails;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
