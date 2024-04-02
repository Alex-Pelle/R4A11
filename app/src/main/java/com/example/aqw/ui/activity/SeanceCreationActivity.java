package com.example.aqw.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aqw.R;
import com.example.aqw.modele.Exercice;
import com.example.aqw.modele.Seance;
import com.example.aqw.ui.adapter.ExerciseSelectionSeanceAdapter;
import com.example.aqw.ui.adapter.PlanningButtonAdapter;

import java.util.ArrayList;

public class SeanceCreationActivity extends AppCompatActivity {

    static final int REQUEST_EXERCISE_CODE = 1234;

    ListView listView;
    ArrayList<Exercice> exercices;
    PlanningButtonAdapter adapter;
    Seance seance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_seance);
        Intent intent = getIntent();
        if (intent != null) {
            seance = (Seance) intent.getSerializableExtra("Seance");
        }

        listView = findViewById(R.id.list);

        TextView nomPage = findViewById(R.id.textPageName);
        Button button = findViewById(R.id.buttonCreation);
        nomPage.setText("Création de séance");
        button.setText("Ajouter un exercice");

        exercices = (ArrayList<Exercice>) seance.getExercices();
        listView.setChoiceMode(listView.CHOICE_MODE_SINGLE);

        //adapter = new PlanningButtonAdapter(this,R.layout.jour_item,list);
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeanceCreationActivity.this, ExercisesActivity.class);
                intent.putExtra("adapter",true);
                startActivityForResult(intent,REQUEST_EXERCISE_CODE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EXERCISE_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Exercice exercice = (Exercice) extras.get("exo");
            seance.addExercice(exercice);
        }
    }


}
