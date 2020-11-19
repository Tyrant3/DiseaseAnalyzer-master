package com.dannextech.apps.diseaseanalyzer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class DiseaseAnalyzerQueries {
    private Context context;
    private DiseaseAnalyzerHelper helper;
    private SQLiteDatabase db;

    public DiseaseAnalyzerQueries(Context context) {
        this.context = context;
        helper = new DiseaseAnalyzerHelper(context);
    }

    public boolean addDisease(String name, String id, String description){
        db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DiseaseAnalyzerContract.Disease.COL_DISEASE_ID,id);
        values.put(DiseaseAnalyzerContract.Disease.COL_DISEASE_NAME,name);
        values.put(DiseaseAnalyzerContract.Disease.COL_DESCRIPTION,description);

        long result = db.insert(DiseaseAnalyzerContract.Disease.TABLE_NAME,null,values);
        return result != -1;
    }
    public boolean addSymptom(String name, String id, String description){
        db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DiseaseAnalyzerContract.Symptoms.COL_SYMPTOM_ID,id);
        values.put(DiseaseAnalyzerContract.Symptoms.COL_SYMPTOM_NAME,name);
        values.put(DiseaseAnalyzerContract.Symptoms.COL_DESCRIPTION,description);

        long result = db.insert(DiseaseAnalyzerContract.Symptoms.TABLE_NAME,null,values);
        return result != -1;
    }

    public boolean addDetermine(String disease, String symptom, String determine){
        db = helper.getWritableDatabase();

        String diseaseId = getDiseaseId(disease), symptomId = getSymptomId(symptom);

        Log.e("DDMMGG", "addDetermine: "+diseaseId + " "+symptomId );

        ContentValues values = new ContentValues();
        if (diseaseId != null){
            if (symptomId != null){
                values.put(DiseaseAnalyzerContract.DiseaseDeterministicSymptom.COL_SYMPTOM_ID,symptomId);
                values.put(DiseaseAnalyzerContract.DiseaseDeterministicSymptom.COL_DISEASE_ID,diseaseId);
                values.put(DiseaseAnalyzerContract.DiseaseDeterministicSymptom.COL_DETERMINISTIC_SYMPTOM,determine);

                long result = db.insert(DiseaseAnalyzerContract.DiseaseDeterministicSymptom.TABLE_NAME,null,values);
                return result != -1;
            }else {
                Toast.makeText(context,"The Symptom doesn't exist. Make sure it is added first",Toast.LENGTH_SHORT).show();
                return false;
            }
        }else {
            Toast.makeText(context,"The Disease doesn't exist. Make sure it is added first",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public String[] getAllSymptoms(){
        db = helper.getReadableDatabase();


        String columns[] = {DiseaseAnalyzerContract.Symptoms.COL_SYMPTOM_NAME};
        Cursor cursor = db.query(DiseaseAnalyzerContract.Symptoms.TABLE_NAME,columns,null,null,null,null,null);

        String symptoms[] = new String[cursor.getCount()];
        int i = 0;
        while (cursor.moveToNext()){
            symptoms[i] = cursor.getString(cursor.getColumnIndexOrThrow(DiseaseAnalyzerContract.Symptoms.COL_SYMPTOM_NAME));
            i++;
        }

        cursor.close();

        return symptoms;
    }

    public String[] getAllDiseases(){
        db = helper.getReadableDatabase();


        String columns[] = {DiseaseAnalyzerContract.Disease.COL_DISEASE_NAME};
        Cursor cursor = db.query(DiseaseAnalyzerContract.Disease.TABLE_NAME,columns,null,null,null,null,null);

        String disease[] = new String[cursor.getCount()];
        int i = 0;
        while (cursor.moveToNext()){
            disease[i] = cursor.getString(cursor.getColumnIndexOrThrow(DiseaseAnalyzerContract.Disease.COL_DISEASE_NAME));
            i++;
        }

        cursor.close();

        return disease;
    }

    private String getDiseaseId(String disease){
        db = helper.getReadableDatabase();

        String id = null;

        String columns[] = {DiseaseAnalyzerContract.Disease.COL_DISEASE_ID};
        String selection = DiseaseAnalyzerContract.Disease.COL_DISEASE_NAME + " = ? ";
        String selectionArgs[] = {disease};

        Cursor cursor = db.query(DiseaseAnalyzerContract.Disease.TABLE_NAME,columns,selection,selectionArgs,null,null,null);

        while (cursor.moveToNext()){
            id = cursor.getString(cursor.getColumnIndexOrThrow(DiseaseAnalyzerContract.Disease.COL_DISEASE_ID));
        }

        cursor.close();

        return id;
    }

    public String getDiseaseName(String disease){
        db = helper.getReadableDatabase();

        String columns[] = {DiseaseAnalyzerContract.Disease.COL_DISEASE_NAME};
        String selection = DiseaseAnalyzerContract.Disease.COL_DISEASE_ID + " = ? ";
        String selectionArgs[] = {disease};

        Cursor cursor = db.query(DiseaseAnalyzerContract.Disease.TABLE_NAME,columns,selection,selectionArgs,null,null,null);

        String names = null;
        while (cursor.moveToNext()){
            names = cursor.getString(cursor.getColumnIndexOrThrow(DiseaseAnalyzerContract.Disease.COL_DISEASE_NAME));
        }

        cursor.close();

        return names;
    }

    private String getSymptomId(String symptom){
        db = helper.getReadableDatabase();

        String id = null;

        String columns[] = {DiseaseAnalyzerContract.Symptoms.COL_SYMPTOM_ID};
        String selection = DiseaseAnalyzerContract.Symptoms.COL_SYMPTOM_NAME + " = ? ";
        String selectionArgs[] = {symptom};

        Cursor cursor = db.query(DiseaseAnalyzerContract.Symptoms.TABLE_NAME,columns,selection,selectionArgs,null,null,null);

        while (cursor.moveToNext()){
            id = cursor.getString(cursor.getColumnIndexOrThrow(DiseaseAnalyzerContract.Symptoms.COL_SYMPTOM_ID));
        }

        cursor.close();

        return id;
    }

    public String[] sortDiseases(String symptom){
        db = helper.getReadableDatabase();

        String symptomId = getSymptomId(symptom);



        String columns[] = {DiseaseAnalyzerContract.DiseaseDeterministicSymptom.COL_DISEASE_ID};
        String selection = DiseaseAnalyzerContract.DiseaseDeterministicSymptom.COL_SYMPTOM_ID + " = ? ";
        String selectionArgs[] = {symptomId};

        Cursor cursor = db.query(DiseaseAnalyzerContract.DiseaseDeterministicSymptom.TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        String diseases[] = new String[cursor.getCount()];
        int i = 0;
        while (cursor.moveToNext()){
            diseases[i] = cursor.getString(cursor.getColumnIndexOrThrow(DiseaseAnalyzerContract.DiseaseDeterministicSymptom.COL_DISEASE_ID));
            Log.e("DDMMGG", "sortDiseases: "+diseases[i]);
            i++;
        }
        cursor.close();
        return diseases;
    }
}
