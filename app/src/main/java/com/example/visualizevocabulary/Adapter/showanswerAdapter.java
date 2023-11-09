package com.example.visualizevocabulary.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.visualizevocabulary.Model.showanswerModel;
import com.example.visualizevocabulary.R;
import com.example.visualizevocabulary.playerActivity;

import java.util.List;

public class showanswerAdapter extends RecyclerView.Adapter<showanswerAdapter.viewHolder> {

    List<showanswerModel> showanswerModelList;

    public showanswerAdapter(List<showanswerModel> showanswerModelList) {
        this.showanswerModelList = showanswerModelList;
    }

    @NonNull
    @Override
    public showanswerAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.translateitem,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull showanswerAdapter.viewHolder holder, int position) {
        String question=showanswerModelList.get(position).getQuestion();
        String audio=showanswerModelList.get(position).getAudio();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.itemView.getContext(), playerActivity.class);
                intent.putExtra("Audio",audio);
                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.setData(question);

    }

    @Override
    public int getItemCount() {
        return showanswerModelList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private ImageView Question;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            Question=itemView.findViewById(R.id.translator);
        }
        public void setData(String q)
        {
            Glide.with(itemView.getContext()).load(q).into(Question);


        }
    }
}
