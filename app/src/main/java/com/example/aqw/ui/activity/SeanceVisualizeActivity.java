package com.example.aqw.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aqw.R;
import com.example.aqw.modele.Exercice;
import com.example.aqw.modele.Planning;
import com.example.aqw.modele.Seance;
import com.example.aqw.ui.adapter.PlanningVisualizeAdapter;
import com.example.aqw.ui.adapter.SeanceVisualizeAdapter;

import java.util.ArrayList;

public class SeanceVisualizeActivity extends AppCompatActivity {
    private Seance seance;
    private ArrayList<Exercice> exercices;
    private RecyclerView seancesVisualizer;
    private LinearLayoutManager llm;
    private TextView seanceNom;
    private SeanceVisualizeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planning_visualizer);
        seancesVisualizer = findViewById(R.id.planningVisualizer);
        seanceNom = findViewById(R.id.nomPlanningVisualizer);

        seance = (Seance) getIntent().getSerializableExtra("seance");
        if (seance!=null) {
            llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.HORIZONTAL);
            seanceNom.setText(seance.getNom());
            exercices = seance.getExercices();
            adapter = new SeanceVisualizeAdapter(this, exercices, new SeanceVisualizeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Exercice item) {
                    Intent intent = new Intent(SeanceVisualizeActivity.this,DetailsExerciceActivity.class);
                    Log.v("item",item.getNom());
                    intent.putExtra("Nom",item.getNom());
                    startActivity(intent);
                }
            });
                    seancesVisualizer.setLayoutManager(llm);
            seancesVisualizer.setAdapter(adapter);
        }

    }
}
