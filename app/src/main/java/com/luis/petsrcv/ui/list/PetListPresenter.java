package com.luis.petsrcv.ui.list;

import com.luis.petsrcv.bd.PetRepo;
import com.luis.petsrcv.ui.PetModel;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class PetListPresenter implements PetListContract.presenter {
    PetListContract.view view;
    PetRepo petRepo;
    ExecutorService executorService;

    public PetListPresenter(PetListContract.view view, PetRepo petRepo, ExecutorService executorService) {
        this.view = view;
        this.petRepo = petRepo;
        this.executorService = executorService;
    }

    @Override
    public void getList() {
        executorService.execute(() -> {
            List<PetModel> list = petRepo.getAllPets();
            view.showList(list);
        });
    }

    @Override
    public void setRating(PetModel item) {
        executorService.execute(() -> {
            PetModel updatedItem = petRepo.increasePetRating(item);
            view.showRating(updatedItem);
        });
    }

}
