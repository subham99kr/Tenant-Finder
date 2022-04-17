package com.example.tenantfinder_9204;

public class TenantDB {
    String name;
    String phone;
    String details;
    String imageUrl;



    public TenantDB(String name, String phone, String details, String imageUrl) {
        this.name = name;
        this.phone = phone;
        this.details = details;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
