package com.codies.ruqyahcounselling.UI.Counsellor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codies.ruqyahcounselling.Models.Patient;
import com.codies.ruqyahcounselling.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;

public class PatientDetailActivity extends AppCompatActivity {

    TextView name;
    TextView gender;
    TextView age;
    TextView country;
    TextView email;
    TextView beentotherapy;
    TextView chronicPain;
    TextView experiencingAnxiety;
    TextView experiencingSadness;
    TextView planForSuicide;
    TextView relegion;
    TextView relegious;
    TextView sleepingHabbits;
    TextView spiritual;
    TextView takingMedication;
    CircularImageView imageView;
    String id;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);
        initViews();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();

        reference.child("patients").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Patient patient = dataSnapshot.getValue(Patient.class);
                Glide.with(getApplicationContext()).load(patient.getImageUrl()).into(imageView);
                name.setText(patient.getName());
                gender.setText(patient.getGender());
                age.setText(patient.getAge());
                country.setText(patient.getCountry());
                email.setText(patient.getEmail());
                beentotherapy.setText(String.valueOf(patient.isBeenToTherapyBefore()));
                chronicPain.setText(String.valueOf(patient.getChronicPain()));
                experiencingAnxiety.setText(String.valueOf(patient.isExperiencingAnxiety()));
                experiencingSadness.setText(String.valueOf(patient.isExperiencingSadness()));
                planForSuicide.setText(patient.getPlanForSuicide());
                relegion.setText(patient.getRelegion());
                relegious.setText(String.valueOf(patient.isRelegious()));
                sleepingHabbits.setText(patient.getSleepingHabits());
                spiritual.setText(String.valueOf(patient.isSpiritual()));
                takingMedication.setText(String.valueOf(patient.isTakingMedication()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void initViews() {
        imageView = findViewById(R.id.circularImageView2);
        name = findViewById(R.id.nameTV);
        gender = findViewById(R.id.genderTV);
        age = findViewById(R.id.ageTV);
        country = findViewById(R.id.countryTV);
        email = findViewById(R.id.emailTV);
        beentotherapy = findViewById(R.id.beenToTherapyTV);
        chronicPain = findViewById(R.id.chronicPainTV);
        experiencingAnxiety = findViewById(R.id.experiencingAnxietyTV);
        experiencingSadness = findViewById(R.id.experiencingSadnessTV);
        planForSuicide = findViewById(R.id.planForSuicideTV);
        relegion = findViewById(R.id.RelegionTV);
        relegious = findViewById(R.id.relegiousTV);
        sleepingHabbits = findViewById(R.id.sleepingHabbitsTV);
        spiritual = findViewById(R.id.SpiritualTV);
        takingMedication = findViewById(R.id.takingMedciationTV);
    }
}
