package com.example.islamicapp.Activties;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.example.islamicapp.Adapter.ImagePagerAdapter;
import com.example.islamicapp.FragmentActivities.FragmentOne;
import com.example.islamicapp.R;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class SurahActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ImagePagerAdapter adapter;
    private List<String> imagePaths;

    private TabLayout tabLayout;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah);

        tabLayout = findViewById(R.id.tabLayout);
        fragmentManager = getSupportFragmentManager();

        // Add tabs to TabLayout
        tabLayout.addTab(tabLayout.newTab().setText("Read"));
        tabLayout.addTab(tabLayout.newTab().setText("Read & Listen"));
        tabLayout.addTab(tabLayout.newTab().setText("Listen"));

        // Replace fragment on tab selection
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment selectedFragment = null;
                switch (tab.getPosition()) {
                    case 0:
                     //   selectedFragment = new FragmentOne();
                        break;
                    case 1:
                      //  selectedFragment = new ReadQuran();
                        break;
                    case 2:
                        selectedFragment = new FragmentOne();
                        break;
                }
                if (selectedFragment != null) {
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.fragmentContainer, selectedFragment);
                    transaction.commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Not needed for this example
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Not needed for this example
            }
        });
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.fragmentContainer, new FragmentOne());
//        transaction.commit();

        viewPager = findViewById(R.id.viewPager) ;
        viewPager.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        String surahNumberStr = getIntent().getStringExtra("SURAH_NUMBER");

        imagePaths = new ArrayList<>();
        try {
            if (surahNumberStr != null) {
                int surahNumber = Integer.parseInt(surahNumberStr);
                String[] allFolders = getAssets().list("");

                if (allFolders != null) {
                    for (String folder : allFolders) {
                        // Check if the folder name is a number and is greater than or equal to the given Surah number
                        if (folder.matches("\\d+") && Integer.parseInt(folder) >= surahNumber) {
                            String[] images = getAssets().list(folder);
                            if (images != null) {
                                for (String image : images) {
                                    imagePaths.add(folder + "/" + image);
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        Collections.sort(imagePaths, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int num1 = extractNumber(o1);
                int num2 = extractNumber(o2);
                return Integer.compare(num1, num2);
            }

            private int extractNumber(String path) {
                String fileName = path.substring(path.lastIndexOf('/') + 1);
                String numberPart = fileName.replaceAll("\\D", "");
                return numberPart.isEmpty() ? 0 : Integer.parseInt(numberPart);
            }
        });

        adapter = new ImagePagerAdapter(this, imagePaths);
        viewPager.setAdapter(adapter);
    }
}


