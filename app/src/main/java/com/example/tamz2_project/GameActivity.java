package com.example.tamz2_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GameActivity extends AppCompatActivity {
    SpaceRunnerView spaceRunnerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.space_runner_view);

        this.spaceRunnerView = new SpaceRunnerView(this);
        setContentView(this.spaceRunnerView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            this.spaceRunnerView.pause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.spaceRunnerView.resume();
    }
}