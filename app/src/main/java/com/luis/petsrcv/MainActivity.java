package com.luis.petsrcv;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private final PetDataset dataset = PetDataset.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView petRcv = findViewById(R.id.rcvPets);
        PetAdapter adapter = new PetAdapter();
        adapter.setList(dataset.get());
        adapter.setOnFavorite(dataset::addToFavorites);
        petRcv.setAdapter(adapter);

        FloatingActionButton actionButton = findViewById(R.id.fabUp);
        actionButton.setOnClickListener(view -> petRcv.smoothScrollToPosition(0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_favorites) {
            goToFavoriteActivity();
            return true;
        }
        if (item.getItemId() == R.id.menu_contact) showContactForm();
        if (item.getItemId() == R.id.menu_about) showOptionAbout();
        return super.onOptionsItemSelected(item);
    }

    private void goToFavoriteActivity() {
        Intent intent = new Intent(getBaseContext(), FavoriteActivity.class);
        startActivity(intent);
        finish();
    }

    private void showContactForm() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_contact, null);
        TextInputLayout tilNames = dialogView.findViewById(R.id.tilNames);
        TextInputLayout tilEmail = dialogView.findViewById(R.id.tilEmail);
        TextInputLayout tilMessage = dialogView.findViewById(R.id.tilMessage);
        Button btnSend = dialogView.findViewById(R.id.btnSend);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        btnSend.setOnClickListener(v -> {
            String names = Objects.requireNonNull(tilNames.getEditText()).getText().toString();
            String email = Objects.requireNonNull(tilEmail.getEditText()).getText().toString();
            String messages = Objects.requireNonNull(tilMessage.getEditText()).getText().toString();
            EmailSender.sendEmail(names, email, messages);
            dialog.dismiss();
        });

        dialog.show();
    }

    private void showOptionAbout(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_about, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}