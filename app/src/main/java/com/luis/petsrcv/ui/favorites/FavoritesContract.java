package com.luis.petsrcv.ui.favorites;

import com.luis.petsrcv.ui.PetModel;

import java.util.List;

public interface FavoritesContract {
    interface view {
        void showFavorites(List<PetModel> list);
    }

    interface presenter {
        void getFavorites();
    }
}
