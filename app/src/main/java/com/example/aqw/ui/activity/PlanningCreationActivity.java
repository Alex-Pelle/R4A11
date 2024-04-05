package com.example.aqw.ui.activity;

import static com.example.aqw.ui.activity.SeanceCreationActivity.REQUEST_EXERCISE_CODE;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aqw.R;
import com.example.aqw.database.DatabaseManager;
import com.example.aqw.modele.Exercice;
import com.example.aqw.modele.Planning;
import com.example.aqw.modele.Seance;
import com.example.aqw.ui.adapter.PlanningButtonAdapter;
import com.example.aqw.ui.adapter.SeanceCreationAdapter;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class PlanningCreationActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Seance> seances;
    PlanningButtonAdapter adapter;
    Planning planning;
    Planning oldPlanning;

    public static int CODE_REQUEST_SEANCE = 54321;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_planning);
        listView = findViewById(R.id.list);
        int position = getIntent().getIntExtra("position",-1);
        seances = new ArrayList<>();
        EditText nomPlanning = findViewById(R.id.nomDuPlanning);
        if(position==-1) {
            planning = new Planning("Sans Titre ");
            seances = new ArrayList<>();

            for(Planning.Jour jour : Planning.Jour.values()) {
                planning.setSeance(jour,new Seance(jour.name()));
                seances.add(planning.getSeance(jour));
            }
        } else {
            planning= (Planning) getIntent().getSerializableExtra("plan");
            oldPlanning = planning.clone();
            for(Planning.Jour jour : Planning.Jour.values()) {
                seances.add(planning.getSeance(jour));
            }
            nomPlanning.setText(planning.getNom());
        }

        listView.setChoiceMode(listView.CHOICE_MODE_SINGLE);

        Button enregistrer = findViewById(R.id.buttonEnregistrer);
        Button annuler = findViewById(R.id.buttonAnnuler);


        enregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nomPlanning.getText().length()<=0) {
                    Toast.makeText(PlanningCreationActivity.this,"Le planning doit avoir un nom", Toast.LENGTH_SHORT).show();
                } else {
                    planning.setNom(String.valueOf(nomPlanning.getText()));
                    DatabaseManager manager = new DatabaseManager(PlanningCreationActivity.this);
                    int i = 0;
                    for (Planning.Jour jour : Planning.Jour.values()) {
                        planning.setSeance(jour, seances.get(i));
                        i++;
                    }
                    if(planning.isPlanningSeancesEmpty()){
                        Toast.makeText(PlanningCreationActivity.this,"Le planning doit comporter au moins une séance", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    try {
                        manager.open();
                    } catch (SQLDataException e) {
                        throw new RuntimeException(e);
                    }
                    if(position==-1) {
                        manager.insertPlanning(planning);
                    } else {
                        Log.v("oldplanning edit ----",oldPlanning.toString());
                        Log.v("planning edit ----",planning.toString());
                        manager.updatePlanning(oldPlanning,planning);
                    }
                    manager.close();
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        adapter = new PlanningButtonAdapter(this,seances);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_REQUEST_SEANCE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();

            Log.v("seance OG", seances.get((Integer) extras.get("position")).toString());

            Integer position = (Integer) extras.get("position");
            Seance seanceEdit = (Seance) extras.get("seance");
            seances.set(position, seanceEdit);
            Log.v("seance OG", seances.toString());
            adapter.notifyDataSetChanged();
            Log.v("plan",planning.toString());
        }
    }

}
