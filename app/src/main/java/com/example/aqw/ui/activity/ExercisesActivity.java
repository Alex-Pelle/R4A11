package com.example.aqw.ui.activity;

import static com.example.aqw.ui.activity.SeanceCreationActivity.REQUEST_EXERCISE_CODE;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aqw.R;
import com.example.aqw.api.ApiManager;
import com.example.aqw.modele.Exercice;
import com.example.aqw.ui.adapter.ExerciseSelectionSeanceAdapter;
import com.example.aqw.ui.adapter.PlanningButtonAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExercisesActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> exercices;
    ApiManager apiManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        apiManager = new ApiManager();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercices);
        listView = findViewById(R.id.list);

        exercices = (ArrayList<String>) apiManager.nomsExercices();

        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

       if(getIntent().getBooleanExtra("adapter",true)) {
           listView.setAdapter(new ExerciseSelectionSeanceAdapter( this,R.layout.exercise_item,exercices));
           listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                   Intent intent = new Intent(ExercisesActivity.this,ExerciseParameter.class);
                   intent.putExtra("Nom",exercices.get(i));
                   startActivityForResult(intent,REQUEST_EXERCISE_CODE);
               }
           });
       }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EXERCISE_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Exercice exercice = (Exercice) extras.get("exo");
            Intent intentRetour = new Intent();
            intentRetour.putExtra("exo",exercice);
            setResult(RESULT_OK,intentRetour);
            finish();
        }
    }
}
