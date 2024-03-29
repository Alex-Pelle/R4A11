package com.example.aqw.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.aqw.R;
import com.example.aqw.modele.Exercice;
import com.example.aqw.modele.Seance;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class PlanningButtonAdapter extends ArrayAdapter<String> {

    private ArrayList<String> plannings;
    private LayoutInflater layout;
    private Seance seance;

    public PlanningButtonAdapter(Context context, int ressource , ArrayList<String> plannings) {
        super(context,ressource,plannings);
        this.plannings=plannings;
        this.layout = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = layout.inflate(R.layout.jour_item, parent, false);
            holder = new ViewHolder();
            holder.button = convertView.findViewById(R.id.buttonAddSeance);
            holder.textView = convertView.findViewById(R.id.jourDeLaSemaineSeanceText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(plannings.get(position));
        if (this.seance == null) {
            holder.button.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this.getContext(), android.R.drawable.ic_input_add), null, null, null);
        } else {
            holder.button.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this.getContext(), android.R.drawable.ic_menu_edit), null, null, null);
        }


        return convertView;
    }


    static class ViewHolder {
        Button button;
        TextView textView;
    }
}