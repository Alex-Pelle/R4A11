package com.example.aqw.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aqw.R;

import java.util.ArrayList;

public class RadioBoutonAdapter extends ArrayAdapter<String> {

    private ArrayList<String> plannings;
    private LayoutInflater layout;
    private int selectedPosition = -1;

    public RadioBoutonAdapter(Context context, int ressource , ArrayList<String> plannings) {
        super(context,ressource,plannings);
        this.plannings=plannings;
        this.layout = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = layout.inflate(R.layout.planning_item, parent, false);
            holder = new ViewHolder();
            holder.radioButton = convertView.findViewById(R.id.radioButtonItem);
            holder.textView = convertView.findViewById(R.id.listItemTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(plannings.get(position));
        holder.radioButton.setChecked(position == selectedPosition);

        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition!=position) {
                    selectedPosition = position;
                } else {
                    selectedPosition=-1;
                }

                notifyDataSetChanged();
            }
        });

        return convertView;
    }


    static class ViewHolder {
        RadioButton radioButton;
        TextView textView;
    }
}
