package com.dannextech.apps.diseaseanalyzer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DiseaseAnalyzerHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "disease_analyzer";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_DISEASE = "CREATE TABLE "+
            DiseaseAnalyzerContract.Disease.TABLE_NAME + " ( " +
            DiseaseAnalyzerContract.Disease.COL_DISEASE_ID + " VARCHAR(30) PRIMARY KEY, " +
            DiseaseAnalyzerContract.Disease.COL_DISEASE_NAME + " VARCHAR(50) UNIQUE NOT NULL, " +
            DiseaseAnalyzerContract.Disease.COL_DESCRIPTION + " TEXT NOT NULL)";
    private static final String CREATE_TABLE_SYMPTOM = "CREATE TABLE " +
            DiseaseAnalyzerContract.Symptoms.TABLE_NAME + " ( " +
            DiseaseAnalyzerContract.Symptoms.COL_SYMPTOM_ID + " VARCHAR(30) PRIMARY KEY, " +
            DiseaseAnalyzerContract.Symptoms.COL_SYMPTOM_NAME + " VARCHAR(50) UNIQUE NOT NULL, " +
            DiseaseAnalyzerContract.Symptoms.COL_DESCRIPTION + " TEXT NOT NULL)";
    private static final String CREATE_TABLE_DETERMINE = "CREATE TABLE " +
            DiseaseAnalyzerContract.DiseaseDeterministicSymptom.TABLE_NAME + " ( " +
            DiseaseAnalyzerContract.DiseaseDeterministicSymptom.COL_DISEASE_ID + " VARCHAR(30) NOT NULL, " +
            DiseaseAnalyzerContract.DiseaseDeterministicSymptom.COL_SYMPTOM_ID + " VARCHAR(30) NOT NULL, " +
            DiseaseAnalyzerContract.DiseaseDeterministicSymptom.COL_DETERMINISTIC_SYMPTOM + " VARCHAR(10) NOT NULL DEFAULT 'No', " +
            "FOREIGN KEY (" + DiseaseAnalyzerContract.DiseaseDeterministicSymptom.COL_DISEASE_ID + ") REFERENCES " +
            DiseaseAnalyzerContract.Disease.TABLE_NAME + "(" + DiseaseAnalyzerContract.Disease.COL_DISEASE_ID + "), " +
            "FOREIGN KEY (" + DiseaseAnalyzerContract.DiseaseDeterministicSymptom.COL_SYMPTOM_ID + ") REFERENCES " +
            DiseaseAnalyzerContract.Symptoms.TABLE_NAME + "(" + DiseaseAnalyzerContract.Symptoms.COL_SYMPTOM_ID + "), " +
            "PRIMARY KEY (" + DiseaseAnalyzerContract.DiseaseDeterministicSymptom.COL_DISEASE_ID + "," + DiseaseAnalyzerContract.DiseaseDeterministicSymptom.COL_SYMPTOM_ID + "))";

    private static final String DROP_TABLE_DISEASE = "DROP TABLE IF EXISTS " + DiseaseAnalyzerContract.Disease.TABLE_NAME;
    private static final String DROP_TABLE_SYMPTOMS = "DROP TABLE IF EXISTS " + DiseaseAnalyzerContract.Symptoms.TABLE_NAME;
    private static final String DROP_TABLE_DETERMINE = "DROP TABLE IF EXISTS " + DiseaseAnalyzerContract.DiseaseDeterministicSymptom.TABLE_NAME;


    public DiseaseAnalyzerHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DISEASE);
        db.execSQL(CREATE_TABLE_SYMPTOM);
        db.execSQL(CREATE_TABLE_DETERMINE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_DETERMINE);
        db.execSQL(DROP_TABLE_SYMPTOMS);
        db.execSQL(DROP_TABLE_DISEASE);

        onCreate(db);
    }
}
