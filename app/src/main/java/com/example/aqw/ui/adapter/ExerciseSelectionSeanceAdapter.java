package com.example.aqw.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.aqw.R;
import com.example.aqw.modele.Seance;

import java.util.ArrayList;

public class ExerciseSelectionSeanceAdapter  extends ArrayAdapter<String> {

    private ArrayList<String> exercices;
    private LayoutInflater layout;
    private Context context;

    public ExerciseSelectionSeanceAdapter(Context context, int ressource , ArrayList<String> exercices) {
        super(context,ressource,exercices);
        this.context=context;
        this.exercices=exercices;
        this.layout = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = layout.inflate(R.layout.exercise_item, parent, false);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.nomExo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(exercices.get(position));

        return convertView;
    }


    static class ViewHolder {
        TextView textView;
    }
}
