package com.example.aqw.ui.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aqw.R;
import com.example.aqw.database.DatabaseManager;
import com.example.aqw.modele.Exercice;
import com.example.aqw.modele.Planning;
import com.example.aqw.modele.Seance;

import java.sql.SQLDataException;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends GymTrackerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DatabaseManager manager = new DatabaseManager(this);
        try {
            manager.open();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
        //manager.seed();
        TextView nomSeanceJour = findViewById(R.id.nomSeanceDuJourText);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Planning.Jour aujourdhui = Planning.Jour.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
        Planning planning = manager.getChoix();

        if (planning!=null) {
            Seance seanceDuJour = planning.getSeance(aujourdhui);
            if (!seanceDuJour.getExercices().isEmpty()) {
                nomSeanceJour.setText(seanceDuJour.getNom());
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
                for (Exercice ex : seanceDuJour) {
                    adapter.add(ex.toString());
                }
                ((ListView) findViewById(R.id.list)).setAdapter(adapter);
            }
            else {
                nomSeanceJour.setText("Repos");
                ((ListView) findViewById(R.id.list)).setAdapter(null);
            }
        } else {
            nomSeanceJour.setText("Repos");
            ((ListView) findViewById(R.id.list)).setAdapter(null);
        }

        manager.close();
        manager= null;
    }




}