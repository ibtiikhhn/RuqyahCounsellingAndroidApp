package com.codies.ruqyahcounselling.PatientRegistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codies.ruqyahcounselling.Models.Patient;
import com.codies.ruqyahcounselling.R;

public class ReligionActivity extends AppCompatActivity {


    Patient patient;

    Button islam;
    Button hindu;
    Button other;
    Button chirsitan;
    Button judaism;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_religion);

        Intent intent = getIntent();
        patient = (Patient) intent.getSerializableExtra("patient");

        islam = findViewById(R.id.islamBT);
        hindu = findViewById(R.id.hinduismBt);
        other = findViewById(R.id.otherBtRelegion);
        chirsitan = findViewById(R.id.christianityBT);
        judaism = findViewById(R.id.judaismBt);

        islam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               patient.setRelegion("Islam");
                Intent intent = new Intent(ReligionActivity.this, SpiritualActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setRelegion("Other");
                Intent intent = new Intent(ReligionActivity.this, SpiritualActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });
        chirsitan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setRelegion("Christianity");
                Intent intent = new Intent(ReligionActivity.this, SpiritualActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });

        hindu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setRelegion("Hinduism");
                Intent intent = new Intent(ReligionActivity.this, SpiritualActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });
        judaism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setRelegion("Judaism");
                Intent intent = new Intent(ReligionActivity.this, SpiritualActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });
    }

}
