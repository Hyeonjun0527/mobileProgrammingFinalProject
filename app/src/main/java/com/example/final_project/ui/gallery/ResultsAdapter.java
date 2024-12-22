package com.example.final_project.ui.gallery;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.R;

import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder> {

    private final List<String[]> results;

    public ResultsAdapter(List<String[]> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public ResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_result, parent, false);
        return new ResultsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultsViewHolder holder, int position) {

        holder.tvNumber.setTextColor(Color.BLACK);
        holder.tvPlayerName.setTextColor(Color.BLACK);
        holder.tvScore.setTextColor(Color.BLACK);
        String[] result = results.get(position);
        holder.tvNumber.setText(String.valueOf(position + 1));
        holder.tvPlayerName.setText("이름 : " + result[0]);
        holder.tvScore.setText("대학: " + result[1]);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    static class ResultsViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumber, tvPlayerName, tvScore;

        public ResultsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tv_number);
            tvPlayerName = itemView.findViewById(R.id.tv_player_name);
            tvScore = itemView.findViewById(R.id.tv_score);
        }
    }
}
