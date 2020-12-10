package com.example.tamz2_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton = (Button)findViewById(R.id.play_button);
    }

    public void onButtonClickCancel(View view) {
        Intent i = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(i);
    }
}