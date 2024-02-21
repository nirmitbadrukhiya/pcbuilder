package com.example.pcbuilder.model;

import java.io.Serializable;

public class PopularModel implements Serializable {
    String img_url;
    String name;
    int price;
    String type;
    String description;
    String rating;

    public PopularModel() {
    }

    public PopularModel(String img_url, String name, int price, String type, String description, String rating) {
        this.img_url = img_url;
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
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
}
