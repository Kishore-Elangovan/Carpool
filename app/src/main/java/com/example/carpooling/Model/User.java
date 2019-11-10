package com.example.carpooling.Model;

import java.util.HashMap;
import java.util.Map;

public class User
{
    private String email,password,name,phone,imageURL,location,destination;
    private String uid;

    public User()
    {
        imageURL = "default";
    }

    public User(String email, String password, String name, String phone)
    {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination= destination;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }
    public String getUid()
    {
        return uid;
    }
    public Map<String, Object> toMap()
    {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("email", email);
        result.put("password", password);
        result.put("phone", phone);
        result.put("uid", uid);

        return result;
    }
}
