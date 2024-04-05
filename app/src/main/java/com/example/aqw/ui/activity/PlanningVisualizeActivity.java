package com.example.aqw.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aqw.R;
import com.example.aqw.modele.Planning;
import com.example.aqw.modele.Seance;
import com.example.aqw.ui.adapter.PlanningVisualizeAdapter;

import java.util.ArrayList;

public class PlanningVisualizeActivity extends GymTrackerActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planning_visualizer);
        RecyclerView planningVisualizer = findViewById(R.id.planningVisualizer);
        TextView planningNom = findViewById(R.id.nomPlanningVisualizer);

        Planning planning = (Planning) getIntent().getSerializableExtra("plan");
        if (planning !=null) {
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.HORIZONTAL);
            planningNom.setText(planning.getNom());
            ArrayList<Seance> seances = new ArrayList<>();
            for(Seance s : planning.getSeances()) {
                if(!s.getExercices().isEmpty()) {
                    seances.add(s);
                }
            }
            PlanningVisualizeAdapter adapter = new PlanningVisualizeAdapter(this, planning, seances, new PlanningVisualizeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Seance item) {
                    Intent intent = new Intent(PlanningVisualizeActivity.this, SeanceVisualizeActivity.class);
                    intent.putExtra("seance", item);
                    startActivity(intent);
                }
            });
                    planningVisualizer.setLayoutManager(llm);
            planningVisualizer.setAdapter(adapter);
        }


    }
}
