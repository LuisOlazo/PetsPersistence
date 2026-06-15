package com.luis.petsrcv.ui;

public class PetModel {
    private final int id;
    private final int imageResId;
    private final String name;
    private int rating;

    public PetModel(int id, int imageResId, String name, int rating) {
        this.id = id;
        this.imageResId = imageResId;
        this.name = name;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
