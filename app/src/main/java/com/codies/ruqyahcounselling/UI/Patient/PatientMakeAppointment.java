package com.codies.ruqyahcounselling.UI.Patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.codies.ruqyahcounselling.Models.Appointment;
import com.codies.ruqyahcounselling.R;
import com.codies.ruqyahcounselling.UI.PaymentActivity;
import com.codies.ruqyahcounselling.Utils.SharedPrefs;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.jetbrains.annotations.NotNull;

public class PatientMakeAppointment extends AppCompatActivity {

    public static final String TAG = "HAHA";
    MaterialCalendarView calendarView;
    Appointment appointment;
    SharedPrefs sharedPrefs;
    String userId;
    Button fixAppointment;
    String time;
    String mdate;
    FirebaseDatabase database;
    DatabaseReference reference;
    Button tempButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_make_appointment);
        calendarView = findViewById(R.id.calendarView);
        fixAppointment = findViewById(R.id.fixAppointment);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        sharedPrefs = SharedPrefs.getInstance(getApplicationContext());
        userId = sharedPrefs.getUserId();
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Log.i(TAG, "onDateSelected: " + date.toString());
                mdate = date.toString();
                Log.i(TAG, "onDateSelected: " + date.getDay());
            }
        });



        fixAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mdate == null) {
                    Toast.makeText(PatientMakeAppointment.this, "Choose Date First", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (time == null) {
                    Toast.makeText(PatientMakeAppointment.this, "Choose Time First", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(PatientMakeAppointment.this, PaymentActivity.class);
                final String appointmentId = reference.child("Appointments").push().getKey();
                appointment = new Appointment(userId, appointmentId, mdate, time, "Pending Approval");
                intent.putExtra("appointment", appointment);
                intent.putExtra("userId", userId);
                startActivity(intent);

            }
        });

    }

    public void timeOnclick(View view) {
        Button button = (Button) view;
        if (tempButton != null) {
            reset(tempButton);
        }
        button.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        button.setTextColor(getResources().getColor(R.color.white));
        time = button.getText().toString();
        tempButton = button;
    }

    public void reset(Button button) {
        button.setBackground(getResources().getDrawable(R.drawable.round_corner_bt));
        button.setTextColor(getResources().getColor(R.color.colorAccent));
    }
}
