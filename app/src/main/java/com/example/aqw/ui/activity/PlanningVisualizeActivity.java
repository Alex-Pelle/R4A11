package com.example.aqw.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aqw.R;
import com.example.aqw.modele.Planning;
import com.example.aqw.modele.Seance;
import com.example.aqw.ui.adapter.PlanningVisualizeAdapter;

import java.util.ArrayList;

public class PlanningVisualizeActivity extends AppCompatActivity {
    private Planning planning;
    private ArrayList<Seance> seances;
    private RecyclerView planningVisualizer;
    private TextView planningNom;
    private PlanningVisualizeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planning_visualizer);
        planningVisualizer = findViewById(R.id.planningVisualizer);
        planningNom = findViewById(R.id.nomPlanningVisualizer);

        planning = (Planning) getIntent().getSerializableExtra("plan");
        Log.v("aaaaaaaaaaaaaa", String.valueOf(planning!=null));
        if (planning!=null) {
            planningNom.setText(planning.getNom());
            seances = new ArrayList<>();
            seances.addAll(planning.getSeances());
            adapter = new PlanningVisualizeAdapter(this,planning,seances);
            planningVisualizer.setAdapter(adapter);
        }

    }
}
