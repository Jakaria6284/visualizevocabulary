package com.example.visualizevocabulary;

import static com.example.visualizevocabulary.Adapter.userQuestionAdapter.count;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.visualizevocabulary.Adapter.userQuestionAdapter;
import com.example.visualizevocabulary.Model.userQuestionModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class userExamActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    int number;
    private TextView correctans,totalnumberofquestion,stopwatch;
    Button submitbtn;
    long Coin;
    FirebaseFirestore firebaseFirestore;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    String questionid;
    private CountDownTimer countDownTimer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_exam);
        recyclerView=findViewById(R.id.userexamrecyclerviewe);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        questionid=getIntent().getStringExtra("UID");
        stopwatch=findViewById(R.id.stopwatch);
        submitbtn=findViewById(R.id.submit);



        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();



        firebaseFirestore.collection("USER").document(questionid)
                .collection("userpost")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if(task.isSuccessful())
                        {
                            List<userQuestionModel>userQuestionModelList=new ArrayList<>();
                            for(QueryDocumentSnapshot documentSnapshot:task.getResult())
                            {
                                String Question= documentSnapshot.getString("question");
                                String option1= documentSnapshot.getString("option1");
                                String option2= documentSnapshot.getString("option2");
                                String option3= documentSnapshot.getString("option3");
                                String option4= documentSnapshot.getString("option4");
                                String answer= documentSnapshot.getString("answer");

                                userQuestionModel questionModel=new userQuestionModel(Question,option1,option2,option3,option4,answer);
                                userQuestionModelList.add(questionModel);
                            }
                            userQuestionAdapter questionAdapter=new userQuestionAdapter(userQuestionModelList);
                            recyclerView.setAdapter(questionAdapter);
                             number=questionAdapter.getItemCount();
                            questionAdapter.notifyDataSetChanged();
                        }

                    }
                });
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore.collection("USER").document(firebaseUser.getUid())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if(task.isSuccessful())
                                {
                                    DocumentSnapshot documentSnapshot=task.getResult();
                                    if(documentSnapshot.exists())
                                    {
                                         Coin=documentSnapshot.getLong("coin");
                                         Coin=Coin+10;

                                    }

                                    firebaseFirestore.collection("USER")
                                            .document(firebaseUser.getUid())
                                            .update("coin",Coin)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful())
                                                    {
                                                        Toast.makeText(userExamActivity.this, "10 coin add successfully", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                }

                            }
                        });


                Intent intent =new Intent(userExamActivity.this, showresultActivity.class);

                intent.putExtra("Number",number);

                startActivity(intent);
            }
        });

        countDownTimer = new CountDownTimer(300000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the timer textview with the remaining time
                long seconds = millisUntilFinished / 1000;
                stopwatch.setText("Time: " + seconds + "s");
            }

            @Override
            public void onFinish() {


            }
        };

        // Start the countdown timer
        countDownTimer.start();
    }
    public void updateCount(int updatedCount) {
        // Handle the updated count here
        // You can use the updatedCount value in your activity
        count = updatedCount;
    }


}
