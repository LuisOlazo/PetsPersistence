package com.luis.petsrcv.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.luis.petsrcv.bd.PetDataset;
import com.luis.petsrcv.R;
import com.luis.petsrcv.ui.PetModel;

import java.util.List;

public class PetProfileFragment extends Fragment implements PetProfileContract.view {

    PetProfileContract.presenter presenter;

    private PetProfileFragment() {
    }

    public static PetProfileFragment newInstance() {
        return new PetProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PetProfilePresenter(this, PetDataset.getInstance());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pet_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        presenter.getProfile();
    }

    @Override
    public void showProfile(List<PetModel> list) {
        View view = getView();
        if (view != null) {
            PetModel firstItem = list.get(0);
            ImageView profileImage = view.findViewById(R.id.ivProfilePhoto);
            TextView profileName = view.findViewById(R.id.tvPetName);
            profileName.setText(firstItem.getName());
            profileImage.setImageResource(firstItem.getImageResId());
            RecyclerView rcvPetProfile = view.findViewById(R.id.rcvPetProfile);
            PetProfileAdapter profileAdapter = new PetProfileAdapter();
            profileAdapter.setList(list);
            rcvPetProfile.setAdapter(profileAdapter);
        }
    }

}