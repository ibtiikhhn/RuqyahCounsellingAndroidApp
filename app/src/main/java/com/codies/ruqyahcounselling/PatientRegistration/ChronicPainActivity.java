package com.codies.ruqyahcounselling.PatientRegistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codies.ruqyahcounselling.Models.Patient;
import com.codies.ruqyahcounselling.R;

public class ChronicPainActivity extends AppCompatActivity {


    Patient patient;
    Button yesBT;
    Button noBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronic_pain);

        Intent intent = getIntent();
        patient = (Patient) intent.getSerializableExtra("patient");

        yesBT = findViewById(R.id.yesChronic);
        noBT = findViewById(R.id.noChronic);

        yesBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setChronicPain(true);
                Intent intent = new Intent(ChronicPainActivity.this, CountryActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });

        noBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setChronicPain(false);
                Intent intent = new Intent(ChronicPainActivity.this, CountryActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });
    }
}
