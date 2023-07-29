package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static int SPLASH_TIMER = 19000;
    private ProgressBar progressBar;
    private TextView loadingNumberTextView, loadingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        loadingNumberTextView = findViewById(R.id.loadingNumberTextView);
        loadingTextView = findViewById(R.id.loadingView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMER);
        updateProgressBar();
    }

    private void updateProgressBar() {
        final int totalProgressTime = 100;
        final int updateInterval = SPLASH_TIMER / totalProgressTime;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            int progress = 0;
            @Override
            public void run() {
                if (progress <= totalProgressTime) {
                    progressBar.setProgress(progress);
                    loadingNumberTextView.setText(progress + "%");
                    progress++;
                    handler.postDelayed(this, updateInterval);
                }
            }
        }, updateInterval);
    }
}