package com.dannextech.apps.diseaseanalyzer;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class AddDiseaseSymptom extends AppCompatActivity {

    Spinner spDisease, spSymptom;
    RadioGroup rgDetermine;
    RadioButton rbTrue,rbFalse;
    Button btnSubmitDisSys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_disease_symptom);

        spDisease = findViewById(R.id.spDisease);
        spSymptom = findViewById(R.id.spSymptom);
        rgDetermine = findViewById(R.id.rgDetermine);
        rbTrue = findViewById(R.id.rbTrue);
        rbFalse = findViewById(R.id.rbFalse);
        btnSubmitDisSys = findViewById(R.id.btnSubmitDetermine);

        final DiseaseAnalyzerQueries queries = new DiseaseAnalyzerQueries(this);

        ArrayAdapter<String> diseaseAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,queries.getAllDiseases());
        diseaseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> symptomAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,queries.getAllSymptoms());
        symptomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spSymptom.setAdapter(symptomAdapter);
        spDisease.setAdapter(diseaseAdapter);


        btnSubmitDisSys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String disease = spDisease.getSelectedItem().toString(),symptom = spSymptom.getSelectedItem().toString(),determine;
                if (rgDetermine.getCheckedRadioButtonId()==rbFalse.getId()){
                    determine = rbFalse.getText().toString();
                }else {
                    determine = rbTrue.getText().toString();
                }

                if (queries.addDetermine(disease,symptom,determine)){
                    Snackbar.make(v,"Saved Successfully",Snackbar.LENGTH_SHORT).show();
                }else {
                    Snackbar.make(v,"Something went wrong",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}
