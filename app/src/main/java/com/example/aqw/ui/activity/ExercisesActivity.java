package com.example.aqw.ui.activity;

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aqw.R;
import com.example.aqw.api.ApiManager;
import com.example.aqw.ui.adapter.ExerciseSelectionSeanceAdapter;
import com.example.aqw.ui.adapter.PlanningButtonAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExercisesActivity extends AppCompatActivity {

    ListView listView;
    List<String> list;
    PlanningButtonAdapter adapter;
    ApiManager apiManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        apiManager = new ApiManager();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercices);
        listView = findViewById(R.id.list);

        list = apiManager.nomsExercices();

        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

       // adapter = new ExerciseSelectionSeanceAdapter(this,R.layout.jour_item,list);
        listView.setAdapter(adapter);


    }
}
