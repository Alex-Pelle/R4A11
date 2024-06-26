package com.example.aqw.database;

import com.example.aqw.modele.Exercice;
import com.example.aqw.modele.Planning;
import com.example.aqw.modele.Seance;

public class Seeder {
    public void seed(DatabaseManager manager) {
        //CLASSE PLUS UTILISEE, seulement pour tester l'application au début
        manager.emptyDatabase();
        String notes = "";
        Exercice bench5x5 = new Exercice("Développé couché",5,5, notes);
        Exercice ohp3x8 = new Exercice("Développé militaire",3,8, notes);
        Exercice el3x12 = new Exercice("Élévations latérales",3,12, notes);
        Exercice extensionsTriceps = new Exercice("Extensions triceps corde",3,10, notes);
        Seance push = new Seance("Push");
        push.addExercice(bench5x5);
        push.addExercice(ohp3x8);
        push.addExercice(el3x12);
        push.addExercice(extensionsTriceps);

        Exercice terre = new Exercice("Soulevé de terre",5,5, notes);
        Exercice tractions = new Exercice("Tractions",3,8, notes);
        Exercice rowing = new Exercice("Rowing Barre",3,12, notes);
        Exercice curls = new Exercice("Curls biceps",3,10, notes);
        Seance pull = new Seance("Pull");
        pull.addExercice(terre);
        pull.addExercice(tractions);
        pull.addExercice(rowing);
        pull.addExercice(curls);

        Exercice squat = new Exercice("Squat", 1,3, "Top Set");
        Exercice squatSeries = new Exercice("Squat",4,4, "Longue négative");
        Exercice rdl = new Exercice("RDL", 4,8, notes);
        Seance legs = new Seance("Legs");
        legs.addExercice(squat);
        legs.addExercice(squatSeries);
        legs.addExercice(rdl);

        Planning ppl = new Planning("PPLx2");
        ppl.setSeance(Planning.Jour.LUNDI, push);
        ppl.setSeance(Planning.Jour.MARDI, pull);
        ppl.setSeance(Planning.Jour.MERCREDI, legs);
        ppl.setSeance(Planning.Jour.JEUDI, push);
        ppl.setSeance(Planning.Jour.VENDREDI, pull);
        ppl.setSeance(Planning.Jour.SAMEDI, legs);

        manager.insertPlanning(ppl);

        manager.deletePlanning(ppl);

        manager.insertPlanning(ppl);

        Planning ppl2 = ppl.clone();
        ppl2.setNom("Push pull legs");

        manager.insertPlanning(ppl2);

        manager.choisirPlanning(ppl2);
    }
}
