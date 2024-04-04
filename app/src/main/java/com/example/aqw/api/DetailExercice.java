package com.example.aqw.api;

public class DetailExercice {
    private String nom;
    private String muscles;
    private String level;
    private String beginnerSets;
    private String intermediateSets;
    private String expertSets;
    private String equipment;
    private String explanation;
    private String longExplanation;
    public DetailExercice() {

    }

    public DetailExercice(String nom, String muscles, String level, String beginnerSets, String intermediateSets, String expertSets, String equipment, String explanation, String longExplanation) {
        this.nom = nom;
        this.muscles = muscles;
        this.level = level;
        this.beginnerSets = beginnerSets;
        this.intermediateSets = intermediateSets;
        this.expertSets = expertSets;
        this.equipment = equipment;
        this.explanation = explanation;
        this.longExplanation = longExplanation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMuscles() {
        return muscles;
    }

    public void setMuscles(String muscles) {
        this.muscles = muscles;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getBeginnerSets() {
        return beginnerSets;
    }

    public void setBeginnerSets(String beginnerSets) {
        this.beginnerSets = beginnerSets;
    }

    public String getIntermediateSets() {
        return intermediateSets;
    }

    public void setIntermediateSets(String intermediateSets) {
        this.intermediateSets = intermediateSets;
    }

    public String getExpertSets() {
        return expertSets;
    }

    public void setExpertSets(String expertSets) {
        this.expertSets = expertSets;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getLongExplanation() {
        return longExplanation;
    }

    public void setLongExplanation(String longExplanation) {
        this.longExplanation = longExplanation;
    }
}
