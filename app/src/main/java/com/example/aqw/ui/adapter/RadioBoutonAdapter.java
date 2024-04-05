package com.example.aqw.ui.adapter;

import static com.example.aqw.ui.activity.PlanningsActivity.CODE_REQUEST_PLANNING;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aqw.R;
import com.example.aqw.database.DatabaseManager;
import com.example.aqw.modele.Planning;
import com.example.aqw.ui.activity.PlanningCreationActivity;

import java.util.ArrayList;

public class RadioBoutonAdapter extends BaseAdapter {
    private static final String TAG = RadioBoutonAdapter.class.getSimpleName();

    private DatabaseManager dbManager;

    private ArrayList<Planning> plannings;

    private int selectedPosition = -1;

    private Activity activity;

    public RadioBoutonAdapter(Activity activity, ArrayList<Planning> plannings, DatabaseManager dbManager) {
        this.plannings=plannings;
        this.activity=activity;
        this.dbManager = dbManager;
        if (dbManager.getChoix() != null) {
            selectedPosition = plannings.indexOf(dbManager.getChoix());
        }
    }

    @Override
    public int getCount() {
        return plannings.size();
    }

    @Override
    public Object getItem(int i) {
        return plannings.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.planning_item, parent, false);
            holder = new ViewHolder();
            holder.radioButton = convertView.findViewById(R.id.radioButtonItem);
            holder.textView = convertView.findViewById(R.id.itemName);
            holder.edit = convertView.findViewById(R.id.editItem);
            holder.delete = convertView.findViewById(R.id.deleteItem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(plannings.get(position).getNom());
        holder.radioButton.setChecked(position == selectedPosition);

        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition!=position) {
                    selectedPosition = position;
                    dbManager.choisirPlanning(dbManager.fetchPlanning(plannings.get(position).getNom()));
                } else {
                    selectedPosition=-1;
                }

                notifyDataSetChanged();
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbManager.getChoix().equals(plannings.get(position)) && plannings.size()>1) {
                    Toast.makeText(activity,"Le planning doit être désélectionné pour être supprimé", Toast.LENGTH_SHORT).show();
                } else {
                    if(plannings.size()==1 ) {
                        selectedPosition =-1;
                    } else if (selectedPosition >= position) {
                        selectedPosition -= 1;
                    }
                    dbManager.deletePlanning(plannings.get(position));
                    plannings.remove(plannings.get(position));
                    notifyDataSetChanged();
                }

            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,PlanningCreationActivity.class);
                intent.putExtra("plan",plannings.get(position));
                intent.putExtra("position",position);
                activity.startActivityForResult(intent,CODE_REQUEST_PLANNING);
            }
        });

        return convertView;
    }


    static class ViewHolder {
        RadioButton radioButton;
        Button delete;
        Button edit;
        TextView textView;
    }
}
