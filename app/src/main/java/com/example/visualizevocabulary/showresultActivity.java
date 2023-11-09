package com.example.visualizevocabulary;

import static com.example.visualizevocabulary.Adapter.userQuestionAdapter.count;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.visualizevocabulary.Model.userQuestionModel;

import java.util.ArrayList;

public class showresultActivity extends AppCompatActivity {
    private TextView correctans,totalnumberr;
    int answer,numberofquestion;
    Button showanswer;
    private ArrayList<userQuestionModel> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showresult);

       totalnumberr=findViewById(R.id.totalnumber);
       correctans=findViewById(R.id.earnednumber);
       answer=count;
       showanswer=findViewById(R.id.showanswer);


       showanswer.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

           }
       });

       // answer=getIntent().getIntExtra("Count",0);
        numberofquestion=getIntent().getIntExtra("Number",0);

        totalnumberr.setText(String.valueOf("Total Question: "+numberofquestion));
        correctans.setText(String.valueOf("Earned number: "+answer));


    }

    @Override
    protected void onStop() {
        super.onStop();
        count = 0;
        numberofquestion = 0;
        answer = 0;
        totalnumberr.setText("");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(showresultActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();

    }
}