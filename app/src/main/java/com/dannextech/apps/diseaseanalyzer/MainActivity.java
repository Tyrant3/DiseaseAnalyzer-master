package com.dannextech.apps.diseaseanalyzer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btDisease,btSymptom, btDisSys, btDetermine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btDisease = findViewById(R.id.btAddDisease);
        btSymptom = findViewById(R.id.btAddSymptom);
        btDisSys = findViewById(R.id.btMrgDisSys);
        btDetermine = findViewById(R.id.btDetermine);

        btDetermine.setOnClickListener(this);
        btDisSys.setOnClickListener(this);
        btSymptom.setOnClickListener(this);
        btDisease.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btDetermine:
                startActivity(new Intent(this,SelectSymptoms.class));
                break;
            case R.id.btMrgDisSys:
                startActivity(new Intent(this,AddDiseaseSymptom.class));
                break;
            case R.id.btAddSymptom:
                startActivity(new Intent(this,AddSymptom.class));
                break;
            case R.id.btAddDisease:
                startActivity(new Intent(this,AddDisease.class));
                break;
        }
    }
}
