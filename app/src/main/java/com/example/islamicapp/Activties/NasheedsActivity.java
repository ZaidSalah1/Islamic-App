package com.example.islamicapp.Activties;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamicapp.Adapter.AudioAdapter;
import com.example.islamicapp.Models.Audio;
import com.example.islamicapp.R;
import java.util.ArrayList;
import java.util.List;

public class NasheedsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AudioAdapter audioAdapter;
    private List<Audio> audioList;
    private TextView tollBarTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasheeds);

        tollBarTxt = findViewById(R.id.toolbar_title);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tollBarTxt.setText("Nasheeds");
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Prepare the list of audio tracks
        audioList = new ArrayList<>();
        audioList.add(new Audio("Nasheed 1", R.raw.test));
        audioList.add(new Audio("Nasheed 2", R.raw.test));
        audioList.add(new Audio("Nasheed 3", R.raw.test));

        // Set up the adapter
        audioAdapter = new AudioAdapter(this, audioList);
        recyclerView.setAdapter(audioAdapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {  // 'home' is the ID of the back arrow button
            finish();  // Close this activity and go back to the previous one
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (audioAdapter != null && audioAdapter.mediaPlayer != null) {
//            audioAdapter.mediaPlayer.release();  // Release MediaPlayer resources
//        }
//    }
}
