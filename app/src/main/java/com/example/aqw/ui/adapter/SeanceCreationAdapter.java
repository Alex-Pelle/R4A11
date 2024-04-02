package com.example.aqw.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.aqw.R;
import com.example.aqw.modele.Seance;
import com.example.aqw.ui.activity.PlanningCreationActivity;
import com.example.aqw.ui.activity.PlanningsActivity;
import com.example.aqw.ui.activity.SeanceCreationActivity;

import java.util.ArrayList;

public class SeanceCreationAdapter extends BaseAdapter {

    private ArrayList<String> exercices;
    private LayoutInflater layout;
    private Context context;

    public SeanceCreationAdapter(Context context, int ressource , ArrayList<String> exercices,Seance seance) {
        super();
        this.context=context;
        this.exercices=exercices;
        this.layout = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.button = convertView.findViewById(R.id.buttonCreation);
            holder.textView = convertView.findViewById(R.id.textPageName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(exercices.get(position));

        return convertView;
    }


    static class ViewHolder {
        Button button;
        TextView textView;
    }
}