package com.example.lecture1batch3java.models;

public class User {

    String email;
    String name;
    String imageUrl;

    public User() {
    }

    public User(String email, String name, String imageUrl) {
        this.email = email;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
