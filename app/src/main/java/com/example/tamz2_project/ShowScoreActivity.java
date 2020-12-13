package com.example.tamz2_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShowScoreActivity extends AppCompatActivity {

    private TextView scoreText;
    private EditText scoreName;
    private int score;
    Intent intent;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);


        intent = getIntent();
        score = intent.getIntExtra("score", 0);

        this.scoreName = (EditText)findViewById(R.id.scoreName);
        this.scoreText = (TextView)findViewById(R.id.scoreText);
        scoreText.setText(""+score);

        this.sharedPref = getSharedPreferences("SpaceRunner", Context.MODE_PRIVATE);
         this.editor = sharedPref.edit();
    }

    public void onPlayButtonClick(View view) {
        Intent i = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(i);
    }

    public void onBackButtonClick(View view) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    public void saveScoreButtonClick(View view){
        String text = scoreName.getText().toString();
        Score newScore = new Score(text, this.score);

        Gson gson = new Gson();
        String scoresString = sharedPref.getString("scores", "[]");

        Type listType = new TypeToken<List<Score>>(){}.getType();
        ArrayList<Score> scoreArrayList = gson.fromJson(scoresString,listType);

        Boolean pushed = false;
        Score changingScore = newScore;

        for(int i = 0; i < scoreArrayList.toArray().length - 1; i++){
            if(pushed){
                scoreArrayList.set(i, changingScore);
            } else if(scoreArrayList.get(i).score >= newScore.score && scoreArrayList.get(i+1).score < newScore.score){
                changingScore = scoreArrayList.get(i+1);
                scoreArrayList.set(i, newScore);
                pushed = true;
            }
        }
        scoreArrayList.add(changingScore);

        String stringArray = gson.toJson(scoreArrayList);

        editor.putString("scores",stringArray);
        editor.apply();
        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
    }
}