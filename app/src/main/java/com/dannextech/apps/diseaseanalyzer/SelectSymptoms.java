package com.dannextech.apps.diseaseanalyzer;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SelectSymptoms extends AppCompatActivity {

    private static final String TAG = "DANNEX DANIELS";

    private EditText patientName;
    private ListView lvSymptoms;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_symptoms);

        lvSymptoms = findViewById(R.id.lvSymptoms);
        patientName = findViewById(R.id.etPatient);
        btnSubmit = findViewById(R.id.btSubmitSymptoms);

        final DiseaseAnalyzerQueries queries = new DiseaseAnalyzerQueries(this);


        final String [] symptoms = queries.getAllSymptoms();

        lvSymptoms.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvSymptoms.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,symptoms));
        lvSymptoms.setItemsCanFocus(false);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int len = lvSymptoms.getCount();
                String diseaseId[] = new String[len],diseaseNames[] = new String[100],allIds[] = new String[100];
                SparseBooleanArray checked = lvSymptoms.getCheckedItemPositions();
                int j = 0;
                for (int i = 0; i < len; i++){
                    if (checked.get(i)){
                        diseaseId = queries.sortDiseases(symptoms[i]);
                        for (int m = 0; m <diseaseId.length;m++){
                            allIds[j] = diseaseId[m];
                            j++;
                        }
                    }
                }

                for (int i = 0; i < j; i++){
                    diseaseNames[i] = queries.getDiseaseName(allIds[i]);
                    Log.e(TAG, "onClick: "+diseaseNames[i]);
                }


                Set<String> dis = new HashSet<>();
                for (int i = 0; i < diseaseNames.length; i++){
                    if (diseaseNames[i]!=null)
                        dis.add(diseaseNames[i]);
                }
                Log.e(TAG, "onClick: diseases"+dis );

                String diseases[] = new String[dis.size()];

                Log.e(TAG, "onClick: Size" + dis.size() );

                AlertDialog.Builder builder = new AlertDialog.Builder(SelectSymptoms.this);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SelectSymptoms.this,android.R.layout.simple_list_item_1);
                builder.setTitle("Possible Diseases");

                Iterator itr = dis.iterator();
                int b = 0;
                while (itr.hasNext()){
                    arrayAdapter.add(itr.next().toString());
                }

                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.setPositiveButton("Get Final Results", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
            }
        });


    }
}
