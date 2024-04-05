package com.example.aqw.modele;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.aqw.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Planning implements Cloneable, Serializable {

    public enum Jour {
        LUNDI, MARDI, MERCREDI, JEUDI, VENDREDI, SAMEDI, DIMANCHE;

        @NonNull
        @Override
        public String toString() {
            return this.name().charAt(0) + this.name().substring(1).toLowerCase();
        }

        public String toString(Context context) {
            switch (this) {
                case LUNDI:
                    return context.getString(R.string.lundi);
                case MARDI:
                    return context.getString(R.string.mardi);
                case MERCREDI:
                    return context.getString(R.string.mercredi);
                case JEUDI:
                    return context.getString(R.string.jeudi);
                case VENDREDI:
                    return context.getString(R.string.vendredi);
                case SAMEDI:
                    return context.getString(R.string.samedi);
                case DIMANCHE:
                    return context.getString(R.string.dimanche);
            }
            return "";
        }

        public static Jour valueOf(int nb) {
            switch (nb) {
                case 1:
                    return DIMANCHE;
                case 2:
                    return LUNDI;
                case 3:
                    return MARDI;
                case 4:
                    return MERCREDI;
                case 5:
                    return JEUDI;
                case 6:
                    return VENDREDI;
                case 7:
                    return SAMEDI;
                default:
                    return null;
            }
        }
    }
    private Map<Jour, Seance> plan;
    private Map<Seance,Jour> jourSeance;
    private String nom;

    public Planning(String nom) {
        this.nom = nom;
        this.plan = new HashMap<>();
        this.jourSeance = new HashMap<>();
    }
    @NonNull
    public String getNom() {
        return nom;
    }

    public boolean isPlanningSeancesEmpty() {
        for (Seance s : plan.values()) {
            if (s.getExercices().size()>0) {
                return false;
            }
        }
        return true;
    }


    public ArrayList<Seance> getSeances() {
        ArrayList<Seance> s = new ArrayList<>();
        for (Jour jour : Jour.values()) {
            if (plan.get(jour) != null) {
                s.add(plan.get(jour));
            }
        }
        return s;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSeance(Jour jour, Seance seance) {
        this.plan.put(jour,seance);
        this.jourSeance.put(seance,jour);
    }

    public Jour getJour(Seance seance) {return this.jourSeance.get(seance);}

    public Seance getSeance(Jour jour) {
        return this.plan.get(jour);
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder(getNom());

        for (Jour jour : Jour.values()) {
            if (plan.get(jour) != null) {
                toString.append(System.lineSeparator());
                toString.append("    ");
                toString.append(jour);
                toString.append(" :");
                toString.append(System.lineSeparator());
                toString.append(plan.get(jour).toString("        "));
            }
        }
        return toString.toString();
    }

    @NonNull
    @Override
    public Planning clone() {
        Planning clone = new Planning(this.nom);
        for (Map.Entry<Jour, Seance> entry: this.plan.entrySet()) {
            clone.setSeance(entry.getKey(), entry.getValue().clone());
        }
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Planning)) return false;
        Planning planning = (Planning) o;
        return toString().equals(planning.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(plan, nom);
    }
}

