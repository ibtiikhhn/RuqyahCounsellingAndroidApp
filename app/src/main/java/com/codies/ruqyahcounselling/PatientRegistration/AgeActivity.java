package com.codies.ruqyahcounselling.PatientRegistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.codies.ruqyahcounselling.Models.Patient;
import com.codies.ruqyahcounselling.R;

public class AgeActivity extends AppCompatActivity {


    Intent intent;
    Patient patient;
    Spinner spinner;
    String itemmm;
    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);
        intent = getIntent();
        patient = (Patient) intent.getSerializableExtra("patient");
        spinner = (Spinner) findViewById(R.id.ageSpinner);
//        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(++check > 1) {
                    itemmm = parent.getItemAtPosition(position).toString();
                    patient.setAge(itemmm);
                    Intent intent = new Intent(AgeActivity.this, RelegiousActivity.class);
                    intent.putExtra("patient", patient);
                    startActivity(intent);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

   /* public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            itemmm = parent.getItemAtPosition(pos).toString();
            patient.setAge(itemmm);
            Intent intent = new Intent(AgeActivity.this, RelegiousActivity.class);
            intent.putExtra("patient", patient);
            startActivity(intent);
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

    }*/
}
