package com.example.islamicapp.Activties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.islamicapp.Adapter.AthkarAdapter;
import com.example.islamicapp.Models.AthkarModel;
import com.example.islamicapp.Models.HorModel;
import com.example.islamicapp.R;
import com.example.islamicapp.Athkars.EaveningAthkar;
import com.example.islamicapp.Athkars.MorningAthkar;
import com.example.islamicapp.Athkars.SalahAthkar;

import java.util.ArrayList;

public class Athkar extends AppCompatActivity {


    private RecyclerView athkarhRecyclerView;
    private AthkarAdapter athkarAdapter;
    private ArrayList<AthkarModel> athkarModelsList;

    private RecyclerView hRecyclerView;
    private ArrayList<HorModel> athkarHorModelsList;
    private CardView morning_athkar,eavening_athkar,salah_athkar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athkar);

        morning_athkar = findViewById(R.id.morning_athkar);
        morning_athkar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Athkar.this, MorningAthkar.class);
                startActivity(intent);
            }
        });

        eavening_athkar = findViewById(R.id.eavening_athkar);
        eavening_athkar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Athkar.this, EaveningAthkar.class);
                startActivity(intent);
            }
        });

        salah_athkar = findViewById(R.id.salah_athkar_cardView);
        salah_athkar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Athkar.this, SalahAthkar.class);
                startActivity(intent);
            }
        });

       // athkarData();

        //        Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle("Athkar");
//        setSupportActionBar(toolbar);

