package com.example.visualizevocabulary.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
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
     String userMotive=newModelList.get(position).getBio();
     String uId=newModelList.get(position).getuID();
     holder.setData(userName,userMotive,uId);


    }

    @Override
    public int getItemCount() {
        return newModelList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView BIO,NAME,lovecount;
        private Button giveexambtn;
        LottieAnimationView lottieAnimationView;
        private boolean love=false;
       // boolean isAnimationPlaying = false;
       int animationCount = 0;
        boolean isAnimationPlaying = false;
        int counter=0;
        long countt;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            BIO=itemView.findViewById(R.id.status);
            NAME=itemView.findViewById(R.id.username);
            lottieAnimationView=itemView.findViewById(R.id.love);
            lovecount=itemView.findViewById(R.id.lovecount);
            giveexambtn=itemView.findViewById(R.id.getexam);
        }

        public void setData(String n,String b,String uidd)
        {

            NAME.setText(n);



            lottieAnimationView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isAnimationPlaying) {
                        // Start animation
                        lottieAnimationView.playAnimation();
                        isAnimationPlaying = true;
                        animationCount++;

                        // Update animation count value in Firebase
                        FirebaseFirestore.getInstance()
                                .collection("USER")
                                .document(uidd) // Replace with your document ID
                                .update("animationCount", FieldValue.increment(1))
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            // Animation count value updated in Firebase
                                        }
                                    }
                                });
                    } else {
                        // Stop animation
                        lottieAnimationView.cancelAnimation();
                        //animationCount=animationCount-1;


                        // Update animation count value in Firebase
                        FirebaseFirestore.getInstance()
                                .collection("USER")
                                .document(uidd) // Replace with your document ID
                                .update("animationCount", FieldValue.increment(-1))
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            // Animation count value updated in Firebase
                                        }
                                    }
                                });
                    }





                }
            });
            FirebaseFirestore.getInstance()
                    .collection("USER")
                    .document(uidd)
                    .addSnapshotListener(new com.google.firebase.firestore.EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            if (e != null) {
                                Log.e("Firestore", "Error fetching document: " + e);
                                return;
                            }

                            if (documentSnapshot != null && documentSnapshot.exists()) {
                                Long countt = documentSnapshot.getLong("animationCount");
                                Log.d("Firestore", "animationCount retrieved: " + countt);
                                lovecount.setText(String.valueOf(countt));
                            }
                        }
                    });



            BIO.setText(b);

            giveexambtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(itemView.getContext(), userExamActivity.class);
                    intent.putExtra("UID",uidd);
                    itemView.getContext().startActivity(intent);

                }
            });

        }

    }
}
