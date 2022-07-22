package com.example.app_stoy.Model;

import com.google.firebase.Timestamp;

import java.util.Date;

public class Product {
    String image, description;
    int price, sole;
    String date;

    public Product(String image, String description, int price, int sole, String date) {
        this.image = image;
        this.description = description;
        this.price = price;
        this.sole = sole;
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSole() {
        return sole;
    }

    public void setSole(int sole) {
        this.sole = sole;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Product{" +
                "image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", sole=" + sole +
                ", date=" + date +
                '}';
    }
}