//        athkarhRecyclerView = findViewById(R.id.athkar_lists);
//        athkarhRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//
//        athkarAdapter = new AthkarAdapter(this, athkarModelsList);
//        athkarhRecyclerView.setAdapter(athkarAdapter);
//
//
//        athkarHorData();
//        hRecyclerView = findViewById(R.id.athkar_hor_recylist);
//        hRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
//
//        athkarHorAdapter = new AthkarHorAdapter(this, athkarHorModelsList);
//        hRecyclerView.setAdapter(athkarHorAdapter);


    }
    private void athkarData(){//"اللَّهُ لَا إِلَهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ لَا تَأْخُذُهُ سِنَةٌ وَلَا نَوْمٌ لَهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الْأَرْضِ مَنْ ذَا الَّذِي يَشْفَعُ عِنْدَهُ إِلَّا بِإِذْنِهِ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ وَلَا يُحِيطُونَ بِشَيْءٍ مِنْ عِلْمِهِ إِلَّا بِمَا شَاءَ وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالْأَرْضَ وَلاَ يَؤُودُهُ حِفْظُهُمَا وَهُوَ الْعَلِيُّ الْعَظِيمُ "

        athkarModelsList = new ArrayList<>();
        athkarModelsList.add(new AthkarModel(1,"اللَّهُ لَا إِلَهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ لَا تَأْخُذُهُ سِنَةٌ وَلَا نَوْمٌ لَهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الْأَرْضِ مَنْ ذَا الَّذِي يَشْفَعُ عِنْدَهُ إِلَّا بِإِذْنِهِ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ وَلَا يُحِيطُونَ بِشَيْءٍ مِنْ عِلْمِهِ إِلَّا بِمَا شَاءَ وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالْأَرْضَ وَلاَ يَؤُودُهُ حِفْظُهُمَا وَهُوَ الْعَلِيُّ الْعَظِيمُ "));

        athkarModelsList.add(new AthkarModel(3,"قُلۡ هُوَ ٱللَّهُ أَحَدٌ (1) ٱللَّهُ ٱلصَّمَدُ (2) لَمۡ يَلِدۡ وَلَمۡ يُولَدۡ (3) وَلَمۡ يَكُن لَّهُۥ كُفُوًا أَحَدُۢ (4) \n\n " +
                " قُلۡ أَعُوذُ بِرَبِّ ٱلۡفَلَقِ (1) مِن شَرِّ مَا خَلَقَ (2) وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ (3) وَمِن شَرِّ ٱلنَّفَّٰثَٰتِ فِي ٱلۡعُقَدِ (4) وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ (5)\n\n"
        + "قُلۡ أَعُوذُ بِرَبِّ ٱلنَّاسِ (1) مَلِكِ ٱلنَّاسِ (2) إِلَٰهِ ٱلنَّاسِ (3) مِن شَرِّ ٱلۡوَسۡوَاسِ ٱلۡخَنَّاسِ (4) ٱلَّذِي يُوَسۡوِسُ فِي صُدُورِ ٱلنَّاسِ (5) مِنَ ٱلۡجِنَّةِ وَٱلنَّاسِ (6)"));
        athkarModelsList.add(new AthkarModel(1,"أَصْـبَحْنا وَأَصْـبَحَ المُـلْكُ لله وَالحَمدُ لله ، لا إلهَ إلاّ اللّهُ وَحدَهُ لا شَريكَ لهُ، لهُ المُـلكُ ولهُ الحَمْـد، وهُوَ على كلّ شَيءٍ قدير ، رَبِّ أسْـأَلُـكَ خَـيرَ ما في هـذا اليوم وَخَـيرَ ما بَعْـدَه ، وَأَعـوذُ بِكَ مِنْ شَـرِّ ما في هـذا اليوم وَشَرِّ ما بَعْـدَه، رَبِّ أَعـوذُبِكَ مِنَ الْكَسَـلِ وَسـوءِ الْكِـبَر ، رَبِّ أَعـوذُ بِكَ مِنْ عَـذابٍ في النّـارِ وَعَـذابٍ في القَـبْر."));

        athkarModelsList.add(new AthkarModel(1,"اللَّهُمَّ أَنْتَ رَبِّي ، لا إِلهَ إِلاَّ أَنْتَ ، خَلَقْتَني وأَنَا عَبْدُكَ ،وَأَنا  عَلى عَهْدِكَ وَوَعْدِكَ ما اسْتَطَعْت ، أَعُوذُ بِكَ مِنْ شَرِّ ما صَنَعْتُ ، أَبوءُ لَكَ بِنِعْمَتِكَ عَلَيَّ وأَبُوءُ بِذَنْبي فَاغْفِرْ لي فَإِنَّهُ لا يَغْفِرُ الذُّنُوبِ إِلاَّ أَنْتَ"));

        athkarModelsList.add(new AthkarModel(1,"اللهم بك أصبحنا وبك أمسينا وبك نحيا وبك نموت وإليك النشور"));

        athkarModelsList.add(new AthkarModel(3,"اللهم عافني في بدني اللهم عافني في سمعي اللهم عافني في بصري لا إله إلا أنت"));

        athkarModelsList.add(new AthkarModel(4,"اللَّهُمَّ إِنِّي أَصْبَحْتُ أُشْهِدُكَ وَأُشْهِدُ حَمَلَةَ عَرْشِكَ وَمَلَائِكَتَكَ، وَجَمِيعَ خَلْقِكَ أَنَّكَ أَنْتَ اللَّهُ لَا إِلَهَ إِلَّا أَنْتَ وَأَنَّ مُحَمَّدًا عَبْدُكَ وَرَسُولُكَ"));

        athkarModelsList.add(new AthkarModel(7,"حَسْبِيَ اللَّهُ لَا إِلَٰهَ إِلَّا هُوَ عَلَيْهِ تَوَكَّلْتُ وَهُوَ رَبُّ الْعَرْشِ الْعَظِيمِ"));

        athkarModelsList.add(new AthkarModel(1,"اللَّهُمَّ إِنِّي أَسْأَلُكَ الْعَافِيَةَ فِي الدُّنْيَا وَالآخِرَةِ، اللَّهُمَّ إِنِّي أَسْأَلُكَ الْعَفْوَ وَالْعَافِيَةَ فِي دِينِي وَدُنْيَايَ وَأَهْلِي وَمَالِي، اللَّهُمَّ استُرْ عَوْرَاتي، وآمِنْ رَوْعَاتي، اللَّهمَّ احْفَظْنِي مِنْ بَينِ يَدَيَّ، ومِنْ خَلْفي، وَعن يَميني، وعن شِمالي، ومِن فَوْقِي، وأعُوذُ بِعَظَمَتِكَ أنْ أُغْتَالَ مِنْ تَحتي"));

        athkarModelsList.add(new AthkarModel(3,"بِسْمِ اللَّهِ الَّذِي لَا يَضُرُّ مَعَ اسْمِهِ شَيْءٌ فِي الْأَرْضِ وَلَا فِي السَّمَاءِ وَهُوَ السَّمِيعُ الْعَلِيمُ"));

        athkarModelsList.add(new AthkarModel(3,"رَضيتُ بِالله رَبًّا، وَبِالإسْلامِ دينًا، وَبِمُحَمَّدٍ صلي الله عليه وسلم نَبِيًّا"));

        athkarModelsList.add(new AthkarModel(3,"أَعُوذُ بِكَلِمَاتِ اللهِ التَّامَّةِ مِنْ شَرِّ مَا خَلَقَ"));

        athkarModelsList.add(new AthkarModel(3,"يَا حَيُّ يَا قَيُّومُ بِرَحْمَتِكَ أَسْتَغِيثُ أَصْلِحْ لِي شَأْنِي كُلَّهُ وَلا تَكِلْنِي إِلَى نَفْسِي طَرَفَةَ عَيْنٍ"));

        athkarModelsList.add(new AthkarModel(1,"أَصْبَـحْـنا وَأَصْبَـحْ المُـلكُ للهِ رَبِّ العـالَمـين ، اللّهُـمَّ إِنِّـي أسْـأَلُـكَ خَـيْرَ هـذا الـيَوْم ، فَـتْحَهُ ، وَنَصْـرَهُ ، وَنـورَهُ وَبَـرَكَتَـهُ ، وَهُـداهُ ، وَأَعـوذُ بِـكَ مِـنْ شَـرِّ ما فـيهِ وَشَـرِّ ما بَعْـدَه "));

        athkarModelsList.add(new AthkarModel(1,"أَصْبَـحْـنا عَلَى فِطْرَةِ الإسْلاَمِ، وَعَلَى كَلِمَةِ الإِخْلاَصِ، وَعَلَى دِينِ نَبِيِّنَا مُحَمَّدٍ صَلَّى اللهُ عَلَيْهِ وَسَلَّمَ، وَعَلَى مِلَّةِ أَبِينَا إبْرَاهِيمَ حَنِيفاً مُسْلِماً وَمَا كَانَ مِنَ المُشْرِكِينَ"));

        athkarModelsList.add(new AthkarModel(10,"لَا إلَه إلّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَى كُلِّ شَيْءِ قَدِيرِ"));

        athkarModelsList.add(new AthkarModel(3,"سُبْحـانَ اللهِ وَبِحَمْـدِهِ عَدَدَ خَلْـقِه ، وَرِضـا نَفْسِـه ، وَزِنَـةَ عَـرْشِـه ، وَمِـدادَ كَلِمـاتِـه"));


    }

    private void athkarHorData(){//"اللَّهُ لَا إِلَهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ لَا تَأْخُذُهُ سِنَةٌ وَلَا نَوْمٌ لَهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الْأَرْضِ مَنْ ذَا الَّذِي يَشْفَعُ عِنْدَهُ إِلَّا بِإِذْنِهِ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ وَلَا يُحِيطُونَ بِشَيْءٍ مِنْ عِلْمِهِ إِلَّا بِمَا شَاءَ وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالْأَرْضَ وَلاَ يَؤُودُهُ حِفْظُهُمَا وَهُوَ الْعَلِيُّ الْعَظِيمُ "
        athkarHorModelsList = new ArrayList<>();
        athkarHorModelsList.add(new HorModel("Morning Athkar", R.drawable.sunn_ic));
        athkarHorModelsList.add(new HorModel("Evening Athkar", R.drawable.night_ic));
        athkarHorModelsList.add(new HorModel("House Athkar", R.drawable.home_ic));
        athkarHorModelsList.add(new HorModel("Sleep Athkar", R.drawable.sleep));
    }

}