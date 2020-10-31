package com.codies.ruqyahcounselling.PatientRegistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codies.ruqyahcounselling.Models.Patient;
import com.codies.ruqyahcounselling.R;

public class SleepingHabitActivity extends AppCompatActivity {

    Patient patient;
    Button goodBT;
    Button fairBT;
    Button poorBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleeping_habit);

        Intent intent = getIntent();
        patient = (Patient) intent.getSerializableExtra("patient");

        goodBT = findViewById(R.id.goodSleeping);
        fairBT = findViewById(R.id.fairSleeping);
        poorBT = findViewById(R.id.poorSleeping);

        goodBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setSleepingHabits("Good");
                Intent intent = new Intent(SleepingHabitActivity.this, ChronicPainActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });

        fairBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setSleepingHabits("Fair");
                Intent intent = new Intent(SleepingHabitActivity.this, ChronicPainActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });

        poorBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setSleepingHabits("Poor");
                Intent intent = new Intent(SleepingHabitActivity.this, SleepingHabitActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });
    }
}
