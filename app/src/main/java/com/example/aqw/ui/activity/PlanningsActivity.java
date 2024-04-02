package com.example.aqw.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aqw.R;
import com.example.aqw.database.DatabaseManager;
import com.example.aqw.modele.Planning;
import com.example.aqw.ui.adapter.RadioBoutonAdapter;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

public class PlanningsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> list;
    RadioBoutonAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_with_add_button_layout);

        TextView nomPage = findViewById(R.id.textPageName);
        Button button = findViewById(R.id.buttonCreation);
        nomPage.setText("Plannings");
        button.setText("Nouveau");

        listView = findViewById(R.id.list);
        Button createPlanning = findViewById(R.id.buttonCreation);

        DatabaseManager manager = new DatabaseManager(this);
        try {
            manager.open();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
        list = new ArrayList<>();
        List<Planning> listPlannings = manager.fetchPlannings();
        if (listPlannings != null) {
            for (Planning planning : listPlannings) {
                list.add(planning.getNom());
            }
        }

        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        adapter = new RadioBoutonAdapter(this,R.layout.planning_item,list, manager);
        listView.setAdapter(adapter);

        createPlanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanningsActivity.this, PlanningCreationActivity.class);
                startActivity(intent);
            }
        });


    }

}
