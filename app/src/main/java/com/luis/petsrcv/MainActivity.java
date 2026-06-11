package com.luis.petsrcv;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    //    private final PetDataset dataset = PetDataset.getInstance();
    private ViewPager2 viewPager;
    private TabLayout tabLayout;

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

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, getFragmentsForViewPager());
        viewPager.setAdapter(viewPagerAdapter);

        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setIcon(R.drawable.pet_house_kennel_svgrepo_com);
                            break;
                        case 1:
                            tab.setIcon(R.drawable.dog_svgrepo_com);
                            break;
                    }
                }
        );
        mediator.attach();

//        RecyclerView petRcv = findViewById(R.id.rcvPets);
//        PetAdapter adapter = new PetAdapter();
//        adapter.setList(dataset.get());
//        adapter.setOnFavorite(dataset::addToFavorites);
//        petRcv.setAdapter(adapter);
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
            EmailSender.sendEmail(names, email, messages,
                    exception -> Toast.makeText(getBaseContext(), getString(R.string.msg_error_mail), Toast.LENGTH_SHORT).show());
            dialog.dismiss();
        });

        dialog.show();
    }

    private void showOptionAbout() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_about, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private List<Fragment> getFragmentsForViewPager() {
        List<Fragment> list = new ArrayList<>(2);
        list.add(PetListFragment.newInstance("", ""));
        list.add(PetProfileFragment.newInstance("", ""));
        return list;
    }

}