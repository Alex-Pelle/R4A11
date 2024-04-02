package com.example.aqw.ui.item;

import android.content.Context;

import com.example.aqw.modele.Seance;

public class SeanceCreationButton extends androidx.appcompat.widget.AppCompatButton {

    private Seance seance;

    public SeanceCreationButton(Context context, Seance seance) {
        super(context);
        this.seance=seance;
    }

    public Seance getSeance() {return this.seance;};

    public void setSeance(Seance seance) {this.seance=seance;}

}
