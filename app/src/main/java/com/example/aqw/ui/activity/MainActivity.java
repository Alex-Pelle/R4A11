package com.example.aqw.ui.activity;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aqw.R;
import com.example.aqw.database.DatabaseManager;
import com.example.aqw.modele.Exercice;
import com.example.aqw.modele.Planning;
import com.example.aqw.modele.Seance;

import java.sql.SQLDataException;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.nav_bar,menu);
        MenuItem accueil = menu.getItem(0);
        MenuItem plannings = menu.getItem(1);
        MenuItem exercices = menu.getItem(2);
        MenuItem chrono = menu.getItem(3);
        MenuItem quit = menu.getItem(4);

        quit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                System.exit(0);
                return true;
            }
        });

        chrono.setOnMenuItemClickListener((item) -> {
            Intent intent = new Intent(MainActivity.this, ChronoActivity.class);
            startActivity(intent);
            return true;
        });

        exercices.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(MainActivity.this, ExercisesActivity.class);
                intent.putExtra("selection",false);
                startActivity(intent);
                return true;
            }
        });


        plannings.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(MainActivity.this, PlanningsActivity.class);
                startActivity(intent);
                return true;
            }
        });

        return true;
    }





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

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Planning.Jour aujourdhui = Planning.Jour.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
        Planning planning = manager.getChoix();
        Seance seanceDuJour = planning.getSeance(aujourdhui);
        if (seanceDuJour != null) {
            Log.v(TAG, seanceDuJour.toString());
            ((TextView) findViewById(R.id.nomSeanceDuJourText)).setText(seanceDuJour.getNom());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
            for (Exercice ex : seanceDuJour) {
                adapter.add(ex.toString());
            }
            ((ListView) findViewById(R.id.list)).setAdapter(adapter);
        }


        manager.close();
        manager= null;
    }




}