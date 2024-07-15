package com.example.islamicapp.Adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamicapp.Models.AyahModel;
import com.example.islamicapp.R;

import java.io.IOException;
import java.util.ArrayList;

public class RLQuranAdapter extends RecyclerView.Adapter<RLQuranAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<AyahModel> ayahList;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;
    private int playingPosition = -1;

    public RLQuranAdapter(Context context, ArrayList<AyahModel> ayahList) {
        this.context = context;
        this.ayahList = ayahList;
        mediaPlayer = new MediaPlayer();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ayah_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AyahModel ayah = ayahList.get(position);

        holder.txtAyahArabic.setText(ayah.getText());
        holder.txtAyahEnglish.setText(ayah.getEngText());

        // Handle play/pause button click
        holder.imgPlayPause.setOnClickListener(v -> {
            if (isPlaying && playingPosition == position) {
                stopAudio();
                holder.imgPlayPause.setImageResource(R.drawable.play_sound_ic);
            } else {
                playAudio(ayah.getAudio());
                holder.imgPlayPause.setImageResource(R.drawable.pause_ic);
                isPlaying = true;
                playingPosition = position;
            }
        });
    }

    @Override
    public int getItemCount() {
        return ayahList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtAyahArabic, txtAyahEnglish;
        ImageView imgPlayPause;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAyahArabic = itemView.findViewById(R.id.txtAyahArabic);
            txtAyahEnglish = itemView.findViewById(R.id.txtAyahEng);
            imgPlayPause = itemView.findViewById(R.id.playSound);
        }
    }

    private void playAudio(String audioUrl) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(audioUrl);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopAudio() {
        mediaPlayer.stop();
        mediaPlayer.reset();
        isPlaying = false;
        playingPosition = -1;
    }

    public void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
