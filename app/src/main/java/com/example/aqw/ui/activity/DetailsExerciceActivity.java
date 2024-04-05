package com.example.aqw.ui.activity;

import android.os.Bundle;
import android.widget.TextView;


import com.example.aqw.R;
import com.example.aqw.api.ApiManager;
import com.example.aqw.api.DetailExercice;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DetailsExerciceActivity extends GymTrackerActivity {
    ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercices_details);
        executor.submit(() -> {
            try {
                DetailExercice details = new ApiManager(this).details(getIntent().getStringExtra("Nom"));

                runOnUiThread(() -> this.afficher(details) );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void afficher(DetailExercice details) {
        ((TextView)findViewById(R.id.descAccueil)).setText(details.getNom());
        ((TextView)findViewById(R.id.descDataDifficulte)).setText(details.getLevel());
        ((TextView)findViewById(R.id.descDataMuscleCible)).setText(details.getMuscles());
        ((TextView)findViewById(R.id.descDataDebutant)).setText(details.getBeginnerSets());
        ((TextView)findViewById(R.id.descDataConfirme)).setText(details.getExpertSets());
        ((TextView)findViewById(R.id.descDataIntermediaire)).setText(details.getIntermediateSets());
        ((TextView)findViewById(R.id.descDataEquipement)).setText(details.getEquipment());
        ((TextView)findViewById(R.id.descDataMiseEnPlaceSimple)).setText(details.getExplanation());
        ((TextView)findViewById(R.id.descDataMiseEnPlaceDetaillee)).setText(details.getLongExplanation());
    }
}
