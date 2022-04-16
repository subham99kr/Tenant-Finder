package com.example.tenantfinder_9204;

public class TenantDB {
    String name;
    String phone;
    String details;
    String imageUrl;


    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDetails() {
        return details;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public TenantDB(String name, String phone, String details) {
        this.name = name;
        this.phone = phone;
        this.details = details;
    }

    public TenantDB(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
