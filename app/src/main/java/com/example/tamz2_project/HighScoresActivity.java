package com.example.tamz2_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HighScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        Gson gson = new Gson();

        SharedPreferences sharedPref =  getSharedPreferences("SpaceRunner", Context.MODE_PRIVATE);
        String scoresString = sharedPref.getString("scores", "[]");

        Type listType = new TypeToken<List<Score>>(){}.getType();
        ArrayList<Score> scoreArrayList = gson.fromJson(scoresString,listType);

        ArrayList list = new ArrayList<>();

        ListView scoreList = (ListView)findViewById(R.id.scoreList);
        ArrayAdapter adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        scoreList.setAdapter(adapter);
        for (Score score : scoreArrayList) {
            list.add((scoreArrayList.indexOf(score)+1) + " - " + score.score + " - " + score.name);
        }
    }
}