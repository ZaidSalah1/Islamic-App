package com.example.islamicapp.Activties;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.islamicapp.Adapter.RLQuranAdapter;
import com.example.islamicapp.Models.AyahModel;
import com.example.islamicapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReadListen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RLQuranAdapter adapter;
    private ArrayList<AyahModel> list;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_listen);

        recyclerView = findViewById(R.id.readListenList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new RLQuranAdapter(ReadListen.this, list);
        recyclerView.setAdapter(adapter);

        queue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        String number = intent.getStringExtra("SURAH_NUMBER");

        fetchSurahData(number);
    }

    private void fetchSurahData(String number) {
        String url = "http://api.alquran.cloud/v1/surah/" + number + "/ar.alafasy";
        String url2 = "http://api.alquran.cloud/v1/surah/" + number + "/en.asad";
        String url3 = "http://api.alquran.cloud/v1/surah/" + number;

        // Request for Arabic text
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            JSONArray ayahs = data.getJSONArray("ayahs");

                            list.clear(); // Clear the list before adding new data
                            for (int j = 0; j < ayahs.length(); j++) {
                                JSONObject ayah = ayahs.getJSONObject(j);

                                int ayahNumber = ayah.getInt("number");
                                String audio = ayah.getString("audio");
                                int numberInSurah = ayah.getInt("numberInSurah");
                                int juz = ayah.getInt("juz");
                                int manzil = ayah.getInt("manzil");
                                int page = ayah.getInt("page");
                                int ruku = ayah.getInt("ruku");
                                int hizbQuarter = ayah.getInt("hizbQuarter");

                                // Create AyahModel object
                                AyahModel ayahModel = new AyahModel(ayahNumber, audio, numberInSurah, juz, manzil, page, ruku, hizbQuarter);
                                list.add(ayahModel);
                            }

                            // Notify adapter of data change
                            adapter.notifyDataSetChanged();

                            // After fetching Arabic text, fetch English text
                            fetchEnglishText(url2, url3);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ReadListen.this, "Error parsing Arabic JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(ReadListen.this, "Error fetching Arabic data", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(request);
    }

    private void fetchEnglishText(String url2, String url3) {
        // Request for English text
        JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, url2, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            JSONArray ayahs = data.getJSONArray("ayahs");

                            for (int j = 0; j < ayahs.length(); j++) {
                                JSONObject ayah = ayahs.getJSONObject(j);
                                String textEng = ayah.getString("text");

                                if (j < list.size()) {
                                    list.get(j).setEngText(textEng);
                                }
                            }

                            // Notify adapter of data change after updating English text
                            adapter.notifyDataSetChanged();

                            // After fetching English text, fetch Arabic text again for updated text
                            fetchArabicText(url3);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ReadListen.this, "Error parsing English JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(ReadListen.this, "Error fetching English data", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(request2);
    }

    private void fetchArabicText(String url3) {
        // Request for Arabic text again to update if there were changes
        JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.GET, url3, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            JSONArray ayahs = data.getJSONArray("ayahs");

                            for (int j = 0; j < ayahs.length(); j++) {
                                JSONObject ayah = ayahs.getJSONObject(j);
                                String text = ayah.getString("text").replace("\n","");
                                if (j < list.size()) {
                                    list.get(j).setText(text);
                                }
                            }

                            // Notify adapter of final data change after updating Arabic text
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ReadListen.this, "Error parsing Arabic JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(ReadListen.this, "Error fetching Arabic data", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(request3);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release the MediaPlayer resources
        adapter.releaseMediaPlayer();
    }
}
