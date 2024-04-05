package com.example.aqw.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aqw.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ChronoActivity  extends AppCompatActivity {

    private static final String TAG = ChronoActivity.class.getSimpleName();
    private int duration;
    private int elapsed;
    private ProgressBar progressBar;
    private Button bouton;
    private EditText minutes;
    private EditText secondes;
    private TextView restant;
    private RadioGroup radio;
    private ScheduledExecutorService executor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono);

        this.progressBar =findViewById(R.id.progressBar);
        progressBar.setMax(0);
        this.bouton = findViewById(R.id.bouton);

        this.minutes = findViewById(R.id.minutes);
        this.secondes = findViewById(R.id.secondes);

        this.restant = findViewById(R.id.restant);
        this.duration = 0;
        this.elapsed = 0;

        this.radio = findViewById(R.id.radioGroup);
        executor = Executors.newScheduledThreadPool(1);

        Runnable increment = () -> {
            runOnUiThread(()->progressBar.setProgress(elapsed));
            elapsed++;
            if (radio.getCheckedRadioButtonId() == R.id.rebours) {
                runOnUiThread(()->restant.setText(elapsed >= duration ?
                        getString(R.string.default_temps_restant) :
                        getString(R.string.heure_formatee,(duration-elapsed)/60,(duration-elapsed)%60)));
            }
            else {
                runOnUiThread(()->restant.setText(elapsed >= duration ?
                        getString(R.string.default_temps_restant) :
                        getString(R.string.heure_formatee,(elapsed)/60,(elapsed)%60)));
            }
        };

        executor.scheduleAtFixedRate(increment, 0, 1, TimeUnit.SECONDS);


        bouton.setOnClickListener((e) -> {
            duration = Integer.parseInt(secondes.getText().toString()) + Integer.parseInt(minutes.getText().toString())*60;
            elapsed = 0;
            progressBar.setMax(duration);
        });

        secondes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int val = Integer.parseInt(charSequence.toString());
                    if (val > 59) {
                        minutes.setText(getString(R.string.nombre,val/60));
                        secondes.setText(getString(R.string.nombre,val%60));
                    }
                }
                catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
