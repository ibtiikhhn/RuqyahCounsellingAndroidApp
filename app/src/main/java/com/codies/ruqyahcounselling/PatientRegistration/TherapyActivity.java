package com.codies.ruqyahcounselling.PatientRegistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codies.ruqyahcounselling.Models.Patient;
import com.codies.ruqyahcounselling.R;

public class TherapyActivity extends AppCompatActivity {

    Patient patient;
    Button yesBT;
    Button noBT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapy);

        Intent intent = getIntent();
        patient = (Patient) intent.getSerializableExtra("patient");

        yesBT = findViewById(R.id.yesTherapy);
        noBT = findViewById(R.id.noTherapy);

        yesBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setBeenToTherapyBefore(true);
                Intent intent = new Intent(TherapyActivity.this, ExperiencingSadnessActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });

        noBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setBeenToTherapyBefore(false);
                Intent intent = new Intent(TherapyActivity.this, ExperiencingSadnessActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });
    }
}
