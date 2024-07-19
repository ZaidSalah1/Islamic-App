package com.example.islamicapp.Activties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.islamicapp.Adapter.TasbihAdapter;
import com.example.islamicapp.Models.AthkarModel;
import com.example.islamicapp.R;

import java.util.ArrayList;

public class TasbihActivity extends AppCompatActivity {

    private RecyclerView athkarhRecyclerView;
    private ArrayList<AthkarModel> athkarModelsList;
    private ConstraintLayout screen_btn;
    private LinearSnapHelper snapHelper;
    private TasbihAdapter adapter;
    private LinearLayoutManager layoutManager;

    private LottieAnimationView lottie;
    private TextView txtTotalCounter;
    private int totalCounter = 0;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasbih);

        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;


        screen_btn = findViewById(R.id.screen_btn);
        txtTotalCounter = findViewById(R.id.totalCounter);
        lottie = findViewById(R.id.animationView);
        lottie.pauseAnimation();
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        totalCounter = sp.getInt("today", 0);
        txtTotalCounter.setText("Today Total: " + totalCounter);

        screen_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lottie.playAnimation();

                // Create a Handler to stop the animation after 1 second (1000 milliseconds)
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lottie.cancelAnimation();
                    }
                }, 500);

                // Increment the counter of the currently displayed card view
                View snappedView = snapHelper.findSnapView(layoutManager);
                if (snappedView != null) {
                    int position = layoutManager.getPosition(snappedView);
                    adapter.incrementCounterAtPosition(position);
                }
                totalCounter++;
                txtTotalCounter.setText("Today Total: " + totalCounter);
                saveTodayTotal(totalCounter);
            }
        });

        ArrayList<AthkarModel> list = new ArrayList<>();
        list.add(new AthkarModel("أستغفر اللهَ"));
        list.add(new AthkarModel("سُبْحَانَ اللَّهِ وَبِحَمْدِهِ "));
        list.add(new AthkarModel("الْحَمْدُ للّهِ رَبِّ الْعَالَمِينَ"));
        list.add(new AthkarModel("لَا إلَه إلّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَى كُلُّ شَيْءِ قَدِيرِ "));
        list.add(new AthkarModel("لا حَوْلَ وَلا قُوَّةَ إِلا بِاللَّهِ َ"));
        list.add(new AthkarModel("الْلَّهُ أَكْبَرُ َ"));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new TasbihAdapter(this, list, width);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }

    public void saveTodayTotal(int counter) {
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sp.edit();

        editor.putInt("today", counter);
        editor.apply();  // Using apply() instead of commit() for asynchronous saving

    }


}
