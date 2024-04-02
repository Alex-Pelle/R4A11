package com.example.aqw.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.aqw.R;
import com.example.aqw.modele.Exercice;
import com.example.aqw.modele.Planning;
import com.example.aqw.modele.Seance;
import com.example.aqw.ui.activity.PlanningCreationActivity;
import com.example.aqw.ui.activity.SeanceCreationActivity;
import com.example.aqw.ui.item.SeanceCreationButton;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class PlanningButtonAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Seance> seances;

    private Context getContext() {
        return this.context;
    }

    public PlanningButtonAdapter(Context context, ArrayList<Seance> seances) {
        super();
        this.context=context;
        this.seances=seances;
    }

    @Override
    public int getCount() {
        return seances.size();
    }

    @Override
    public Object getItem(int i) {
        return seances.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.jour_item, parent, false);
            holder = new ViewHolder();
            holder.button = convertView.findViewById(R.id.buttonAddSeance);
            holder.button.setSeance(seances.get(position));
            holder.textView = convertView.findViewById(R.id.jourDeLaSemaineSeanceText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(seances.get(position).getNom());
        if (seances.get(position).getExercices().isEmpty()) {
            holder.button.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this.getContext(), android.R.drawable.ic_input_add), null, null, null);
        } else {
            holder.button.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this.getContext(), android.R.drawable.ic_menu_edit), null, null, null);
        }

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SeanceCreationActivity.class);
                intent.putExtra("Seance",holder.button.getSeance());
                context.startActivity(intent);
            }
        });


        return convertView;
    }

    static class ViewHolder {
        SeanceCreationButton button;
        TextView textView;
    }
}