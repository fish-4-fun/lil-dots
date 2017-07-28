package com.fish4fun.lildots;

import android.os.Bundle;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private DotsView dotsView;

    private ImageButton buttonPlay;

    private AnimatedVectorDrawableCompat animatedVectorPlayToPause;
    private AnimatedVectorDrawableCompat animatedVectorPauseToPlay;

    private boolean play = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dots view will save and restore it's running
        dotsView = findViewById(R.id.dotsView);

        animatedVectorPlayToPause = AnimatedVectorDrawableCompat.create(this, R.drawable.pause_to_play);
        animatedVectorPauseToPlay = AnimatedVectorDrawableCompat.create(this, R.drawable.play_to_pause);

        buttonPlay = findViewById(R.id.buttonPlay);

        if (savedInstanceState == null) {
            setupDots();
        }
    }

    private void setupDots() {

        play = dotsView.isRunning();
        buttonPlay.setImageDrawable(play ? animatedVectorPlayToPause : animatedVectorPauseToPlay);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (play) {
                    dotsView.stop();
                    buttonPlay.setImageDrawable(animatedVectorPlayToPause);
                    animatedVectorPlayToPause.start();
                } else {
                    dotsView.start();
                    buttonPlay.setImageDrawable(animatedVectorPauseToPlay);
                    animatedVectorPauseToPlay.start();
                }
                play = !play;
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setupDots();
    }
}
