package com.luis.petsrcv.ui.profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.luis.petsrcv.R;
import com.luis.petsrcv.ui.PetModel;

import java.util.List;

public class PetProfileAdapter extends RecyclerView.Adapter<PetProfileAdapter.ViewHolder> {
    private List<PetModel> list;

    public void setList(List<PetModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_pet_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PetModel item = list.get(position);
        holder.configViews(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView petImage;
        private final TextView petName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            petImage = itemView.findViewById(R.id.imvPetImage);
            petName = itemView.findViewById(R.id.tvPetName);
        }

        private void configViews(PetModel model) {
            petImage.setImageResource(model.getImageResId());
            petName.setText(String.valueOf(model.getRating()));
        }
    }
}
