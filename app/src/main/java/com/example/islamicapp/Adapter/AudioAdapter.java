package com.example.islamicapp.Adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamicapp.AudioService;
import com.example.islamicapp.Models.Audio;
import com.example.islamicapp.R;
import java.util.List;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.AudioViewHolder> {

    private List<Audio> audioList;
    private Context context;
    private int currentPlayingPosition = -1;

    public AudioAdapter(Context context, List<Audio> audioList) {
        this.context = context;
        this.audioList = audioList;
    }

    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nasheed_audio, parent, false);
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
        Audio audio = audioList.get(position);
        holder.audioTitle.setText(audio.getTitle());

        holder.playPauseButton.setOnClickListener(v -> {
            // Start the foreground service to play the selected audio
            playAudio(audio.getAudioResId());
            currentPlayingPosition = position;
        });
    }

    private void playAudio(int audioResId) {
        // Stop the current playing audio if any
        if (currentPlayingPosition != -1) {
            context.stopService(new Intent(context, AudioService.class));
        }

        // Start the Foreground Service to play the audio
        Intent serviceIntent = new Intent(context, AudioService.class);
        serviceIntent.putExtra("audioResId", audioResId);
        context.startService(serviceIntent);
    }

    @Override
    public int getItemCount() {
        return audioList.size();
    }

    public static class AudioViewHolder extends RecyclerView.ViewHolder {
        TextView audioTitle;
        ImageView playPauseButton;

        public AudioViewHolder(@NonNull View itemView) {
            super(itemView);
            audioTitle = itemView.findViewById(R.id.audioTitle);
            playPauseButton = itemView.findViewById(R.id.playPauseButton);
        }
    }
}
