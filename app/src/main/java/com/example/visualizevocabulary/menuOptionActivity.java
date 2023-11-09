package com.example.visualizevocabulary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menuOptionActivity extends AppCompatActivity {
    private Button worngbtn,translatebtn,leaderboardbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_option);
        worngbtn=findViewById(R.id.lottieAnimationView3);
        translatebtn=findViewById(R.id.button3);
        leaderboardbtn=findViewById(R.id.button2);

        worngbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String w="r";
                Intent intent=new Intent(menuOptionActivity.this,allinONeActivity.class);
                intent.putExtra("W",w);
                startActivity(intent);
            }
        });
        leaderboardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String w="l";
                Intent intent=new Intent(menuOptionActivity.this,allinONeActivity.class);
                intent.putExtra("W",w);
                startActivity(intent);
            }
        });
        translatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String w="t";
                Intent intent=new Intent(menuOptionActivity.this,allinONeActivity.class);
                intent.putExtra("W",w);
                startActivity(intent);
            }
        });



    }
}