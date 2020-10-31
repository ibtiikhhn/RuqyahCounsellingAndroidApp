package com.codies.ruqyahcounselling.PatientRegistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codies.ruqyahcounselling.Models.Patient;
import com.codies.ruqyahcounselling.R;

public class GenderActivity extends AppCompatActivity {

    Button maleBt;
    Button femaleBT;
    Button otherBT;

    Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        patient = new Patient();

        maleBt = findViewById(R.id.maleBTGender);
        femaleBT = findViewById(R.id.femaleBTGender);
        otherBT = findViewById(R.id.otherBTGender);


        maleBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setGender("Male");
                Intent intent = new Intent(GenderActivity.this, AgeActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });

        femaleBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setGender("Female");
                Intent intent = new Intent(GenderActivity.this, AgeActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });

        otherBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setGender("Non-binary");
                Intent intent = new Intent(GenderActivity.this, AgeActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });
    }
}
