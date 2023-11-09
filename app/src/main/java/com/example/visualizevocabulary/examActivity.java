package com.example.visualizevocabulary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.visualizevocabulary.Adapter.showanswerAdapter;
import com.example.visualizevocabulary.Model.showanswerModel;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class examActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;
    ShimmerFrameLayout shimmerFrameLayout;
    public List<showanswerModel> showanswerModelList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomnavigationviewww);
        bottomNavigationView.setSelectedItemId(R.id.trunamentee);
        shimmerFrameLayout=findViewById(R.id.shimmer);

        recyclerView=findViewById(R.id.audiorecyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        shimmerFrameLayout.startShimmer();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.homeee) {
                startActivity(new Intent(examActivity.this,HomeActivity.class));
                finish();

                return true;
            } else if (itemId == R.id.readee) {
                startActivity(new Intent(examActivity.this,readActivity.class));
                finish();
                return true;

            } else if (itemId == R.id.quizeee) {
                startActivity(new Intent(examActivity.this,quizeActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.trunamentee) {
                return true;
            } else if (itemId == R.id.profileee) {
                startActivity(new Intent(examActivity.this,profileActivity.class));
                finish();
                return true;
            }

            return false;
        });

        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseFirestore.collection("audio")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            shimmerFrameLayout.stopShimmer();
                            shimmerFrameLayout.setVisibility(View.GONE);

                            showanswerModelList.clear();

                            for(QueryDocumentSnapshot queryDocumentSnapshot:task.getResult())
                            {
                                String question=queryDocumentSnapshot.getString("a");
                                String audio=queryDocumentSnapshot.getString("m");

                                showanswerModel model=new showanswerModel(question,audio);
                                showanswerModelList.add(model);
                            }
                            showanswerAdapter Adapter=new showanswerAdapter(showanswerModelList);
                            recyclerView.setAdapter(Adapter);
                            Adapter.notifyDataSetChanged();

                        }
                    }
                });






    }
}