package com.example.islamicapp.Activties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.islamicapp.Adapter.TasbihAdapter;
import com.example.islamicapp.Models.AthkarModel;
import com.example.islamicapp.Models.DailyDataModel;
import com.example.islamicapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    private ArrayList<Integer> totalList;
    static ArrayList<DailyDataModel> dateList;

    private TextView tollBarTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasbih);

        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;


        tollBarTxt = findViewById(R.id.toolbar_title);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tollBarTxt.setText("Tasbih");
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        screen_btn = findViewById(R.id.screen_btn);
        txtTotalCounter = findViewById(R.id.totalCounter);
        lottie = findViewById(R.id.animationView);
        lottie.pauseAnimation();
        sp = PreferenceManager.getDefaultSharedPreferences(this);



        String lastSaveDate = sp.getString("lastSaveDate", "");
        String todayDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        totalCounter = sp.getInt("today", 0);
        txtTotalCounter.setText("Today Total: " + totalCounter);

        String simulatedDate = "2024-09-06";
        savePreviousDayTotal("2024-09-02", 60);
        savePreviousDayTotal("2024-09-03", 40);
        savePreviousDayTotal("2024-09-04", 20);

        if (!lastSaveDate.equals(todayDate)) {
            savePreviousDayTotal(lastSaveDate, totalCounter);
//            Toast.makeText(this, "lastSaveDate " + lastSaveDate, Toast.LENGTH_SHORT).show();
            totalCounter = 0;
            txtTotalCounter.setText("Today Total: " + totalCounter);
            editor = sp.edit();
            editor.putInt("today", totalCounter);
            editor.putString("lastSaveDate", todayDate);
            editor.apply();
        }

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

        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the new page
                Intent intent = new Intent(TasbihActivity.this, DailyDataActivity.class);
                startActivity(intent);
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
        loadDailyTotals();
    }

    public void savePreviousDayTotal(String date, int total) {
        if (!date.isEmpty()) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("total_" + date, total);// save with date as key
            editor.apply();
        }
    }

    public void saveTodayTotal(int counter) {
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sp.edit();

        editor.putInt("today", counter);
        editor.apply();  // Using apply() instead of commit() for asynchronous saving
    }
    private void loadDailyTotals() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        totalList = new ArrayList<>();
        dateList = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        // Loop through SharedPreferences and find all saved totals
        for (String key : sp.getAll().keySet()) {
            if (key.startsWith("total_")) {
                String date = key.replace("total_", "");
                int total = sp.getInt(key, 0);
                totalList.add(total);
                String count = String.valueOf(total);
                dateList.add(new DailyDataModel(date, count));
            }
        }

        // Sort dateList by date
        Collections.sort(dateList, new Comparator<DailyDataModel>() {
            @Override
            public int compare(DailyDataModel o1, DailyDataModel o2) {
                try {
                    return sdf.parse(o1.getDate()).compareTo(sdf.parse(o2.getDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {  // 'home' is the ID of the back arrow button
            finish();  // Close this activity and go back to the previous one
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
