package com.luis.petsrcv;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PetListFragment extends Fragment {
    private final PetDataset dataset = PetDataset.getInstance();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public PetListFragment() {
    }

    public static PetListFragment newInstance(String param1, String param2) {
        PetListFragment fragment = new PetListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        adapter.setList(dataset.get());
        adapter.setOnFavorite(dataset::addToFavorites);
        petRcv.setAdapter(adapter);
        FloatingActionButton actionButton = view.findViewById(R.id.fabUp);
        actionButton.setOnClickListener(v -> petRcv.smoothScrollToPosition(0));
    }


}