package com.luis.petsrcv.ui.favorites;

import com.luis.petsrcv.bd.PetRepo;
import com.luis.petsrcv.ui.PetModel;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class FavoritesPresenter implements FavoritesContract.presenter {
    private final FavoritesContract.view view;
    private final PetRepo petRepo;
    private final ExecutorService executorService;

    public FavoritesPresenter(FavoritesContract.view view, PetRepo petRepo, ExecutorService executorService) {
        this.view = view;
        this.petRepo = petRepo;
        this.executorService = executorService;
    }

    @Override
    public void getFavorites() {
        executorService.execute(() -> {
            List<PetModel> list = petRepo.getFiveMostFavoritePets();
            view.showFavorites(list);
        });
    }

}
