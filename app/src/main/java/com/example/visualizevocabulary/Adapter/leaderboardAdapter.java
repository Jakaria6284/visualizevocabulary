package com.example.visualizevocabulary.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.visualizevocabulary.Model.leaderboardModel;
import com.example.visualizevocabulary.R;

import java.util.List;

public class leaderboardAdapter extends RecyclerView.Adapter<leaderboardAdapter.viewHolder> {

    List<leaderboardModel>leaderboardModelList;

    public leaderboardAdapter(List<leaderboardModel> leaderboardModelList) {
        this.leaderboardModelList = leaderboardModelList;
    }

    @NonNull
    @Override
    public leaderboardAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull leaderboardAdapter.viewHolder holder, int position) {
          String leader=leaderboardModelList.get(position).getName();

          holder.setData(leader);
    }

    @Override
    public int getItemCount() {
        return leaderboardModelList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.leaderborardName);
        }
        public void setData(String n)
        {
            textView.setText(n);
        }
    }
}
