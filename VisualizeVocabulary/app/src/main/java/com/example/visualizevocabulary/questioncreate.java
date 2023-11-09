package com.example.visualizevocabulary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.visualizevocabulary.Adapter.userQuestionAdapter;
import com.example.visualizevocabulary.Model.userQuestionModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class questioncreate extends AppCompatActivity {


     String documentID;
     public    List<userQuestionModel>userQuestionModelList=new ArrayList<>();


    Button submitbtn;
    private CountDownTimer countDownTimer;
    TextView stopwatch;
     int finalLimit;
     String limit;
     private RecyclerView recyclerView;
     FirebaseFirestore firebaseFirestore;
     FirebaseUser currentUser;
     FirebaseAuth firebaseAuth;
     int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questioncreate);



        recyclerView=findViewById(R.id.systemcreatquestionrecyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        currentUser=firebaseAuth.getCurrentUser();
        stopwatch=findViewById(R.id.stopwatch);
        submitbtn=findViewById(R.id.submit);





        Intent intent=getIntent();
        if(intent!=null)
        {
            documentID=intent.getStringExtra("L");


        }








        firebaseFirestore.collection("database")
                .document(documentID)
                .collection("quiz")
                .limit(200)  // Limit to the total number of documents in the collection
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> allDocuments = task.getResult().getDocuments(); // Corrected line
                            int totalDocuments = allDocuments.size();
                            List<Integer> selectedIndices = getRandomIndices(totalDocuments, 5);

                            List<DocumentSnapshot> selectedDocuments = new ArrayList<>();
                            for (int index : selectedIndices) {
                                selectedDocuments.add(allDocuments.get(index));
                            }

                            userQuestionModelList.clear();
                            for (DocumentSnapshot documentSnapshot : selectedDocuments) { // Changed to DocumentSnapshot
                                String Question = documentSnapshot.getString("question");
                                String option1 = documentSnapshot.getString("option1");
                                String option2 = documentSnapshot.getString("option2");
                                String option3 = documentSnapshot.getString("option3");
                                String option4 = documentSnapshot.getString("option4");
                                String answer = documentSnapshot.getString("answer");

                                userQuestionModel questionModel = new userQuestionModel(Question, option1, option2, option3, option4, answer);
                                userQuestionModelList.add(questionModel);
                            }

                            Collections.shuffle(userQuestionModelList);
                            userQuestionAdapter questionAdapter = new userQuestionAdapter(userQuestionModelList);
                            recyclerView.setAdapter(questionAdapter);
                            number = questionAdapter.getItemCount();
                            questionAdapter.notifyDataSetChanged();
                        }
                    }
                });





        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(questioncreate.this, showresultActivity.class);
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
    private List<Integer> getRandomIndices(int total, int count) {
        List<Integer> selectedIndices = new ArrayList<>();
        List<Integer> allIndices = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            allIndices.add(i);
        }

        Collections.shuffle(allIndices);

        for (int i = 0; i < count && i < total; i++) {
            selectedIndices.add(allIndices.get(i));
        }

        return selectedIndices;
    }





    }
