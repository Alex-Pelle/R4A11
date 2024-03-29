package com.example.aqw.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aqw.R;
import com.example.aqw.ui.adapter.PlanningButtonAdapter;

import java.util.ArrayList;

public class SeanceCreationActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> list;
    PlanningButtonAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_seance);
        listView = findViewById(R.id.list);

        TextView nomPage = findViewById(R.id.textPageName);
        Button button = findViewById(R.id.buttonCreation);
        nomPage.setText("Création de séance");
        button.setText("Ajouter un exercice");
        list = new ArrayList<>();

        listView.setChoiceMode(listView.CHOICE_MODE_SINGLE);

        adapter = new PlanningButtonAdapter(this,R.layout.jour_item,list);
        listView.setAdapter(adapter);


    }
}
