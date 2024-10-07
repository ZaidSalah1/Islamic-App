package com.example.islamicapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamicapp.Activties.Athkar;
import com.example.islamicapp.Activties.NasheedsActivity;
import com.example.islamicapp.Activties.QiblaActivity;
import com.example.islamicapp.Activties.QuranActivity;
import com.example.islamicapp.Activties.QuranSelectionActivity;
import com.example.islamicapp.Activties.TasbihActivity;
import com.example.islamicapp.Models.HorModel;
import com.example.islamicapp.R;

import java.util.ArrayList;

public class HomeHorAdapter extends RecyclerView.Adapter<HomeHorAdapter.MyViewHolder> {

    Context context;
    ArrayList<HorModel> homeHorModelsList;
    int row_index = -1;
    public HomeHorAdapter(Context context, ArrayList<HorModel> homeHorModelsList){
        this.context = context;
        this.homeHorModelsList = homeHorModelsList;
    }

    @NonNull
    @Override
    public HomeHorAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This is where you inflate the layouts (Giving a loock to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.home_horizontal_item, parent, false);


        return new HomeHorAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHorAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // assigning values to the view we created in the home_hor_item layout file
        //base on the position of the recycler view

        holder.textName.setText(homeHorModelsList.get(position).getName());
        holder.imageView.setImageResource(homeHorModelsList.get(position).getImage());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                if(row_index == 0){
                    Intent intent = new Intent(context, QuranSelectionActivity.class);
                    context.startActivity(intent);
                }

                if(row_index == 1){
                    Intent intent = new Intent(context, Athkar.class);
                    context.startActivity(intent);
                }
                if(row_index == 2){
                    Intent intent = new Intent(context, TasbihActivity.class);
                    context.startActivity(intent);
                }

                if(row_index == 5){
                    Intent intent = new Intent(context, NasheedsActivity.class);
                    context.startActivity(intent);
                }

                if(row_index == 4){
                    Intent intent = new Intent(context, QiblaActivity.class);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeHorModelsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // Similar to onCreate method
        // grabbing the view form our recycler home_hor_item layout file
        // kinda like in the ocCreate method

        ImageView imageView;
        TextView textName;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageHor);
            textName = itemView.findViewById(R.id.textHor);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
