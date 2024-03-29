package com.example.aqw.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.aqw.R;
import com.example.aqw.modele.Seance;
import com.example.aqw.ui.activity.PlanningCreationActivity;
import com.example.aqw.ui.activity.PlanningsActivity;
import com.example.aqw.ui.activity.SeanceCreationActivity;

import java.util.ArrayList;

public class SeanceCreationAdapter extends ArrayAdapter<String> {

    private ArrayList<String> exercices;
    private LayoutInflater layout;
    private Context context;
    private Seance seance;

    public SeanceCreationAdapter(Context context, int ressource , ArrayList<String> exercices,Seance seance) {
        super(context,ressource,exercices);
        this.context=context;
        this.exercices=exercices;
        this.layout = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PlanningButtonAdapter.ViewHolder holder;

        if (convertView == null) {
            convertView = layout.inflate(R.layout.list_item, parent, false);
            holder = new PlanningButtonAdapter.ViewHolder();
            holder.button = convertView.findViewById(R.id.buttonCreation);
            holder.textView = convertView.findViewById(R.id.textPageName);
            convertView.setTag(holder);
        } else {
            holder = (PlanningButtonAdapter.ViewHolder) convertView.getTag();
        }

        holder.textView.setText(exercices.get(position));

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("popokvopefvk","^pldc^plc^p");
                Intent intent = new Intent(context, PlanningCreationActivity.class);
                context.startActivity(intent);
            }
        });




        return convertView;
    }


    static class ViewHolder {
        Button button;
        TextView textView;
    }
}