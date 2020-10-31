package com.codies.ruqyahcounselling.PatientRegistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codies.ruqyahcounselling.Models.Patient;
import com.codies.ruqyahcounselling.R;

public class PlanForSuicideActivity extends AppCompatActivity {


    Button never;
    Button month;
    Button threemonth;
    Button year;
    Button thisweek;
    Button oneweek;
    Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_for_suicide);
        Intent intent = getIntent();
        patient = (Patient) intent.getSerializableExtra("patient");

        never = findViewById(R.id.neverSuicide);
        month = findViewById(R.id.monthAgoBTSuicide);
        threemonth = findViewById(R.id.threemonthsagoBTSuicide);
        year = findViewById(R.id.yearAgoSuicide);
        thisweek = findViewById(R.id.thisWeekBTSuicide);
        oneweek = findViewById(R.id.weekAgoBTSuicide);

        never.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setPlanForSuicide("Never");
                Intent intent = new Intent(PlanForSuicideActivity.this, ExperiencingAnxietyActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });

        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setPlanForSuicide("Month Ago");
                Intent intent = new Intent(PlanForSuicideActivity.this, ExperiencingAnxietyActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });

        threemonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setPlanForSuicide("Three Months Ago");
                Intent intent = new Intent(PlanForSuicideActivity.this, ExperiencingAnxietyActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });
        thisweek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setPlanForSuicide("This Week");
                Intent intent = new Intent(PlanForSuicideActivity.this, ExperiencingAnxietyActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });
        oneweek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setPlanForSuicide("One Week Ago");
                Intent intent = new Intent(PlanForSuicideActivity.this, ExperiencingAnxietyActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });
        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.setPlanForSuicide("One Year Ago");
                Intent intent = new Intent(PlanForSuicideActivity.this, ExperiencingAnxietyActivity.class);
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });

    }
}
