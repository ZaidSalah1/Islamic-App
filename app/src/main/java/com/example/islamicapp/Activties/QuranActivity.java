package com.example.islamicapp.Activties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.islamicapp.Adapter.ReadQuranAdapter;
import com.example.islamicapp.Models.QuranModel;
import com.example.islamicapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class QuranActivity extends AppCompatActivity {

    private RecyclerView quranSurahLists;
    private ReadQuranAdapter readQuranAdapter;
    private ArrayList<QuranModel> quranModelArrayList;
    private ArrayList<String> surahNamesArabic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran);

        quranSurahLists = findViewById(R.id.surahsNames_list);
        quranSurahLists.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        quranModelArrayList = new ArrayList<>();  // Initialize the ArrayList


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "http://api.alquran.cloud/v1/meta";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getInt("code") == 200) {
                                JSONObject data = jsonObject.getJSONObject("data");

                                JSONObject ayahs = data.getJSONObject("ayahs");
                                int ayahCount = ayahs.getInt("count");

                                JSONObject surahs = data.getJSONObject("surahs");
                                int surahCount = surahs.getInt("count");
                                JSONArray references = surahs.getJSONArray("references");

                                Log.d("API Response", "Ayahs: " + ayahCount + ", Surahs: " + surahCount);
                                for (int i = 0; i < references.length(); i++) {
                                    JSONObject surah = references.getJSONObject(i);
                                    String number = surah.getString("number");
                                    String tmpName = surah.getString("name");
                                    String name = tmpName.replaceAll("[\\u0610-\\u061A\\u064B-\\u065F\\u06D6-\\u06DC\\u06DF-\\u06E4\\u06E7-\\u06E8\\u06EA-\\u06ED]", "");
                                    String englishName = surah.getString("englishName");
                                    String englishNameTranslation = surah.getString("englishNameTranslation");
                                    int numberOfAyahs = surah.getInt("numberOfAyahs");
                                    String revelationType = surah.getString("revelationType");
                                    quranModelArrayList.add(new QuranModel(name, englishName, englishNameTranslation, number));

                                }
                                surahArabic();
                                for (int i = 0; i < surahNamesArabic.size(); i++) {
                                    quranModelArrayList.get(i).setTxtSurahArbicName(surahNamesArabic.get(i));
                                }

                                readQuranAdapter = new ReadQuranAdapter(QuranActivity.this, quranModelArrayList);
                                quranSurahLists.setAdapter(readQuranAdapter);

                            } else {
                                Log.e("API Response", "Error: " + jsonObject.getString("status"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("API Response", "Error: " + error.toString());
                    }
                });
        requestQueue.add(stringRequest);
    }


    public void surahArabic() {
        surahNamesArabic = new ArrayList<>(Arrays.asList(
                "سورة الفاتحة",
                "سورة البقرة",
                "سورة آل عمران",
                "سورة النساء",
                "سورة المائدة",
                "سورة الأنعام",
                "سورة الأعراف",
                "سورة الأنفال",
                "سورة التوبة",
                "سورة يونس",
                "سورة هود",
                "سورة يوسف",
                "سورة الرعد",
                "سورة إبراهيم",
                "سورة الحجر",
                "سورة النحل",
                "سورة الإسراء",
                "سورة الكهف",
                "سورة مريم",
                "سورة طه",
                "سورة الأنبياء",
                "سورة الحج",
                "سورة المؤمنون",
                "سورة النور",
                "سورة الفرقان",
                "سورة الشعراء",
                "سورة النمل",
                "سورة القصص",
                "سورة العنكبوت",
                "سورة الروم",
                "سورة لقمان",
                "سورة السجدة",
                "سورة الأحزاب",
                "سورة سبأ",
                "سورة فاطر",
                "سورة يس",
                "سورة الصافات",
                "سورة ص",
                "سورة الزمر",
                "سورة غافر",
                "سورة فصلت",
                "سورة الشورى",
                "سورة الزخرف",
                "سورة الدخان",
                "سورة الجاثية",
                "سورة الأحقاف",
                "سورة محمد",
                "سورة الفتح",
                "سورة الحجرات",
                "سورة ق",
                "سورة الذاريات",
                "سورة الطور",
                "سورة النجم",
                "سورة القمر",
                "سورة الرحمن",
                "سورة الواقعة",
                "سورة الحديد",
                "سورة المجادلة",
                "سورة الحشر",
                "سورة الممتحنة",
                "سورة الصف",
                "سورة الجمعة",
                "سورة المنافقون",
                "سورة التغابن",
                "سورة الطلاق",
                "سورة التحريم",
                "سورة الملك",
                "سورة القلم",
                "سورة الحاقة",
                "سورة المعارج",
                "سورة نوح",
                "سورة الجن",
                "سورة المزمل",
                "سورة المدثر",
                "سورة القيامة",
                "سورة الإنسان",
                "سورة المرسلات",
                "سورة النبأ",
                "سورة النازعات",
                "سورة عبس",
                "سورة التكوير",
                "سورة الإنفطار",
                "سورة المطففين",
                "سورة الإنشقاق",
                "سورة البروج",
                "سورة الطارق",
                "سورة الأعلى",
                "سورة الغاشية",
                "سورة الفجر",
                "سورة البلد",
                "سورة الشمس",
                "سورة الليل",
                "سورة الضحى",
                "سورة الشرح",
                "سورة التين",
                "سورة العلق",
                "سورة القدر",
                "سورة البينة",
                "سورة الزلزلة",
                "سورة العاديات",
                "سورة القارعة",
                "سورة التكاثر",
                "سورة العصر",
                "سورة الهمزة",
                "سورة الفيل",
                "سورة قريش",
                "سورة الماعون",
                "سورة الكوثر",
                "سورة الكافرون",
                "سورة النصر",
                "سورة المسد",
                "سورة الإخلاص",
                "سورة الفلق",
                "سورة الناس"
        ));
    }

}
