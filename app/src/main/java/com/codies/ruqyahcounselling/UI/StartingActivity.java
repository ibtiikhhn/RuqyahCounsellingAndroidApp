package com.codies.ruqyahcounselling.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codies.ruqyahcounselling.PatientRegistration.GenderActivity;
import com.codies.ruqyahcounselling.R;
import com.codies.ruqyahcounselling.UI.Counsellor.CounsellorMainActivity;
import com.codies.ruqyahcounselling.UI.Patient.PatientMainActivity;
import com.codies.ruqyahcounselling.Utils.SharedPrefs;

public class StartingActivity extends AppCompatActivity {

    Button loginBT;
    Button signUpBT;
    SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        sharedPrefs = new SharedPrefs(getApplicationContext());
        loginBT = findViewById(R.id.loginBTST);
        signUpBT = findViewById(R.id.signUPBTST);

        if (sharedPrefs.isLoggedInAsUser()) {
            startActivity(new Intent(StartingActivity.this, PatientMainActivity.class));
            finish();
            return;
        }
        if (sharedPrefs.isLoggedInAsAdmin()) {
            startActivity(new Intent(StartingActivity.this, CounsellorMainActivity.class));
            finish();
            return;
        }


        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartingActivity.this, LoginActivity.class));
            }
        });

        signUpBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartingActivity.this, GenderActivity.class));
            }
        });
    }
}
