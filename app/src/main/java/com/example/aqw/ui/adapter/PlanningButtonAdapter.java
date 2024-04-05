package com.example.aqw.ui.adapter;




import static com.example.aqw.ui.activity.PlanningCreationActivity.CODE_REQUEST_SEANCE;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.example.aqw.R;
import com.example.aqw.modele.Planning;
import com.example.aqw.modele.Seance;
import com.example.aqw.ui.activity.SeanceCreationActivity;

import java.util.ArrayList;

public class PlanningButtonAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Seance> seances;

    private Activity getActivity() {
        return this.activity;
    }

    public PlanningButtonAdapter(Activity activity, ArrayList<Seance> seances) {
        super();
        this.activity=activity;
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
            convertView = LayoutInflater.from(activity).inflate(R.layout.jour_item, parent, false);
            holder = new ViewHolder();
            holder.button = convertView.findViewById(R.id.buttonAddSeance);
            holder.textView = convertView.findViewById(R.id.jourDeLaSemaineSeanceText);
            holder.delete = convertView.findViewById(R.id.buttonRemoveSeance);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(seances.get(position).getNom());
        if (seances.get(position).getExercices().isEmpty()) {
            holder.button.setCompoundDrawablesWithIntrinsicBounds(ActivityCompat.getDrawable(this.getActivity(), android.R.drawable.ic_input_add), null, null, null);
            holder.delete.setVisibility(View.INVISIBLE);
        } else {
            holder.button.setCompoundDrawablesWithIntrinsicBounds(ActivityCompat.getDrawable(this.getActivity(), android.R.drawable.ic_menu_edit), null, null, null);
            holder.delete.setVisibility(View.VISIBLE);
            holder.delete.setCompoundDrawablesWithIntrinsicBounds(ActivityCompat.getDrawable(this.getActivity(), android.R.drawable.ic_delete), null, null, null);
        }

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, SeanceCreationActivity.class);
                intent.putExtra("seance",seances.get(position));
                intent.putExtra("position",position);
                activity.startActivityForResult(intent,CODE_REQUEST_SEANCE);
            }
        });

        holder.delete.setOnClickListener((v) -> {
            seances.set(position, new Seance(Planning.Jour.valueOf((position+2)%7).name()));
            notifyDataSetChanged();
        });

        return convertView;
    }

    static class ViewHolder {
        Button delete;
        Button button;
        TextView textView;
    }
}