package com.codies.ruqyahcounselling.UI.Patient;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.LocaleList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codies.ruqyahcounselling.Agora.VideoCallScreenUser;
import com.codies.ruqyahcounselling.Models.Appointment;
import com.codies.ruqyahcounselling.R;
import com.codies.ruqyahcounselling.Utils.OnItemClickListener;
import com.codies.ruqyahcounselling.Utils.PatientAppointmentsAdapter;
import com.codies.ruqyahcounselling.Utils.SharedPrefs;
import com.google.android.gms.tasks.OnSuccessListener;
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
public class PatientAppointmentsFragment extends Fragment implements OnItemClickListener {

    static PatientAppointmentsFragment patientAppointmentsFragment;
    RecyclerView recyclerView;
    PatientAppointmentsAdapter appointmentsAdapter;
    FloatingActionButton addAppointmetn;
    List<Appointment> appointments;
    FirebaseDatabase database;
    SharedPrefs sharedPrefs;
    DatabaseReference reference;

    public static PatientAppointmentsFragment newInstance() {
        if (patientAppointmentsFragment == null) {
            patientAppointmentsFragment = new PatientAppointmentsFragment();
        }
        return patientAppointmentsFragment;
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
        View view = inflater.inflate(R.layout.fragment_patient_appointments, container, false);

        addAppointmetn = view.findViewById(R.id.appointmentBT);
        recyclerView = view.findViewById(R.id.appointmentsRVPatient);
        appointmentsAdapter = new PatientAppointmentsAdapter(getContext(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(appointmentsAdapter);

        addAppointmetn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PatientMakeAppointment.class));
            }
        });
        getData();
        return view;
    }

    @Override
    public void onClick(String itemId) {
        startActivity(new Intent(getContext(), VideoCallScreenUser.class));
    }

    void getData() {
        reference.child("UserAppointments").child(sharedPrefs.getUserId()).addValueEventListener(new ValueEventListener() {
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

            }
        });
    }

}
