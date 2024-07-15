package com.example.islamicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamicapp.Activties.ReadListen;
import com.example.islamicapp.Activties.SurahActivity;
import com.example.islamicapp.Models.QuranModel;
import com.example.islamicapp.R;

import java.util.ArrayList;

public class ReadQuranAdapter extends RecyclerView.Adapter<ReadQuranAdapter.MyViewHolder> {

    Context context;
    ArrayList<QuranModel> quranModelsList;

    public ReadQuranAdapter(Context context, ArrayList<QuranModel> quranModels) {
        this.context = context;
        this.quranModelsList = quranModels;
    }

    @NonNull
    @Override
    public ReadQuranAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.quran_item, parent, false);

        return new ReadQuranAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadQuranAdapter.MyViewHolder holder, int position) {
        holder.txtSurahArabicName.setText(quranModelsList.get(position).getTxtSurahArbicName());
        holder.txtSurahEngName.setText(quranModelsList.get(position).getTxtSurahEngName());
        holder.txtSurahEng2Name.setText(quranModelsList.get(position).getTxtSurahEng2Name());
        String surahName = quranModelsList.get(position).getTxtSurahEngName();
        String surahNumber = quranModelsList.get(position).getNumber();
        holder.txtSurahNumber.setText(surahNumber);
//        holder.itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(context, SurahActivity.class);
//            //  Toast.makeText(context, surahName, Toast.LENGTH_SHORT).show();
//            intent.putExtra("SURAH_NUMBER", surahNumber);
//            context.startActivity(intent);
//        });
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ReadListen.class);
            //  Toast.makeText(context, surahName, Toast.LENGTH_SHORT).show();
            intent.putExtra("SURAH_NUMBER", surahNumber);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return quranModelsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtSurahArabicName;
        TextView txtSurahEngName;
        TextView txtSurahEng2Name;
        TextView txtSurahNumber;
        CardView cardView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSurahArabicName = itemView.findViewById(R.id.txtsurahArName);
            txtSurahEngName = itemView.findViewById(R.id.txtsurahEngName);
            txtSurahEng2Name = itemView.findViewById(R.id.txtsurahEng2Name);
            txtSurahNumber = itemView.findViewById(R.id.txtSurahNumber);
            cardView = itemView.findViewById(R.id.quran_item);
        }
    }
}
