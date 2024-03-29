package com.example.aqw.ui.activity;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.aqw.R;

public class MainActivity extends AppCompatActivity {


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.nav_bar,menu);
        MenuItem accueil = menu.getItem(0);
        MenuItem plannings = menu.getItem(1);
        MenuItem exercices = menu.getItem(2);
        MenuItem quit = menu.getItem(3);

        quit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                System.exit(0);
                return true;
            }
        });

        plannings.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(MainActivity.this, PlanningsActivity.class);
                startActivity(intent); // Correction ici
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
}