package com.luis.petsrcv.ui.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.luis.petsrcv.main.MyApp;
import com.luis.petsrcv.R;
import com.luis.petsrcv.bd.PetRepo;
import com.luis.petsrcv.ui.PetAdapter;
import com.luis.petsrcv.ui.PetModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PetListFragment extends Fragment {
    private PetRepo petRepo;
    private ExecutorService executorService;

    private PetListFragment() {
    }

    public static PetListFragment newInstance() {
        return new PetListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        petRepo = MyApp.getInstance().getPetRepo();
        executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pet_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView petRcv = view.findViewById(R.id.rcvPets);
        PetAdapter adapter = new PetAdapter();
        adapter.setOnFavorite(petModel ->
                executorService.execute(() -> petRepo.increasePetRating(petModel)));
        executorService.execute(() -> {
            List<PetModel> allPets = petRepo.getAllPets();
            if (getActivity() != null) {
                getActivity().runOnUiThread(() -> {
                    adapter.setList(allPets);
                    petRcv.setAdapter(adapter);
                });
            }
        });
        FloatingActionButton actionButton = view.findViewById(R.id.fabUp);
        actionButton.setOnClickListener(v -> petRcv.smoothScrollToPosition(0));
    }

}