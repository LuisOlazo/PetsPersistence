package com.luis.petsrcv.bd;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PetEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PetDao petDao();
}
