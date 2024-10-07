package com.example.islamicapp.Athkars;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.islamicapp.Adapter.AthkarAdapter;
import com.example.islamicapp.Models.AthkarModel;
import com.example.islamicapp.R;

import java.util.ArrayList;

public class MosqueAthkar extends AppCompatActivity {

    private RecyclerView athkarhRecyclerView;
    private AthkarAdapter athkarAdapter;
    private ArrayList<AthkarModel> athkarModelsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salah_athkar);

        athkarData();

        athkarhRecyclerView = findViewById(R.id.list);
        athkarhRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        athkarAdapter = new AthkarAdapter(this, athkarModelsList);
        athkarhRecyclerView.setAdapter(athkarAdapter);


        LottieAnimationView lottieAnimationView = findViewById(R.id.athkar_done);
        AthkarAdapter adapter = new AthkarAdapter(this, athkarModelsList, lottieAnimationView);
        athkarhRecyclerView.setAdapter(adapter);

    }

    private void athkarData(){
        //"اللَّهُ لَا إِلَهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ لَا تَأْخُذُهُ سِنَةٌ وَلَا نَوْمٌ لَهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الْأَرْضِ مَنْ ذَا الَّذِي يَشْفَعُ عِنْدَهُ إِلَّا بِإِذْنِهِ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ وَلَا يُحِيطُونَ بِشَيْءٍ مِنْ عِلْمِهِ إِلَّا بِمَا شَاءَ وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالْأَرْضَ وَلاَ يَؤُودُهُ حِفْظُهُمَا وَهُوَ الْعَلِيُّ الْعَظِيمُ "
        // athkarModelsList.add(new AthkarModel(3 ,""));
        athkarModelsList = new ArrayList<>();
        athkarModelsList.add(new AthkarModel(1,"دعاء دخول المسجد \n " +
                "اللَّهُمَّ اجْعَلْ فِي قَلْبِي نُورًا، وَفِي بَصَرِي نُورًا، وَفِي سَمْعِي نُورًا، وَعَنْ يَمِينِي نُورًا، وَعَنْ يَسَارِي نُورًا، وَفَوْقِي نُورًا، وَتَحْتِي نُورًا، وَأَمَامِي نُورًا، وَخَلْفِي نُورًا، وَاجْعَلْ لِي نُورًا"));

        athkarModelsList.add(new AthkarModel(1,"دعاء دخول المسجد \n " +
                "إذا دخل أحدُكم المسجدَ فليُسلِّم على النبي ﷺ، ثم ليقل: اللهم افتح لي أبوابَ رحمتك"));

        athkarModelsList.add(new AthkarModel(1,"دعاء الخروج من المسجد \n " +
                "((يَبْدَأُ بِرِجْلِهِ الْيُسْرَى)) وَيَقُولُ: ((بِسْمِ اللَّهِ وَالصّلَاةُ وَالسَّلَامُ عَلَى رَسُولِ اللَّهِ، اللَّهُمَّ إِنِّي أَسْأَلُكَ مِنْ فَضْلِك، اللَّهُمَّ اعْصِمْنِي مِنَ الشَّيْطَانِ الرَّجِيمِ))."));

    }
}