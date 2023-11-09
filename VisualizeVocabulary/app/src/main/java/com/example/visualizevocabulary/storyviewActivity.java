package com.example.visualizevocabulary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.Collections;



public class storyviewActivity extends AppCompatActivity {

    String urll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storyview);
        urll = getIntent().getStringExtra("url");


        PhotoView photoView = findViewById(R.id.storyview);
        Glide.with(this)
                .load(urll)
                .into(photoView);


    }
}

