package com.example.aqw.ui.activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aqw.R;
import com.example.aqw.ui.adapter.PlanningButtonAdapter;

import java.util.ArrayList;

public class PlanningCreationActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> list;
    PlanningButtonAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_planning);
        listView = findViewById(R.id.list);

        list = new ArrayList<>();
        list.add("Lundi");
        list.add("Mardi");
        list.add("Mercredi");
        list.add("Jeudi");
        list.add("Vendredi");
        list.add("Samedi");
        list.add("Dimanche");

        listView.setChoiceMode(listView.CHOICE_MODE_SINGLE);

        adapter = new PlanningButtonAdapter(this,R.layout.jour_item,list);
        listView.setAdapter(adapter);


    }
}
