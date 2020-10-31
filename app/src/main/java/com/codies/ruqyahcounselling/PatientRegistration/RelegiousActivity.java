package com.codies.ruqyahcounselling.PatientRegistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.codies.ruqyahcounselling.Models.Patient;
import com.codies.ruqyahcounselling.R;

public class RelegiousActivity extends AppCompatActivity {


    Button yesBT;
    Button noBT;
    Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relegious);

        Intent intent = getIntent();
        patient = (Patient) intent.getSerializableExtra("patient");
        Log.i("HELL", "onCreate: "+patient.isRelegious());

        yesBT = findViewById(R.id.yesRelegious);
        noBT = findViewById(R.id.noRelegious);

        yesBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setRelegious(true);
                Intent intent = new Intent(RelegiousActivity.this, ReligionActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });

        noBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setRelegious(false);
                Intent intent = new Intent(RelegiousActivity.this, ReligionActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });

    }
}
