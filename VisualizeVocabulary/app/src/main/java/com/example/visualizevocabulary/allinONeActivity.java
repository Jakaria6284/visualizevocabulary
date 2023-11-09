package com.example.visualizevocabulary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.visualizevocabulary.Adapter.leaderboardAdapter;
import com.example.visualizevocabulary.Adapter.wrongAdapter;
import com.example.visualizevocabulary.Model.leaderboardModel;
import com.example.visualizevocabulary.Model.wrongModel;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class allinONeActivity extends AppCompatActivity {

    private RecyclerView wrongrecyclerview,leaderboardreyclerview;
    List<wrongModel> wrongModelList=new ArrayList<>();
    public FirebaseFirestore firebaseFirestore;
    public FirebaseUser firebaseUser;
    public FirebaseAuth firebaseAuth;
    private Button previousquestionbtn;
    public String wrongcmp="r";
    private TextView leaderboarddtxt;

    String wrongg;
    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allin_one);
        wrongrecyclerview=findViewById(R.id.wrongrecyclerview);
        leaderboardreyclerview=findViewById(R.id.learboardrecyclerview);
        leaderboarddtxt=findViewById(R.id.textView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        wrongrecyclerview.setLayoutManager(layoutManager);
        shimmerFrameLayout=findViewById(R.id.shimmer);

      shimmerFrameLayout.startShimmer();
        LinearLayoutManager layoutManager1=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
       leaderboardreyclerview.setLayoutManager(layoutManager1);
        firebaseFirestore=FirebaseFirestore.getInstance();

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        previousquestionbtn=findViewById(R.id.previousquestionbtn);
        wrongg=getIntent().getStringExtra("W");

        if(wrongg.equals("r"))
        {
            wrongrecyclerview.setVisibility(View.VISIBLE);
            previousquestionbtn.setVisibility(View.VISIBLE);


        }

        if(wrongg.equals("l"))
        {
           leaderboardreyclerview.setVisibility(View.VISIBLE);
           leaderboarddtxt.setVisibility(View.VISIBLE);


        }

        firebaseFirestore.collection("USER").orderBy("coin", Query.Direction.DESCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            shimmerFrameLayout.stopShimmer();
                            shimmerFrameLayout.setVisibility(View.GONE);
                            List<leaderboardModel>showanswerModelList=new ArrayList<>();

                            for(QueryDocumentSnapshot queryDocumentSnapshot:task.getResult())
                            {
                               String name=queryDocumentSnapshot.getString("User");
                                String m=queryDocumentSnapshot.getString("motive");


                                leaderboardModel model=new leaderboardModel(name);
                                showanswerModelList.add(model);
                            }
                            leaderboardAdapter adapter=new leaderboardAdapter(showanswerModelList);
                            leaderboardreyclerview.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });



        firebaseFirestore.collection("wrong")
                .document(firebaseUser.getUid()).collection("mcq")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            previousquestionbtn.setVisibility(View.VISIBLE);
                            shimmerFrameLayout.stopShimmer();
                            shimmerFrameLayout.setVisibility(View.GONE);
                            wrongModelList.clear();
                            for(QueryDocumentSnapshot documentSnapshot:task.getResult())
                            {
                                String Question= documentSnapshot.getString("question");
                                String option1= documentSnapshot.getString("option1");
                                String option2= documentSnapshot.getString("option2");
                                String option3= documentSnapshot.getString("option3");
                                String option4= documentSnapshot.getString("option4");
                                String answer= documentSnapshot.getString("answer");

                                wrongModel questionModel=new wrongModel(Question,option1,option2,option3,option4,answer);
                                wrongModelList.add(questionModel);

                            }
                            wrongAdapter questionAdapter=new wrongAdapter(wrongModelList);
                            wrongrecyclerview.setAdapter(questionAdapter);

                            questionAdapter.notifyDataSetChanged();
                        }
                    }
                });


    }
}