package com.example.aqw.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aqw.R;
import com.example.aqw.modele.Exercice;
import com.example.aqw.modele.Seance;
import com.example.aqw.ui.adapter.SeanceVisualizeAdapter;

import java.util.ArrayList;

public class SeanceVisualizeActivity extends GymTrackerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planning_visualizer);
        RecyclerView seancesVisualizer = findViewById(R.id.planningVisualizer);
        TextView seanceNom = findViewById(R.id.nomPlanningVisualizer);

        Seance seance = (Seance) getIntent().getSerializableExtra("seance");
        if (seance !=null) {
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.HORIZONTAL);
            seanceNom.setText(seance.getNom());
            ArrayList<Exercice> exercices = seance.getExercices();
            SeanceVisualizeAdapter adapter = new SeanceVisualizeAdapter(this, exercices, new SeanceVisualizeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Exercice item) {
                    Intent intent = new Intent(SeanceVisualizeActivity.this, DetailsExerciceActivity.class);
                    Log.v("item", item.getNom());
                    intent.putExtra("Nom", item.getNom());
                    startActivity(intent);
                }
            });
                    seancesVisualizer.setLayoutManager(llm);
            seancesVisualizer.setAdapter(adapter);
        }

    }
}
