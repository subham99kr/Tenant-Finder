package com.example.tenantfinder_9204;

public class OwnerDB {
    String name;
    String phone;
    String details;
    String address;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDetails() {
        return details;
    }

    public String getAddress() {
        return address;
    }

    public OwnerDB(String name, String phone, String details, String address) {
        this.name = name;
        this.phone = phone;
        this.details = details;
        this.address = address;

    }
}

