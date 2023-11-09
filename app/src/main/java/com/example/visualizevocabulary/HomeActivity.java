package com.example.visualizevocabulary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.visualizevocabulary.Adapter.jobAdapter;
import com.example.visualizevocabulary.Adapter.newAdapter;
import com.example.visualizevocabulary.Model.jobModel;
import com.example.visualizevocabulary.Model.newModel;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView,newsfeedrecyclerview;
    FloatingActionButton postbtn;
    ImageView menu,logo;
    ShimmerFrameLayout shimmerFrameLayout;

   FirebaseFirestore firestore;
   FirebaseAuth firebaseAuth;
   FirebaseUser firebaseUser;
   ConstraintLayout constraintLayout;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        newsfeedrecyclerview=findViewById(R.id.newfeedrecyclerview);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomnavigationviewww);
        bottomNavigationView.setSelectedItemId(R.id.homeee);
        postbtn=findViewById(R.id.post);
        menu=findViewById(R.id.menuoption);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        shimmerFrameLayout=findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmer();
        logo=findViewById(R.id.gifImageView2);



        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.homeee) {

                return true;
            } else if (itemId == R.id.readee) {
                startActivity(new Intent(HomeActivity.this,readActivity.class));
                finish();
                return true;

            } else if (itemId == R.id.quizeee) {
                startActivity(new Intent(HomeActivity.this,quizeActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.trunamentee) {
                startActivity(new Intent(HomeActivity.this,examActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.profileee) {
                startActivity(new Intent(HomeActivity.this,profileActivity.class));
                finish();
                return true;
            }

            return false;
        });


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, menuOptionActivity.class));
            }
        });
        firestore=FirebaseFirestore.getInstance();



       recyclerView=findViewById(R.id.jobrecycler);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
       recyclerView.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManager1=new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        newsfeedrecyclerview.setLayoutManager(layoutManager1);

        firestore.collection("USER").document(firebaseUser.getUid())
                        .get()
                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if(task.isSuccessful())
                                        {
                                            shimmerFrameLayout.stopShimmer();
                                            logo.setVisibility(View.VISIBLE);
                                            menu.setVisibility(View.VISIBLE);
                                            bottomNavigationView.setVisibility(View.VISIBLE);
                                            postbtn.setVisibility(View.VISIBLE);
                                            shimmerFrameLayout.setVisibility(View.GONE);

                                            DocumentSnapshot documentSnapshot=task.getResult();
                                            if(documentSnapshot.exists())
                                            {
                                                 name=documentSnapshot.getString("User");
                                            }
                                        }
                                    }
                                });
        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, postAcitivity.class);
                intent.putExtra("Name",name);
                startActivity(intent);


            }
        });

        firestore.collection("news")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<newModel> newModelList = new ArrayList<>();
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                String uName = documentSnapshot.getString("Image");

                                newModel model = new newModel(uName);
                                newModelList.add(model);
                            }
                            newAdapter adapter = new newAdapter(newModelList);
                            newsfeedrecyclerview.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });




        firestore.collection("JOBPOST")
               .get()
               .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
                     if(task.isSuccessful())
                     {
                         List<jobModel>jobModelList=new ArrayList<>();

                         for(QueryDocumentSnapshot Snapshot: task.getResult())
                         {
                             String image=Snapshot.getString("Image");
                             jobModel model=new jobModel(image);
                             jobModelList.add(model);
                         }
                         jobAdapter adapter=new jobAdapter(jobModelList);
                         recyclerView.setAdapter(adapter);
                         adapter.notifyDataSetChanged();

                     }
                   }
               });






    }
}