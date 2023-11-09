package com.example.visualizevocabulary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

public class quizeActivity extends AppCompatActivity {

    private Button Level1,Level2,Level3;
    private EditText qInstruction;
    String limitdocument;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quize);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomnavigationviewww);
        bottomNavigationView.setSelectedItemId(R.id.quizeee);
        Level1=findViewById(R.id.level1);
        Level2=findViewById(R.id.level2);
        Level3=findViewById(R.id.level3);
        firebaseFirestore=FirebaseFirestore.getInstance();
        qInstruction=findViewById(R.id.qInstruction);





       Level1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String l1="level1";
               limitdocument=qInstruction.getText().toString();

               Intent intent=new Intent(quizeActivity.this,questioncreate.class);
               intent.putExtra("L",l1);
              // intent.putExtra("LIMIT",limitdocument);
               startActivity(intent);
           }
       });

        Level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limitdocument=qInstruction.getText().toString();
                Intent intent=new Intent(quizeActivity.this,questioncreate.class);
                String l1="level2";
              //  intent.putExtra("LIMIT",limitdocument);
                intent.putExtra("L",l1);
                startActivity(intent);
            }
        });

        Level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String l1="level3";
                limitdocument=qInstruction.getText().toString();

                Intent intent=new Intent(quizeActivity.this,questioncreate.class);
                intent.putExtra("L",l1);
               // intent.putExtra("LIMIT",limitdocument);
                startActivity(intent);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.homeee) {
                startActivity(new Intent(quizeActivity.this,HomeActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.readee) {
                startActivity(new Intent(quizeActivity.this,readActivity.class));
                finish();

                return true;

            } else if (itemId == R.id.quizeee) {
                return true;
            } else if (itemId == R.id.trunamentee) {
                startActivity(new Intent(quizeActivity.this,examActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.profileee) {
                startActivity(new Intent(quizeActivity.this,profileActivity.class));
                finish();
                return true;
            }

            return false;
        });


    }
}