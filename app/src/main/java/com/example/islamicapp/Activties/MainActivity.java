package com.example.islamicapp.Activties;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.islamicapp.Adapter.HomeHorAdapter;
import com.example.islamicapp.Models.HorModel;
import com.example.islamicapp.R;
import com.example.islamicapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView hRecyclerView;
    private HomeHorAdapter homeHorAdapter;
    private ArrayList<HorModel> homeHorModelsList;
    ActivityMainBinding binding;

    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

// Set background to null to remove shadows or borders
        binding.bottomNaviBar.setBackground(null);



        horData();

        hRecyclerView = findViewById(R.id.hRecyclerView);
        hRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        homeHorAdapter = new HomeHorAdapter(this, homeHorModelsList);
        hRecyclerView.setAdapter(homeHorAdapter);

        bottomNavigationView = findViewById(R.id.bottomNaviBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
//                    case R.id.home:
//                        openQuranActivity();
//                        return true;
                    case R.id.athkar:
                        openAthkarActivity();
                        return true;
                    case R.id.quran:
                        openQuranActivity();
                        return true;
                    // Add cases for other menu items as needed
                }
                return false;
            }
        });
    }

    private void openQuranActivity() {
        Intent intent = new Intent(MainActivity.this, QuranSelectionActivity.class);
        startActivity(intent);
    }

    private void openAthkarActivity() {
        Intent intent = new Intent(MainActivity.this, Athkar.class);
        startActivity(intent);
    }

    private void openTasbihActivity() {
        Intent intent = new Intent(MainActivity.this, TasbihActivity.class);
        startActivity(intent);
    }

     private void horData(){
         homeHorModelsList = new ArrayList<>();
         homeHorModelsList.add(new HorModel("Quran", R.drawable.quran_icon_new));
         homeHorModelsList.add(new HorModel("Athkar", R.drawable.athkar_icon_2));
         homeHorModelsList.add(new HorModel("Tasbih", R.drawable.tasbih_new3));
         homeHorModelsList.add(new HorModel("Prayer Time", R.drawable.salah_ic));
         homeHorModelsList.add(new HorModel("Qibla", R.drawable.qaba));
         homeHorModelsList.add(new HorModel("Nasheeds", R.drawable.nasheed_icon));
         homeHorModelsList.add(new HorModel("Quiz", R.drawable.quiz));
     }

}