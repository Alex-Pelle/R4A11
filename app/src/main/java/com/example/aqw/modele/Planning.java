package com.example.aqw.modele;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.Serializable;
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
    private String nom;

    public Planning(String nom) {
        this.nom = nom;
        this.plan = new HashMap<>();
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
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSeance(Jour jour, Seance seance) {
        this.plan.put(jour,seance);
    }

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

