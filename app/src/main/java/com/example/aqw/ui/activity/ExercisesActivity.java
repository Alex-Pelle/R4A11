package com.example.aqw.ui.activity;

import static com.example.aqw.ui.activity.SeanceCreationActivity.REQUEST_EXERCISE_CODE;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.aqw.R;
import com.example.aqw.api.ApiManager;
import com.example.aqw.modele.Exercice;
import com.example.aqw.ui.adapter.ExerciseSelectionSeanceAdapter;

import java.io.IOException;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExercisesActivity extends GymTrackerActivity {

    private static final String TAG = ExercisesActivity.class.getSimpleName();
    ListView listView;
    Map<String,String> exercices;
    ApiManager apiManager;

    ExecutorService executor = Executors.newSingleThreadExecutor();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        apiManager = new ApiManager(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercices);
        listView = findViewById(R.id.list);
        Toast.makeText(this,R.string.connexion_api , Toast.LENGTH_SHORT).show();

        executor.submit(
                () -> {
                    Log.v(TAG, "fetch de l'api commence");
                    try {
                        exercices = apiManager.nomsExercices();
                        Log.v(TAG, "fetch de l'api fini : " + System.lineSeparator() + exercices);
                        ExerciseSelectionSeanceAdapter adapter = new ExerciseSelectionSeanceAdapter( ExercisesActivity.this,R.layout.exercise_item,exercices);
                        runOnUiThread(() -> listView.setAdapter(adapter));
                        runOnUiThread(() -> findViewById(R.id.loadingBar).setVisibility(View.GONE));

                        listView.setOnItemClickListener( (adapterView, view, i, l) -> {
                            Intent intent = null;
                            if (getIntent().getBooleanExtra("selection", true)) {
                                intent = new Intent(ExercisesActivity.this,ExerciseParameter.class);
                            }
                            else {
                                intent = new Intent(ExercisesActivity.this,DetailsExerciceActivity.class);
                            }
                            intent.putExtra("Nom",adapter.getItem(i).getKey());
                            startActivityForResult(intent,REQUEST_EXERCISE_CODE);
                        });
                    } catch (IOException e) {
                        runOnUiThread(() -> Toast.makeText(ExercisesActivity.this,R.string.erreur_api,Toast.LENGTH_SHORT).show());
                    }
                }
        );

        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EXERCISE_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Exercice exercice = (Exercice) extras.get("exo");
            int position = (Integer) extras.get("Position");
            Intent intentRetour = new Intent();
            intentRetour.putExtra("exo",exercice);
            intentRetour.putExtra("Position",position);
            setResult(RESULT_OK,intentRetour);
            finish();
        }
    }
}
