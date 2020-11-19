package com.dannextech.apps.diseaseanalyzer;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddSymptom extends AppCompatActivity {

    private Button btnSubmitSymptom;
    private EditText etSymptom,etDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_symptom);

        final DiseaseAnalyzerQueries queries = new DiseaseAnalyzerQueries(this);

        etDescription = findViewById(R.id.etSymptomDescription);
        etSymptom = findViewById(R.id.etSymptom);
        btnSubmitSymptom = findViewById(R.id.btnSubmitSymptom);

        btnSubmitSymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = (int) (Math.random()*1000);
                if (queries.addSymptom(etSymptom.getText().toString(),"SYM"+id,etDescription.getText().toString())){
                    Snackbar.make(v,"Saved Successfully",Snackbar.LENGTH_SHORT).show();
                    etDescription.setText("");
                    etSymptom.setText("");
                }else {
                    Snackbar.make(v,"Something went wrong",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}
