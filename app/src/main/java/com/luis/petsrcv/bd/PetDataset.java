package com.luis.petsrcv.bd;

import com.luis.petsrcv.ui.PetModel;
import com.luis.petsrcv.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PetDataset {
    private static PetDataset instance;
    private final List<PetModel> allPets;

    private PetDataset() {
        allPets = new ArrayList<>();
        loadPets();
    }

    public static PetDataset getInstance() {
        if (instance == null) instance = new PetDataset();
        return instance;
    }

    private void loadPets() {
        allPets.clear();
        allPets.add(new PetModel(1, R.drawable.pet1, "Max", 3));
        allPets.add(new PetModel(2, R.drawable.pet2, "Luna", 4));
        allPets.add(new PetModel(3, R.drawable.pet3, "Rocky", 5));
        allPets.add(new PetModel(4, R.drawable.pet4, "Coco", 2));
        allPets.add(new PetModel(5, R.drawable.pet5, "Bella", 1));
        allPets.add(new PetModel(6, R.drawable.pet6, "Simba", 3));
        allPets.add(new PetModel(7, R.drawable.pet7, "Nina", 1));
        allPets.add(new PetModel(8, R.drawable.pet8, "Toby", 7));
        allPets.add(new PetModel(9, R.drawable.pet9, "Lola", 2));
        allPets.add(new PetModel(10, R.drawable.pet10, "Zeus", 6));
    }

    public List<PetModel> get() {
        return new ArrayList<>(allPets);
    }

    public List<PetModel> getFavoritePets() {
        //get five most voted pets
        return allPets.stream()
                .sorted(Comparator.comparingInt(PetModel::getRating).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    public List<PetModel> profilePet() {
        List<PetModel> list = new ArrayList<>();
        list.add(new PetModel(1, R.drawable.pet4, "Coco", 3));
        list.add(new PetModel(2, R.drawable.pet4, "Coco", 4));
        list.add(new PetModel(3, R.drawable.pet4, "Coco", 5));
        list.add(new PetModel(4, R.drawable.pet4, "Coco", 2));
        list.add(new PetModel(5, R.drawable.pet4, "Coco", 1));
        list.add(new PetModel(6, R.drawable.pet4, "Coco", 3));
        list.add(new PetModel(7, R.drawable.pet4, "Coco", 1));
        list.add(new PetModel(8, R.drawable.pet4, "Coco", 7));
        list.add(new PetModel(9, R.drawable.pet4, "Coco", 2));
        return list;
    }

    public void addToFavorites(PetModel pet) {
        int rating = pet.getRating() + 1;
        pet.setRating(rating);
    }

}
