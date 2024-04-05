package com.example.aqw.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.aqw.R;
import com.example.aqw.modele.Exercice;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class ExerciseParameter  extends GymTrackerActivity {

    Button enregistrer;
    Button annuler;
    EditText repetitions;
    EditText series;
    TextInputEditText notes;
    String nomExercice;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercice_parametres);

        enregistrer = findViewById(R.id.buttonEnregistrer);
        annuler = findViewById(R.id.buttonAnnuler);
        repetitions = findViewById(R.id.repetitions);
        series = findViewById(R.id.series);
        notes = findViewById(R.id.notesSupplementaires);


        int position = getIntent().getIntExtra("Position",-1);
        if (position!=-1) {
            Exercice exerciceEdit = (Exercice) getIntent().getSerializableExtra("Exercice");
            nomExercice = exerciceEdit.getNom();

            repetitions.setText(""+exerciceEdit.getNbRepetitions());
            series.setText(""+exerciceEdit.getNbSeries());
            notes.setText(exerciceEdit.getNotes());
        } else {
            nomExercice = getIntent().getStringExtra("Nom");
        }

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        enregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repetitions.getText().length()>0 && series.getText().length()>0) {

                    Exercice retour = new Exercice(nomExercice,Integer.parseInt(String.valueOf(series.getText())),Integer.parseInt(String.valueOf(repetitions.getText())),notes.getText().toString());
                    Intent intentRetour = new Intent();
                    intentRetour.putExtra("Position",position);
                    intentRetour.putExtra("exo",retour);
                    setResult(RESULT_OK,intentRetour);

                    finish();
                } else {
                    if (repetitions.getText().length()<=0) {
                        Toast.makeText(ExerciseParameter.this,"Le nombre de répétition est vide", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ExerciseParameter.this,"Le nombre de séries est vide", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
    }
}
