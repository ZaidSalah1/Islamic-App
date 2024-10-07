package com.example.islamicapp.Activties;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.islamicapp.Adapter.DailyDataAdapter;
import com.example.islamicapp.Models.DailyDataModel;
import com.example.islamicapp.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class DailyDataActivity extends AppCompatActivity {

    private RecyclerView dailyDataRecyclerView;
    private DailyDataAdapter adapter;
    private ArrayList<String> dailyDataList;

    private TextView tollBarTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_data);

        tollBarTxt = findViewById(R.id.toolbar_title);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tollBarTxt.setText("Tasbih Data");
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dailyDataRecyclerView = findViewById(R.id.dailyDataRecyclerView);
        dailyDataRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        dailyDataList = new ArrayList<>();
        dailyDataList = getDailyCountData();

        // Set the adapter
        adapter = new DailyDataAdapter(this, TasbihActivity.dateList);
        dailyDataRecyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {  // 'home' is the ID of the back arrow button
            finish();  // Close this activity and go back to the previous one
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private ArrayList<String> getDailyCountData() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> dailyDataSet = sp.getStringSet("daily_data", new HashSet<>());
        return new ArrayList<>(dailyDataSet);
    }
}
