package com.example.aqw.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "GYMTRACKER.DB";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_PLANNING = "planning";
    static final String TABLE_SEANCE = "seance";
    static final String TABLE_SELECTION = "selection";
    static final String TABLE_COMPOSITION = "composition";
    static final String PLANNING_NOM = "nom";
    static final String PLANNING_LUNDI = "lundi";
    static final String PLANNING_MARDI = "mardi";
    static final String PLANNING_MERCREDI = "mercredi";
    static final String PLANNING_JEUDI = "jeudi";
    static final String PLANNING_VENDREDI = "vendredi";
    static final String PLANNING_SAMEDI = "samedi";
    static final String PLANNING_DIMANCHE = "dimanche";
    static final String SEANCE_ID = "id";
    static final String SEANCE_NOM = "nom";
    static final String SELECTION_ID = "id";
    static final String SELECTION_NOM = "nom";
    static final String SELECTION_TIMESTAMPS = "ts";
    static final String COMPOSITION_ID = "id";
    static final String COMPOSITION_ID_SEANCE = "id_seance";
    static final String COMPOSITION_NOM_EXERCICE = "nom_exercice";
    static final String COMPOSITION_NOMBRE_SERIES = "nombre_series";
    static final String COMPOSITION_NOMBRE_REPETITIONS = "nombre_repetitions";
    static final String COMPOSITION_NOTES = "notes";

    static final String CREATE_TABLE_PLANNING_QUERY = "CREATE TABLE planning (" +
            "  nom VARCHAR(25) PRIMARY KEY," +
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
            "  notes TEXT," +
            "  FOREIGN KEY(id_seance) REFERENCES seance(id) ON DELETE CASCADE" +
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

    @Override
    public void onOpen(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys = ON;");
    }
}
