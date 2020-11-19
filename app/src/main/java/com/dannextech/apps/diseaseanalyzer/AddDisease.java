package com.dannextech.apps.diseaseanalyzer;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddDisease extends AppCompatActivity {

    private Button btnSubmitDisease;
    private EditText etDisease,etDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_disease);

        final DiseaseAnalyzerQueries queries = new DiseaseAnalyzerQueries(this);

        btnSubmitDisease = findViewById(R.id.btnSubmitDisease);
        etDescription = findViewById(R.id.etDiseaseDescription);
        etDisease = findViewById(R.id.etDisease);


        btnSubmitDisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = (int) (Math.random()*1000);
                if (queries.addDisease(etDisease.getText().toString(),"DIS"+id,etDescription.getText().toString())){
                    Snackbar.make(v,"Saved Successfully",Snackbar.LENGTH_SHORT).show();
                    etDescription.setText("");
                    etDisease.setText("");
                }else {
                    Snackbar.make(v,"Something went wrong",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}
