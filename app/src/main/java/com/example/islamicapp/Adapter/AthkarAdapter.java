package com.example.islamicapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.islamicapp.Models.AthkarModel;
import com.example.islamicapp.R;

import java.util.ArrayList;

public class AthkarAdapter extends RecyclerView.Adapter<AthkarAdapter.MyViewHolder> {

    Context context;
    ArrayList<AthkarModel> athkarModelsList;
    LottieAnimationView lottieAnimationView;

    public AthkarAdapter(Context context, ArrayList<AthkarModel> athkarModelsList ) {
        this.context = context;
        this.athkarModelsList = athkarModelsList;
    }

    public AthkarAdapter(Context context, ArrayList<AthkarModel> athkarModelsList, LottieAnimationView lottieAnimationView) {
        this.context = context;
        this.athkarModelsList = athkarModelsList;
        this.lottieAnimationView = lottieAnimationView;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.athkar_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtButton.setText(0 + " / " + athkarModelsList.get(position).getGoal());
        holder.text.setText(athkarModelsList.get(position).getText());

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                athkarModelsList.get(position).incrementCounter();
                int goal = athkarModelsList.get(position).getGoal();
                int counter = athkarModelsList.get(position).getCounter();

                if (counter <= goal) {
                    holder.btn.setText(counter + " / " + goal);
                }
                if (counter >= goal) {
                    holder.cardView.animate()
                            .alpha(0f)
                            .setDuration(300)
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    removeAt(position);
                                    if (athkarModelsList.size() == 0) {
                                        showCompletionAnimation();
                                    }
                                }
                            })
                            .start();
                }
            }
        });
    }

    private void showCompletionAnimation() {
        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.playAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (context instanceof AppCompatActivity) {
                    ((AppCompatActivity) context).finish();
                }
            }
        }, 1000); // 1000 ms = 1 second

    }

    public void removeAt(int position) {
        athkarModelsList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, athkarModelsList.size());
    }

    @Override
    public int getItemCount() {
        return athkarModelsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        View cardView;
        Button txtButton;
        Button btn;
        TextView text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtButton = itemView.findViewById(R.id.btnCount);
            text = itemView.findViewById(R.id.text);
            btn = itemView.findViewById(R.id.btnCount);
            cardView = itemView.findViewById(R.id.cardViewAthkar);
        }
    }
}
