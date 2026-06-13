package com.luis.petsrcv.bd;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PetEntity {

    public PetEntity(int pet_id, String name, int imageResId, int rating) {
        this.pet_id = pet_id;
        this.name = name;
        this.imageResId = imageResId;
        this.rating = rating;
    }

    @PrimaryKey
    public int pet_id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "resource_id")
    public int imageResId;

    @ColumnInfo(name = "rating")
    public int rating;
}
