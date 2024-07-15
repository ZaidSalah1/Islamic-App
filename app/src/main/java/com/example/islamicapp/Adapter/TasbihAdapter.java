package com.example.islamicapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamicapp.Models.AthkarModel;
import com.example.islamicapp.R;

import java.util.ArrayList;

public class TasbihAdapter extends RecyclerView.Adapter<TasbihAdapter.MyViewHolder> {

    Context context;
    ArrayList<AthkarModel> athkarModelsList;

    public TasbihAdapter(Context context, ArrayList<AthkarModel> athkarModelsList) {
        this.context = context;
        this.athkarModelsList = athkarModelsList;
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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.txtTasbih);
            counterText = itemView.findViewById(R.id.counterText);
        }
    }
}
