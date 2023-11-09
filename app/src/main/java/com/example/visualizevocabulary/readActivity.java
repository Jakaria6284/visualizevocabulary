package com.example.visualizevocabulary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class readActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomnavigationviewww);
        bottomNavigationView.setSelectedItemId(R.id.readee);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.homeee) {
                startActivity(new Intent(readActivity.this,HomeActivity.class));
                finish();

                return true;
            } else if (itemId == R.id.readee) {
                return true;


            } else if (itemId == R.id.quizeee) {
                startActivity(new Intent(readActivity.this,quizeActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.trunamentee) {
                startActivity(new Intent(readActivity.this,examActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.profileee) {
                startActivity(new Intent(readActivity.this,profileActivity.class));
                finish();
                return true;
            }

            return false;
        });

    }
}