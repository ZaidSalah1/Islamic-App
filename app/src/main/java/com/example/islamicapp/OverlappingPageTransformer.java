package com.example.islamicapp;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

public class OverlappingPageTransformer implements ViewPager2.PageTransformer {
    private final float overlapPercentage;

    public OverlappingPageTransformer(float overlapPercentage) {
        this.overlapPercentage = overlapPercentage;
    }

    @Override
    public void transformPage(@NonNull View page, float position) {
        page.setTranslationX(-position * page.getWidth() * overlapPercentage);
    }
}