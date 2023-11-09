package com.example.visualizevocabulary.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.visualizevocabulary.Model.newModel;
import com.example.visualizevocabulary.R;
import com.example.visualizevocabulary.userExamActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.EventListener;
import java.util.List;

public class newAdapter extends RecyclerView.Adapter<newAdapter.viewHolder> {
    List<newModel> newModelList;
    FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();

    public newAdapter(List<newModel> newModelList) {
        this.newModelList = newModelList;
    }

    @NonNull
    @Override
    public newAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.newfeed,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull newAdapter.viewHolder holder, int position) {
     String userName=newModelList.get(position).getName();

     holder.setData(userName);


    }

    @Override
    public int getItemCount() {
        return newModelList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

     ImageView i;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
           i=itemView.findViewById(R.id.news);
        }

        public void setData(String n)
        {


            Glide.with(itemView.getContext()).load(n).into(i);




        }

    }
}
