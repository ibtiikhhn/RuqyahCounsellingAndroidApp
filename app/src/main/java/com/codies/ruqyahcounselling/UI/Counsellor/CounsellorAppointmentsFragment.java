package com.codies.ruqyahcounselling.UI.Counsellor;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codies.ruqyahcounselling.Models.Appointment;
import com.codies.ruqyahcounselling.R;
import com.codies.ruqyahcounselling.UI.Patient.PatientAppointmentsFragment;
import com.codies.ruqyahcounselling.UI.Patient.PatientMakeAppointment;
import com.codies.ruqyahcounselling.Utils.AppointmentClickListener;
import com.codies.ruqyahcounselling.Utils.CounsellorAppointmentsAdapter;
import com.codies.ruqyahcounselling.Utils.OnItemClickListener;
import com.codies.ruqyahcounselling.Utils.PatientAppointmentsAdapter;
import com.codies.ruqyahcounselling.Utils.SharedPrefs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CounsellorAppointmentsFragment extends Fragment implements AppointmentClickListener {


    public static final String TAG = "APPOINTMENT";
    RecyclerView recyclerView;
    CounsellorAppointmentsAdapter appointmentsAdapter;
    List<Appointment> appointments;
    FirebaseDatabase database;
    SharedPrefs sharedPrefs;
    DatabaseReference reference;

    static CounsellorAppointmentsFragment counsellorAppointmentsFragment;
    public static CounsellorAppointmentsFragment getInstance() {
        if (counsellorAppointmentsFragment == null) {
            counsellorAppointmentsFragment = new CounsellorAppointmentsFragment();
        }
        return counsellorAppointmentsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        sharedPrefs = SharedPrefs.getInstance(getContext());

        appointments = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_counsellor_appointments, container, false);

        recyclerView = view.findViewById(R.id.counsellorAppointmentsRV);
        appointmentsAdapter = new CounsellorAppointmentsAdapter(getContext(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(appointmentsAdapter);

        getData();
        return view;
    }


    void getData() {
        reference.child("Appointments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                appointments.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Appointment appointment = dataSnapshot1.getValue(Appointment.class);
                    appointments.add(appointment);
                }
                appointmentsAdapter.setList(appointments);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onAccept(final String appointmentId, final String userId) {
        reference.child("Appointments").child(appointmentId).child("status").setValue("accepted");

        reference.child("UserAppointments").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Appointment appointment = snapshot.getValue(Appointment.class);
                    Log.i(TAG, "onDataChange: " + appointment.getAppointmentId());
                    Log.i(TAG, "onDataChange: " + appointment.getUserId());
                        if (appointment.getAppointmentId().equals(appointmentId)) {
                            reference.child("UserAppointments").child(userId).child(appointmentId).child("status").setValue("accepted");
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onDelete(final String appointmentId, final String userId) {
        reference.child("Appointments").child(appointmentId).child("status").setValue("declined");
        reference.child("UserAppointments").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Appointment appointment = snapshot.getValue(Appointment.class);
                        if (appointment.getAppointmentId().equals(appointmentId)) {
                            reference.child("UserAppointments").child(userId).child(appointmentId).child("status").setValue("declined");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
