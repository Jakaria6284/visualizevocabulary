package com.example.visualizevocabulary.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.visualizevocabulary.Model.jobModel;
import com.example.visualizevocabulary.R;
import com.example.visualizevocabulary.storyviewActivity;

import java.util.List;

public class jobAdapter extends RecyclerView.Adapter<jobAdapter.viewHolder> {
    List<jobModel>jobModelList;

    public jobAdapter(List<jobModel> jobModelList) {
        this.jobModelList = jobModelList;
    }

    @NonNull
    @Override
    public jobAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.jobitem,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull jobAdapter.viewHolder holder, int position) {
        String image=jobModelList.get(position).getJobImage();
        holder.setData(image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation fadeIn = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.fade_in);
                holder.itemView.startAnimation(fadeIn);

                Intent intent=new Intent(holder.itemView.getContext(), storyviewActivity.class);
                intent.putExtra("url",image);
                holder.itemView.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return jobModelList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.jobimage);
        }
        public void setData(String picture)
        {
            Glide.with(itemView.getContext()).load(picture).into(img);
        }
    }
}
