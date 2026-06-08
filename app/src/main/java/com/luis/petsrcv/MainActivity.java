package com.luis.petsrcv;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
        return super.onOptionsItemSelected(item);
    }

    private void goToFavoriteActivity() {
        Intent intent = new Intent(getBaseContext(), FavoriteActivity.class);
        startActivity(intent);
        finish();
    }

}