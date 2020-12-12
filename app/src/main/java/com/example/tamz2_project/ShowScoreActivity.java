package com.example.tamz2_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowScoreActivity extends AppCompatActivity {

    private TextView scoreText;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);


        intent = getIntent();
        int score = intent.getIntExtra("score", 0);

        this.scoreText = (TextView)findViewById(R.id.scoreText);
        scoreText.setText(""+score);
    }

    public void onPlayButtonClick(View view) {
        Intent i = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(i);
    }

    public void onBackButtonClick(View view) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}