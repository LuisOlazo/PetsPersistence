package com.luis.petsrcv.bd;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PetDao {

    @Query("SELECT * FROM PetEntity WHERE pet_id = :petId")
    PetEntity getPetById(int petId);

    @Query("SELECT * FROM PetEntity ORDER BY rating DESC LIMIT 5")
    List<PetEntity> getFiveMostFavoritePets();

    @Query("SELECT * FROM PetEntity LIMIT 10")
    List<PetEntity> getAllPets();

    @Insert
    void insertAll(PetEntity... petEntities);

    @Query("UPDATE PetEntity SET rating = :newRating WHERE pet_id = :petId")
    void updatePetRating(int petId, int newRating);

    @Query("SELECT COUNT(*) FROM PetEntity")
    int getCount();

}
