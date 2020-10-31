package com.codies.ruqyahcounselling.PatientRegistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.codies.ruqyahcounselling.Models.Patient;
import com.codies.ruqyahcounselling.R;
import com.codies.ruqyahcounselling.UI.SignUpActivity;
import com.hbb20.CountryCodePicker;

public class CountryActivity extends AppCompatActivity {

    CountryCodePicker countryCodePicker;
    Patient patient;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        button = findViewById(R.id.nextBt);

        Intent intent = getIntent();
        patient = (Patient) intent.getSerializableExtra("patient");
        countryCodePicker = findViewById(R.id.ccp);
        final String countryName = countryCodePicker.getSelectedCountryName();
        patient.setCountry(countryName);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(CountryActivity.this, SignUpActivity.class);
                myIntent.putExtra("patient", patient);
                startActivity(myIntent);

            }
        });

    }
}
