package com.luis.petsrcv.ui.profile;

import com.luis.petsrcv.ui.PetModel;

import java.util.List;

public interface PetProfileContract {
    interface view {
        void showProfile(List<PetModel> list);
    }

    interface presenter {
        void getProfile();
    }
}
