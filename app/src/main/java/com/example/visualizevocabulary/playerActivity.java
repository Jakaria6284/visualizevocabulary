package com.example.visualizevocabulary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;



public class playerActivity extends AppCompatActivity {

    private String audioUrl;
    private TextView text;

    private SeekBar seekBar;

    private ImageView playButton;
    private ImageView pauseButton;

    private boolean isPlaying = false;
    private MediaPlayer mediaPlayer;
    private static final int NOTIFICATION_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        audioUrl = getIntent().getStringExtra("Audio");



        seekBar=findViewById(R.id.seekber);
        playButton = findViewById(R.id.play);
        pauseButton = findViewById(R.id.pause);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Audio playback completed, update UI
                isPlaying = false;
                playButton.setVisibility(View.VISIBLE);
                pauseButton.setVisibility(View.GONE);
                seekBar.setProgress(0);
                seekBar.setVisibility(View.GONE);
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseAudio();

            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio();
            }
        });



        prepareMediaPlayer();

        // Start playing the audio as soon as the activity starts
        playAudio();





    }

    private void prepareMediaPlayer() {
        try {
            mediaPlayer.setDataSource(audioUrl);
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void playAudio() {
        mediaPlayer.start();
        isPlaying = true;
        playButton.setVisibility(View.VISIBLE);
        pauseButton.setVisibility(View.GONE);
        seekBar.setVisibility(View.VISIBLE);


        seekBar.setMax(mediaPlayer.getDuration());
        updateSeekBar();
    }

    private void stopAudio() {
        if (isPlaying) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            isPlaying = false;
            playButton.setVisibility(View.VISIBLE);
            pauseButton.setVisibility(View.GONE);
            seekBar.setProgress(0);
            seekBar.setVisibility(View.GONE);


        }








    }

    private void pauseAudio() {
        mediaPlayer.pause();
        isPlaying = false;
        playButton.setVisibility(View.GONE);
        pauseButton.setVisibility(View.VISIBLE);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();


    }

    private void updateSeekBar() {
        try {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                Runnable updateSeekBarRunnable = new Runnable() {
                    @Override
                    public void run() {
                        updateSeekBar();
                    }
                };
                seekBar.postDelayed(updateSeekBarRunnable, 1000); // Update every second
            }
        } catch (IllegalStateException e) {
            // Handle the exception, log it, or take appropriate action
            e.printStackTrace();
        }
    }






}