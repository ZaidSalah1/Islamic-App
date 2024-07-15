package com.example.islamicapp.Activties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.islamicapp.Adapter.HomeHorAdapter;
import com.example.islamicapp.Models.HorModel;
import com.example.islamicapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView hRecyclerView;
    private HomeHorAdapter homeHorAdapter;
    private ArrayList<HorModel> homeHorModelsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        horData();

        hRecyclerView = findViewById(R.id.hRecyclerView);
        hRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        homeHorAdapter = new HomeHorAdapter(this, homeHorModelsList);
        hRecyclerView.setAdapter(homeHorAdapter);

    }

     private void horData(){
         homeHorModelsList = new ArrayList<>();
         homeHorModelsList.add(new HorModel("Quran", R.drawable.quran_icon_new));
         homeHorModelsList.add(new HorModel("Athkar", R.drawable.athkar_icon_2));
         homeHorModelsList.add(new HorModel("Tasbih", R.drawable.tasbih_new));
         homeHorModelsList.add(new HorModel("Prayer Time", R.drawable.salah_ic));
         homeHorModelsList.add(new HorModel("Nasheeds", R.drawable.nashed));
         homeHorModelsList.add(new HorModel("Quiz", R.drawable.quiz));
     }

}