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
import com.example.aqw.modele.Exercice;
import com.example.aqw.modele.Planning;
import com.example.aqw.modele.Seance;

import java.util.ArrayList;
import java.util.ArrayList;

public class SeanceVisualizeAdapter extends RecyclerView.Adapter<SeanceVisualizeAdapter.ViewHolder> {

    private ArrayList<Exercice> exercices;
    private OnItemClickListener listener;

    private static LayoutInflater inflater = null;

    public interface OnItemClickListener {
        void onItemClick(Exercice item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nomExercice;
        TextView nbSeries;
        TextView nbReps;
        TextView notes;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.v("viewholder","bite");
            this.nomExercice = itemView.findViewById(R.id.exerciceNomSeanceVisualizer);
            this.nbSeries = itemView.findViewById(R.id.nbSeriesSeanceVisualizer);
            this.nbReps = itemView.findViewById(R.id.nbRepetitionsSeanceVisualizer);
            this.notes = itemView.findViewById(R.id.notesSeanceVisualizer);
        }

        public void onClickListener(final Exercice exercice, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(exercice);
                }
            });
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.v("onCreateView","bite");
        View view = inflater.inflate(R.layout.seance_visualizer_item,parent,false);
        ViewHolder v = new ViewHolder(view);
        return v;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.v("bind","bite");
        holder.onClickListener(exercices.get(position),listener);
        holder.nomExercice.setText(this.exercices.get(position).getNom());
        holder.nbSeries.setText("Nombre de séries : "+this.exercices.get(position).getNbSeries());
        holder.nbReps.setText("Nombre de répétitions : "+this.exercices.get(position).getNbRepetitions());
        holder.notes.setText(this.exercices.get(position).getNotes());
    }


    public SeanceVisualizeAdapter(Context context, ArrayList<Exercice> exercices, OnItemClickListener listener) {
        Log.v("planning","bite");
        this.exercices=exercices;
        this.listener=listener;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemCount() {
        return exercices.size();
    }


}
