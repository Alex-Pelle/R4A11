package com.example.aqw.ui.adapter;

import static com.example.aqw.ui.activity.SeanceCreationActivity.REQUEST_EXERCISE_CODE;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.aqw.R;
import com.example.aqw.modele.Exercice;
import com.example.aqw.ui.activity.ExerciseParameter;

import java.util.ArrayList;

public class SeanceCreationAdapter extends BaseAdapter {

    private ArrayList<Exercice> exercices;
    private LayoutInflater layout;
    private Activity activity;

    public SeanceCreationAdapter(Activity activity, ArrayList<Exercice> exercices) {
        super();
        this.activity=activity;
        this.exercices=exercices;
        this.layout = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return exercices.size();
    }

    @Override
    public Object getItem(int i) {
        return exercices.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.delete = convertView.findViewById(R.id.deleteItem);
            holder.edit = convertView.findViewById(R.id.editItem);
            holder.textView = convertView.findViewById(R.id.itemName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(exercices.get(position).getNom());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercices.remove(exercices.get(position));
                notifyDataSetChanged();
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ExerciseParameter.class);
                intent.putExtra("Exercice",exercices.get(position));
                intent.putExtra("Position",position);
                activity.startActivityForResult(intent,REQUEST_EXERCISE_CODE);
            }
        });

        return convertView;
    }


    static class ViewHolder {
        Button delete;
        Button edit;
        TextView textView;
    }
}