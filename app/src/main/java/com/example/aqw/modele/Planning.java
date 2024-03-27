package com.example.aqw.modele;

import java.util.Map;

public class Planning {
    private Map<Jour, Seance> plan;
    private String nom;

    public Planning(String nom) {
        this.nom = nom;
    }

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

}

enum Jour {
    LUNDI,MARDI,MERCREDI,JEUDI,VENDREDI,SAMEDI,DIMANCHE
}