package com.example.pcbuilder.model;

import java.io.Serializable;

public class ShowAllModel implements Serializable {
    String img_url;
    String name;
    int price;
    String rating;
    String description;
    String type;

    public ShowAllModel(String img_url, String name,int price, String type,String description) {
        this.img_url = img_url;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.type = type;
        this.description = description;
    }

    public ShowAllModel() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}


