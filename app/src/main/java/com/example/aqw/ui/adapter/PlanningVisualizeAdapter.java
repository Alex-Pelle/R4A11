package com.example.aqw.ui.adapter;

import android.content.Context;
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

public class PlanningVisualizeAdapter extends RecyclerView.Adapter<PlanningVisualizeAdapter.ViewHolder> {

    private ArrayList<Seance> seances;
    private Planning planning;
    private OnItemClickListener listener;
    private Context context;

    private static LayoutInflater inflater = null;

    public interface OnItemClickListener {
        void onItemClick(Seance item);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView jourSemaine;
        TextView nomSeance;
        TextView nbExercices;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.jourSemaine = itemView.findViewById(R.id.jourDeLaSemainePlanningVisualizer);
            this.nomSeance = itemView.findViewById(R.id.titreDeLaSeancePlanningVisualizer);
            this.nbExercices = itemView.findViewById(R.id.nombreExercicesPlanningVisualizer);
        }

        public void onClickListener(final Seance seance, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(seance);
                }
            });
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.planning_visualize_item,parent,false);
        ViewHolder v = new ViewHolder(view);
        return v;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onClickListener(seances.get(position),listener);
        holder.jourSemaine.setText(this.planning.getJour(seances.get(position)).toString(context));
        holder.nomSeance.setText(seances.get(position).getNom());
        holder.nbExercices.setText(context.getResources().getQuantityString(R.plurals.exercice, seances.get(position).countExercices(), seances.get(position).countExercices()));
    }


    public PlanningVisualizeAdapter(Context context, Planning planning, ArrayList<Seance> seances, OnItemClickListener listener) {
        this.planning = planning;
        this.seances = seances;
        this.listener = listener;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemCount() {
        return seances.size();
    }


}
