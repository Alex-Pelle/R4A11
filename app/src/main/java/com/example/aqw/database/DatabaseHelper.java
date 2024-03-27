package com.example.aqw.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "GYMTRACKER.DB";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_TABLE_PLANNING_QUERY = "CREATE TABLE planning (" +
            "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "  nom VARCHAR(25)," +
            "  lundi INTEGER," +
            "  mardi INTEGER," +
            "  mercredi INTEGER," +
            "  jeudi INTEGER," +
            "  vendredi INTEGER," +
            "  samedi INTEGER," +
            "  dimanche INTEGER," +
            "  FOREIGN KEY(lundi) REFERENCES seance(id),"+
            "  FOREIGN KEY(mardi) REFERENCES seance(id),"+
            "  FOREIGN KEY(mercredi) REFERENCES seance(id),"+
            "  FOREIGN KEY(jeudi) REFERENCES seance(id),"+
            "  FOREIGN KEY(vendredi) REFERENCES seance(id),"+
            "  FOREIGN KEY(samedi) REFERENCES seance(id),"+
            "  FOREIGN KEY(dimanche) REFERENCES seance(id)"+
            ");";
    static final String CREATE_TABLE_SELECTION_QUERY = "CREATE TABLE selection (" +
            "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "  nom VARCHAR(25)," +
            "  ts TIMESTAMP," +
            "  FOREIGN KEY(nom) REFERENCES planning(nom)" +
            ");";
    static final String CREATE_TABLE_SEANCE_QUERY = "CREATE TABLE seance (" +
            "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "  nom VARCHAR(25)" +
            ");";
    static final String CREATE_TABLE_COMPOSITION_QUERY = "CREATE TABLE composition (" +
            "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "  id_seance INTEGER," +
            "  nom_exercice VARCHAR(25)," +
            "  nombre_series INTEGER," +
            "  nombre_repetitions INTEGER," +
            "  FOREIGN KEY(id_seance) REFERENCES seance(id)" +
            ");";
    static final String DROP_TABLE_PLANNING_QUERY = "DROP TABLE IF EXISTS planning;";
    static final String DROP_TABLE_SELECTION_QUERY = "DROP TABLE IF EXISTS selection;";
    static final String DROP_TABLE_SEANCE_QUERY = "DROP TABLE IF EXISTS seance;";
    static final String DROP_TABLE_COMPOSITION_QUERY = "DROP TABLE IF EXISTS composition;";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SEANCE_QUERY);
        db.execSQL(CREATE_TABLE_PLANNING_QUERY);
        db.execSQL(CREATE_TABLE_SELECTION_QUERY);
        db.execSQL(CREATE_TABLE_COMPOSITION_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE_COMPOSITION_QUERY);
        db.execSQL(DROP_TABLE_SELECTION_QUERY);
        db.execSQL(DROP_TABLE_PLANNING_QUERY);
        db.execSQL(DROP_TABLE_SEANCE_QUERY);
    }
}
