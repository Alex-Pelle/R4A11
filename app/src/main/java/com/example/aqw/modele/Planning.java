package com.example.aqw.modele;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class Planning implements Cloneable{

    public enum Jour {
        LUNDI, MARDI, MERCREDI, JEUDI, VENDREDI, SAMEDI, DIMANCHE;

        @NonNull
        @Override
        public String toString() {
            return this.name().charAt(0) + this.name().substring(1).toLowerCase();
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
}

