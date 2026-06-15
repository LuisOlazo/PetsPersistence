package com.luis.petsrcv.ui.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.luis.petsrcv.ui.MainActivity;
import com.luis.petsrcv.main.MyApp;
import com.luis.petsrcv.R;
import com.luis.petsrcv.bd.PetRepo;
import com.luis.petsrcv.ui.PetAdapter;
import com.luis.petsrcv.ui.PetModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoriteActivity extends AppCompatActivity {
    private PetRepo petRepo;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favorites);
        petRepo = MyApp.getInstance().getPetRepo();
        executorService = Executors.newSingleThreadExecutor();
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.favorites), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView petRcv = findViewById(R.id.rcvFavoritePets);
        PetAdapter adapter = new PetAdapter();

        executorService.execute(() -> {
            List<PetModel> petList = petRepo.getFiveMostFavoritePets();
            adapter.setList(petList);
            petRcv.setAdapter(adapter);
        });
        adapter.setOnFavorite(p ->
                Toast.makeText(this, getString(R.string.msg_favorites), Toast.LENGTH_SHORT).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favorite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            goToMainActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMainActivity() {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

}