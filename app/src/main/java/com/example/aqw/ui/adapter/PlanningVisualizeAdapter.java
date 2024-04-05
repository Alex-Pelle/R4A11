package com.example.aqw.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aqw.R;
import com.example.aqw.modele.Planning;
import com.example.aqw.modele.Seance;

import java.util.ArrayList;
import java.util.ArrayList;

public class PlanningVisualizeAdapter extends RecyclerView.Adapter<PlanningVisualizeAdapter.ViewHolder> {

    private ArrayList<Seance> seances;
    private Planning planning;

    private static LayoutInflater inflater = null;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView jourSemaine;
        TextView nomSeance;
        TextView nbExercices;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.v("viewholder","bite");
            this.jourSemaine = itemView.findViewById(R.id.jourDeLaSemainePlanningVisualizer);
            this.nomSeance = itemView.findViewById(R.id.titreDeLaSeancePlanningVisualizer);
            this.nbExercices = itemView.findViewById(R.id.nombreExercicesPlanningVisualizer);
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.v("onCreateView","bite");
        View view = inflater.inflate(R.layout.planning_visualize_item,parent,false);
        ViewHolder v = new ViewHolder(view);
        return v;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.v("bind","bite");
        holder.jourSemaine.setText(this.planning.getJour(seances.get(position)).toString());
        holder.nomSeance.setText(seances.get(position).getNom());
        holder.nbExercices.setText(""+seances.get(position).countExercices());
    }


    public PlanningVisualizeAdapter(Context context, Planning planning, ArrayList<Seance> seances) {
        Log.v("planning","bite");
        this.planning=planning;
        this.seances=seances;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemCount() {
        return seances.size();
    }


}
