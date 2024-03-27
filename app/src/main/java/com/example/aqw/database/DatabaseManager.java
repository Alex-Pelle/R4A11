package com.example.aqw.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.aqw.modele.*;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DatabaseManager {
    private static final String TAG = DatabaseManager.class.getSimpleName();
    private DatabaseHelper dbHelper;
    private final Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        this.context = context;
    }

    public DatabaseManager open() throws SQLDataException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void seed() {
        Seeder seeder = new Seeder();
        seeder.seed(this);
    }
    private long insertExercice(Exercice exercice, long idSeance) {
        ContentValues contentValues = new ContentValues();
        Log.v(TAG,"Insertion exercice : "+exercice.getNom());
        contentValues.put(DatabaseHelper.COMPOSITION_NOM_EXERCICE, exercice.getNom());
        contentValues.put(DatabaseHelper.COMPOSITION_ID_SEANCE, idSeance);
        contentValues.put(DatabaseHelper.COMPOSITION_NOMBRE_SERIES, exercice.getNbSeries());
        contentValues.put(DatabaseHelper.COMPOSITION_NOMBRE_REPETITIONS, exercice.getNbRepetitions());
        return database.insert(DatabaseHelper.TABLE_COMPOSITION, null, contentValues);
    }

    private long insertSeance(Seance seance) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.SEANCE_NOM, seance.getNom());
        Log.v(TAG,"Insertion seance : "+seance.getNom());
        long idSeance = database.insert(DatabaseHelper.TABLE_SEANCE, null, contentValues);

        for (Exercice exercice : seance) {
            insertExercice(exercice, idSeance);
        }

        return idSeance;
    }
    public long insertPlanning(Planning planning) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.PLANNING_NOM, planning.getNom());
        Log.v(TAG,"Insertion planning : " + planning.getNom());
        for (Planning.Jour jour : Planning.Jour.values()) {
            Seance seanceDuJour = planning.getSeance(jour);
            if (seanceDuJour != null ) {
                long idSeance = insertSeance(seanceDuJour);
                contentValues.put(jour.name().toLowerCase(), idSeance);
            }
        }
        return database.insert(DatabaseHelper.TABLE_PLANNING, null, contentValues);
    }

    @SuppressLint("Range")
    public List<Planning> fetchPlannings() {
        Log.v(TAG, "Fetch");
        Cursor cursor = database.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_PLANNING,null);

        List<Planning> plannings = new LinkedList<>();
        Planning planning;
        while (!cursor.isLast()) {
            cursor.moveToNext();
            planning  = new Planning(cursor.getString(cursor.getColumnIndex(DatabaseHelper.PLANNING_NOM)));


            for (Planning.Jour jour : Planning.Jour.values()) {
                int idSeanceDuJour = cursor.getInt(cursor.getColumnIndex(jour.name().toLowerCase()));
                if (!cursor.isNull(cursor.getColumnIndex(jour.name().toLowerCase()))) {
                    planning.setSeance(jour,fetchSeance(idSeanceDuJour));
                }
            }


            plannings.add(planning);
        }
        Log.v(TAG, "PLANNING : "+System.lineSeparator() + plannings.get(0).toString());
        cursor.close();
        return plannings;
    }

    private Seance fetchSeance(int id) {
        Cursor cursor = database.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_SEANCE+" WHERE "+DatabaseHelper.SEANCE_ID+ " = ?",new String[]{""+id});
        Seance seance = null;
        if (!cursor.isAfterLast()) {
            cursor.moveToNext();
            seance = new Seance(cursor.getString(1));

            seance.addExercices(fetchExercices(id));
            Log.v(TAG, seance.toString());
        }
        cursor.close();
        return seance;
    }

    @SuppressLint("Range")
    private List<Exercice> fetchExercices(int id) {
        Cursor cursor = database.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_COMPOSITION+" WHERE "+DatabaseHelper.COMPOSITION_ID_SEANCE+" = ?",new String[]{""+id});
        List<Exercice> exercices = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return exercices;
        }
        while (!cursor.isAfterLast()) {
            exercices.add(new Exercice(
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.COMPOSITION_NOM_EXERCICE)),
                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COMPOSITION_NOMBRE_SERIES)),
                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COMPOSITION_NOMBRE_REPETITIONS))
                    ));
            cursor.moveToNext();
        }
        cursor.close();
        return exercices;
    }

    public void emptyDatabase() {
        dbHelper.onUpgrade(database,0,0);
        dbHelper.onCreate(database);
    }
}
