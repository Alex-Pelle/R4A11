package com.example.aqw;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PlanningsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> list;
    ArrayAdapter adapter;
    String[] plannings = {"P1","P2","P3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listPlanning);

        list = new ArrayList<>();

        for (int i = 0;i<plannings.length;i++){

            list.add(plannings[i]);

        }
        listView.setChoiceMode(listView.CHOICE_MODE_SINGLE);

        adapter = new ArrayAdapter(PlanningsActivity.this,android.R.layout.simple_list_item_single_choice,list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(MainActivity.this, "Selected -> " + version[i], Toast.LENGTH_SHORT).show();
            }
        });

    }

}
