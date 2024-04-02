package com.example.aqw.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aqw.R;
import com.example.aqw.modele.Planning;
import com.example.aqw.modele.Seance;
import com.example.aqw.ui.adapter.PlanningButtonAdapter;
import com.example.aqw.ui.adapter.SeanceCreationAdapter;

import java.util.ArrayList;

public class PlanningCreationActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Seance> seances;
    PlanningButtonAdapter adapter;
    Planning planning;

    static int CODE_REQUEST_SEANCE = 54321;

    public static int getCodeRequestSeance() {return CODE_REQUEST_SEANCE;}



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_planning);
        listView = findViewById(R.id.list);
        planning = new Planning("Sans Titre ");
        seances = new ArrayList<>();

        for(Planning.Jour jour : Planning.Jour.values()) {
            planning.setSeance(jour,new Seance(jour.name()));
            seances.add(planning.getSeance(jour));
        }

        listView.setChoiceMode(listView.CHOICE_MODE_SINGLE);

        Button enregistrer = findViewById(R.id.buttonEnregistrer);
        Button annuler = findViewById(R.id.buttonAnnuler);



        adapter = new PlanningButtonAdapter(this,seances);
        listView.setAdapter(adapter);



    }
}
