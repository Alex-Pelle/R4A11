package com.example.aqw.ui.activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aqw.R;

public abstract class GymTrackerActivity extends AppCompatActivity {
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.nav_bar,menu);
        MenuItem accueil = menu.getItem(0);
        MenuItem plannings = menu.getItem(1);
        MenuItem chrono = menu.getItem(2);
        MenuItem quit = menu.getItem(3);

        quit.setOnMenuItemClickListener((MenuItem item) -> {
                this.finishAffinity();
                return true;
            });

        accueil.setOnMenuItemClickListener(menuItem -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        });

        chrono.setOnMenuItemClickListener((item) -> {
            Intent intent = new Intent(this, ChronoActivity.class);
            startActivity(intent);
            return true;
        });


        plannings.setOnMenuItemClickListener((MenuItem item) -> {
                Intent intent = new Intent(this, PlanningsActivity.class);
                startActivity(intent);
                return true;
            });

        return true;
    }

}
