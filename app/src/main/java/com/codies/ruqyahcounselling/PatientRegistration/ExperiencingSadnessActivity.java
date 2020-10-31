package com.codies.ruqyahcounselling.PatientRegistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codies.ruqyahcounselling.Models.Patient;
import com.codies.ruqyahcounselling.R;

public class ExperiencingSadnessActivity extends AppCompatActivity {
    Patient patient;
    Button yesBT;
    Button noBT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiencing_sadness);

        Intent intent = getIntent();
        patient = (Patient) intent.getSerializableExtra("patient");

        yesBT = findViewById(R.id.yesSadness);
        noBT = findViewById(R.id.noSadness);

        yesBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setExperiencingSadness(true);
                Intent intent = new Intent(ExperiencingSadnessActivity.this, PlanForSuicideActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });

        noBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setExperiencingSadness(false);
                Intent intent = new Intent(ExperiencingSadnessActivity.this, PlanForSuicideActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });
    }
}
