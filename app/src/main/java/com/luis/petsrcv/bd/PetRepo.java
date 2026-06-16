package com.luis.petsrcv.bd;

import com.luis.petsrcv.ui.PetModel;
import com.luis.petsrcv.R;

import java.util.List;
import java.util.stream.Collectors;

public class PetRepo {
    private final PetDao dao;

    public PetRepo(PetDao dao) {
        this.dao = dao;
    }

    public List<PetModel> getAllPets() {
        List<PetEntity> entityList = dao.getAllPets();
        return entityList.stream().map(entity -> new PetModel(
                entity.pet_id,
                entity.imageResId,
                entity.name,
                entity.rating
        )).collect(Collectors.toList());
    }

    public List<PetModel> getFiveMostFavoritePets() {
        return dao.getFiveMostFavoritePets()
                .stream().map(entity -> new PetModel(
                        entity.pet_id,
                        entity.imageResId,
                        entity.name,
                        entity.rating
                )).collect(Collectors.toList());
    }

    public PetModel increasePetRating(PetModel petModel) {
        int newRatting = dao.getPetById(petModel.getId()).rating + 1;
        dao.updatePetRating(petModel.getId(), newRatting);
        PetEntity updatedItem = dao.getPetById(petModel.getId());
        return new PetModel(
                updatedItem.pet_id,
                updatedItem.imageResId,
                updatedItem.name,
                updatedItem.rating
        );
    }

    public void insertPreviousData() {
        dao.insertAll(
                new PetEntity(1, "Max", R.drawable.pet1, 3),
                new PetEntity(2, "Luna", R.drawable.pet2, 4),
                new PetEntity(3, "Rocky", R.drawable.pet3, 5),
                new PetEntity(4, "Coco", R.drawable.pet4, 2),
                new PetEntity(5, "Bella", R.drawable.pet5, 1),
                new PetEntity(6, "Simba", R.drawable.pet6, 3),
                new PetEntity(7, "Nina", R.drawable.pet7, 1),
                new PetEntity(8, "Toby", R.drawable.pet8, 7),
                new PetEntity(9, "Lola", R.drawable.pet9, 2),
                new PetEntity(10, "Zeus", R.drawable.pet10, 6)
        );
    }
}
