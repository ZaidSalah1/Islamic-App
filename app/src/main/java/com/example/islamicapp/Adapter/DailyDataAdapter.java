package com.example.islamicapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.islamicapp.Models.DailyDataModel;
import com.example.islamicapp.R;

import java.util.ArrayList;

public class DailyDataAdapter extends RecyclerView.Adapter<DailyDataAdapter.ViewHolder> {
    private ArrayList<DailyDataModel> dailyDataList;
    private Context context;

    public DailyDataAdapter(Context context, ArrayList<DailyDataModel> dailyDataList) {
        this.context = context;
        this.dailyDataList = dailyDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_daily_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        String dailyData = dailyDataList.get(position);
//
//        // Split the string "date:totalCount" to display
//        String[] parts = dailyData.split(":");
//        String date = parts[0];
//        String count = parts[1];

        String date = dailyDataList.get(position).getDate();
        String count = dailyDataList.get(position).getCounter();

        holder.dateTextView.setText(date);
        holder.countTextView.setText(" Total: " + count);
    }

    @Override
    public int getItemCount() {
        return dailyDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView, countTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            countTextView = itemView.findViewById(R.id.countTextView);
        }
    }
}
