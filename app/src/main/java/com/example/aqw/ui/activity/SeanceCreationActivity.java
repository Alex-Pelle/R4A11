package com.example.aqw.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aqw.R;
import com.example.aqw.modele.Exercice;
import com.example.aqw.modele.Seance;
import com.example.aqw.ui.adapter.ExerciseSelectionSeanceAdapter;
import com.example.aqw.ui.adapter.PlanningButtonAdapter;
import com.example.aqw.ui.adapter.SeanceCreationAdapter;

import java.util.ArrayList;

public class SeanceCreationActivity extends AppCompatActivity {

    public static final int REQUEST_EXERCISE_CODE = 1234;

    ListView listView;
    SeanceCreationAdapter adapter;
    Seance seance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_seance);


        seance = (Seance) getIntent().getSerializableExtra("seance");

        Log.v("s", String.valueOf(seance));
        listView = findViewById(R.id.list);
        listView.setChoiceMode(listView.CHOICE_MODE_SINGLE);
        adapter = new SeanceCreationAdapter(SeanceCreationActivity.this, seance.getExercices());
        listView.setAdapter(adapter);


        TextView nomPage = findViewById(R.id.textPageName);
        Button button = findViewById(R.id.buttonCreation);
        nomPage.setText("Création de séance");
        button.setText("Ajouter un exercice");
        Button enregistrer = findViewById(R.id.buttonEnregistrer);
        Button annuler = findViewById(R.id.buttonAnnuler);
        EditText nomSeance = findViewById(R.id.nomSeance);

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        enregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (seance.getExercices().isEmpty()) {
                    Toast.makeText(SeanceCreationActivity.this,getString(R.string.erreur_exercice), Toast.LENGTH_SHORT).show();
                } else {
                    Log.v("SeanceCreation", seance.toString());
                    if(nomSeance.getText().toString().trim().isEmpty()) {
                        seance.setNom("Sans titre");
                    } else {
                        seance.setNom(nomSeance.getText().toString());
                    }
                    Intent intentRetour = new Intent();
                    intentRetour.putExtra("seance",seance);
                    intentRetour.putExtra("position",getIntent().getIntExtra("position",-1));
                    setResult(RESULT_OK,intentRetour);
                    finish();
                }
            }
        });





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeanceCreationActivity.this, ExercisesActivity.class);
                intent.putExtra("selection",true);
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
            int position = (Integer) extras.get("Position");
            Log.v("positionEdit", String.valueOf(position));
            Log.v("exerciceEdit",exercice.toString());
            if (position!=-1) {
                seance.getExercices().remove(position);
            }
            seance.addExercice(exercice);

            adapter.notifyDataSetChanged();
        }
    }


}