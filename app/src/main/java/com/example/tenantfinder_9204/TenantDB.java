package com.example.tenantfinder_9204;

public class TenantDB {
    String name;
    String phone;
    String details;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDetails() {
        return details;
    }

    public TenantDB(String name, String phone, String details) {
        this.name = name;
        this.phone = phone;
        this.details = details;


    }
}
