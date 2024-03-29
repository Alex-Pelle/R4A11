package com.example.aqw.modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Seance implements Iterable<Exercice>, Cloneable{
    private List<Exercice> exercices;
    private String nom;

    public Seance(String nom) {
        this.nom = nom;
        this.exercices = new ArrayList<>();
    }

    public void addExercice(Exercice exercice) {
        exercices.add(exercice);
    }
    public void addExercices(Collection<Exercice> exercices) {
        this.exercices.addAll(exercices);
    }

    public Exercice getExercice(int index) {
        return exercices.get(index);
    }

    public Iterator<Exercice> iterator() {
        return exercices.iterator();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other instanceof Seance) {
            Seance another = (Seance) other;
            return Objects.equals(another.getNom(), this.getNom()) && Objects.equals(another.iterator(), this.iterator());
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder(getNom());
        for (Exercice exo: this) {
            toString.append(System.lineSeparator()).append("    ").append(exo);
        }
        return toString.toString();
    }

    public String toString(String indent) {
        StringBuilder toString = new StringBuilder(indent + getNom() +" :");
        for (Exercice exo: this) {
            toString.append(System.lineSeparator());
            toString.append("    ");
            toString.append(indent);
            toString.append(exo);
        }
        return toString.toString();
    }

    @Override
    public Seance clone() {
        Seance clone = new Seance(this.nom);
        for (Exercice exercice : this) {
            clone.addExercice(exercice.clone());
        }
        return clone;
    }
}
