package com.example.aqw.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aqw.R;
import com.example.aqw.modele.Planning;
import com.example.aqw.modele.Seance;
import com.example.aqw.ui.adapter.PlanningVisualizeAdapter;
import com.example.aqw.ui.adapter.SeanceVisualizeAdapter;

import java.util.ArrayList;

public class PlanningVisualizeActivity extends AppCompatActivity {
    private Planning planning;
    private ArrayList<Seance> seances;
    private RecyclerView planningVisualizer;
    private LinearLayoutManager llm;
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
            llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.HORIZONTAL);
            planningNom.setText(planning.getNom());
            seances = new ArrayList<>();
            for(Seance s : planning.getSeances()) {
                if(!s.getExercices().isEmpty()) {
                    seances.add(s);
                }
            }
            adapter = new PlanningVisualizeAdapter(this, planning, seances, new PlanningVisualizeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Seance item) {
                    Intent intent = new Intent(PlanningVisualizeActivity.this,SeanceVisualizeActivity.class);
                    intent.putExtra("seance",item);
                    startActivity(intent);
                }
            });
                    planningVisualizer.setLayoutManager(llm);
            planningVisualizer.setAdapter(adapter);
        }


    }
}
