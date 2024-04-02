package com.example.aqw.modele;

import java.io.Serializable;
import java.util.Objects;

public class Exercice implements Cloneable, Serializable {
    private String nom;
    private int nbSeries;
    private int nbRepetitions;
    private String notes;

    public Exercice(String nom, int nbSeries, int nbRepetitions, String notes){
        this.nom = nom;
        this.nbSeries = nbSeries;
        this.nbRepetitions = nbRepetitions;
        this.notes = notes;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbSeries() {
        return nbSeries;
    }

    public void setNbSeries(int nbSeries) {
        this.nbSeries = nbSeries;
    }

    public int getNbRepetitions() {
        return nbRepetitions;
    }

    public void setNbRepetitions(int nbRepetitions) {
        this.nbRepetitions = nbRepetitions;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other instanceof Exercice) {
            Exercice another = (Exercice) other;
            return Objects.equals(another.getNom(), this.getNom());
        }
        return false;
    }
    @Override
    public String toString() {
        return getNom() + " " + getNbSeries() + "x" + getNbRepetitions() +  (!getNotes().isEmpty() ? " ["+getNotes() + "]":"");
    }

    @Override
    public Exercice clone() {
        try {
            return (Exercice) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
