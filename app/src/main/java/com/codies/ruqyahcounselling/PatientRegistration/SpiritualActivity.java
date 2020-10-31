package com.codies.ruqyahcounselling.PatientRegistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codies.ruqyahcounselling.Models.Patient;
import com.codies.ruqyahcounselling.R;

public class SpiritualActivity extends AppCompatActivity {

    Patient patient;
    Button yesBT;
    Button noBT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiritual);

        Intent intent = getIntent();
        patient = (Patient) intent.getSerializableExtra("patient");

        yesBT = findViewById(R.id.yesSpiritual);
        noBT = findViewById(R.id.noSpiritual);

        yesBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setSpiritual(true);
                Intent intent = new Intent(SpiritualActivity.this, TherapyActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });

        noBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setSpiritual(false);
                Intent intent = new Intent(SpiritualActivity.this, TherapyActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });

    }
}
