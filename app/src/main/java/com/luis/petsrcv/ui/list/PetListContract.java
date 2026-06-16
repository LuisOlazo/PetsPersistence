package com.luis.petsrcv.ui.list;

import com.luis.petsrcv.ui.PetModel;

import java.util.List;

public interface PetListContract {
    interface view {
        void showList(List<PetModel> list);

        void showRating(PetModel item);
    }

    interface presenter {
        void getList();

        void setRating(PetModel item);
    }
}
