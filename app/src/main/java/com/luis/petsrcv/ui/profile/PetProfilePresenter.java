package com.luis.petsrcv.ui.profile;

import com.luis.petsrcv.bd.PetDataset;

public class PetProfilePresenter implements PetProfileContract.presenter {
    private final PetProfileContract.view view;
    private final PetDataset dataset;

    public PetProfilePresenter(PetProfileContract.view view, PetDataset dataset) {
        this.view = view;
        this.dataset = dataset;
    }

    @Override
    public void getProfile() {
        view.showProfile(dataset.profilePet());
    }

}
