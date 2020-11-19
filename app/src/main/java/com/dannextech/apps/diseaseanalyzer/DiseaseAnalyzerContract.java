package com.dannextech.apps.diseaseanalyzer;

import android.provider.BaseColumns;

public class DiseaseAnalyzerContract{

    public DiseaseAnalyzerContract() {
    }

    public static class DiseaseDeterministicSymptom implements BaseColumns{
        public static final String TABLE_NAME = "determiner";

        public static final String COL_DISEASE_ID = "disease_id";
        public static final String COL_SYMPTOM_ID = "symptom_id";
        public static final String COL_DETERMINISTIC_SYMPTOM = "deterministic_symptom";
    }

    public static class Symptoms implements BaseColumns{
        public static final String TABLE_NAME = "symptom";

        public static final String COL_SYMPTOM_ID = "symptom_id";
        public static final String COL_SYMPTOM_NAME = "symptom_name";
        public static final String COL_DESCRIPTION = "description";
    }

    public static class Disease implements BaseColumns{
        public static final String TABLE_NAME = "disease";

        public static final String COL_DISEASE_ID = "disease_id";
        public static final String COL_DISEASE_NAME = "disease_name";
        public static final String COL_DESCRIPTION = "description";
    }
}
