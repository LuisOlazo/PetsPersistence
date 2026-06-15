package com.luis.petsrcv.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.luis.petsrcv.R;

import java.util.List;
import java.util.function.Consumer;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ViewHolder> {
    private List<PetModel> list;
    private Consumer<PetModel> onFavorite;

    public void setList(List<PetModel> list) {
        this.list = list;
    }

    public void setOnFavorite(Consumer<PetModel> onFavorite) {
        this.onFavorite = onFavorite;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_pet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PetModel item = list.get(position);
        holder.configViews(item);
        holder.imvFavorite.setOnClickListener(view -> {
            if (position != RecyclerView.NO_POSITION) {
                onFavorite.accept(item);
                notifyItemChanged(holder.getAbsoluteAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView petImage, imvFavorite;
        private final TextView petName, petRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            petImage = itemView.findViewById(R.id.imvPetImage);
            imvFavorite = itemView.findViewById(R.id.imvBone1);
            petName = itemView.findViewById(R.id.tvPetName);
            petRating = itemView.findViewById(R.id.tvPetRating);
        }

        private void configViews(PetModel model) {
            petImage.setImageResource(model.getImageResId());
            petName.setText(model.getName());
            petRating.setText(String.valueOf(model.getRating()));
        }

    }
}
