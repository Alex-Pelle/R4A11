package com.example.aqw.modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Seance implements Iterable<Exercice>{
    private List<Exercice> exercices;
    private String nom;

    public Seance(String nom) {
        this.nom = nom;
        this.exercices = new ArrayList<>();
    }

    private void addExercice(Exercice exercice) {
        exercices.add(exercice);
    }
    private void addExercices(Collection<Exercice> exercices) {
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
            if (Objects.equals(another.getNom(), this.getNom()) && Objects.equals(another.iterator(), this.iterator())) {
                return true;
            }
        }
        return false;
    }
}
