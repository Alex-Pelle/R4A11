package com.example.aqw.ui.activity;

import static com.example.aqw.ui.activity.SeanceCreationActivity.REQUEST_EXERCISE_CODE;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aqw.R;
import com.example.aqw.database.DatabaseManager;
import com.example.aqw.modele.Planning;
import com.example.aqw.modele.Seance;
import com.example.aqw.ui.adapter.RadioBoutonAdapter;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

public class PlanningsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Planning> planning;
    RadioBoutonAdapter adapter;

    public static int CODE_REQUEST_PLANNING = 12345;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_with_add_button_layout);

        TextView nomPage = findViewById(R.id.textPageName);
        Button createPlanning = findViewById(R.id.buttonCreation);
        nomPage.setText(R.string.plannings);
        createPlanning.setText(R.string.nouveau);

        listView = findViewById(R.id.list);
        planning = new ArrayList<>();
        DatabaseManager manager = new DatabaseManager(this);
        fillPlanningList(manager,planning);

        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        adapter = new RadioBoutonAdapter(this,planning, manager);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(PlanningsActivity.this,"clic",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PlanningsActivity.this, PlanningVisualizeActivity.class);
            intent.putExtra("plan", planning.get(i));
            Log.v("plan",planning.get(i).toString());
            startActivity(intent);
        });

        listView.setAdapter(adapter);
        createPlanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanningsActivity.this, PlanningCreationActivity.class);
                startActivityForResult(intent,CODE_REQUEST_PLANNING);
            }
        });




    }

    private void fillPlanningList(DatabaseManager manager, ArrayList<Planning> planning) {
        try {
            manager.open();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
        planning.clear();
        List<Planning> listPlannings = manager.fetchPlannings();
        if (listPlannings != null) {
            for (Planning p : listPlannings) {
                planning.add(p);
                Log.v("planning",planning.toString());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_REQUEST_PLANNING && resultCode == RESULT_OK) {
            DatabaseManager manager = new DatabaseManager(this);

            fillPlanningList(manager,planning);
            if (planning.size()==1) {
                manager.choisirPlanning(planning.get(0));
            }
            adapter.notifyDataSetChanged();
            manager.close();
        }
    }

}
