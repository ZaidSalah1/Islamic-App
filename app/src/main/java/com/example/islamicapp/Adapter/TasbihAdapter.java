package com.example.islamicapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamicapp.Models.AthkarModel;
import com.example.islamicapp.R;

import java.util.ArrayList;

public class TasbihAdapter extends RecyclerView.Adapter<TasbihAdapter.MyViewHolder> {

    Context context;
    ArrayList<AthkarModel> athkarModelsList;
    int width;

    public TasbihAdapter(Context context, ArrayList<AthkarModel> athkarModelsList, int width) {
        this.context = context;
        this.athkarModelsList = athkarModelsList;
        this.width = width;
    }

    @NonNull
    @Override
    public TasbihAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.tasbih_item, parent, false);
        return new TasbihAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasbihAdapter.MyViewHolder holder, int position) {
        holder.text.setText(athkarModelsList.get(position).getText());
        holder.counterText.setText(String.valueOf(athkarModelsList.get(position).getCounter()));

//       holder.cardView.getLayoutParams().height = ;
        if (position == athkarModelsList.size() - 1 || position == 0) {
            holder.cardView.getLayoutParams().width = width - 100;
        }
        else{
            holder.cardView.getLayoutParams().width = width - 200;

        }
    }

    @Override
    public int getItemCount() {
        return athkarModelsList.size();
    }

    public void incrementCounterAtPosition(int position) {
        athkarModelsList.get(position).incrementCounter();
        notifyItemChanged(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        TextView counterText;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.txtTasbih);
            cardView = itemView.findViewById(R.id.odehCardView);
            counterText = itemView.findViewById(R.id.counterText);

        }
    }
}
