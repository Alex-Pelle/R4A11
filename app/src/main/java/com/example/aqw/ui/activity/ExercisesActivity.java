package com.example.aqw.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aqw.R;
import com.example.aqw.ui.adapter.ExerciseSelectionSeanceAdapter;
import com.example.aqw.ui.adapter.PlanningButtonAdapter;

import java.util.ArrayList;

public class ExercisesActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> list;
    PlanningButtonAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercices);
        listView = findViewById(R.id.list);

        list = new ArrayList<>();

        listView.setChoiceMode(listView.CHOICE_MODE_SINGLE);

       // adapter = new ExerciseSelectionSeanceAdapter(this,R.layout.jour_item,list);
        listView.setAdapter(adapter);


    }
}
