package com.example.aqw.ui.item;

import android.content.Context;
import android.widget.Button;

import com.example.aqw.modele.Seance;

public class SeanceCreationButton extends androidx.appcompat.widget.AppCompatButton {

    private Seance seance;
    private Button button;

    public SeanceCreationButton(Context context,Button button, Seance seance) {
        super(context);
        this.seance=seance;
        this.button=button;
    }

    public Seance getSeance() {return this.seance;};

    public Button getButton() {return this.button;}

}
