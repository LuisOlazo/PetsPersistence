package com.luis.petsrcv;

import android.app.Application;

import androidx.room.Room;

import com.luis.petsrcv.bd.AppDatabase;
import com.luis.petsrcv.bd.PetRepo;

import java.util.concurrent.Executors;

public class MyApp extends Application {
    private static final String DB_NAME = "PETAGRAM_BD";
    private static MyApp instance;
    private AppDatabase appDatabase;
    private PetRepo petRepo;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
        petRepo = new PetRepo(appDatabase.petDao());

        Executors.newSingleThreadExecutor().execute(() -> petRepo.insertPreviousData());
    }

    public static MyApp getInstance() {
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public PetRepo getPetRepo() {
        return petRepo;
    }

}
