package com.codies.ruqyahcounselling.UI.Counsellor;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codies.ruqyahcounselling.Models.Patient;
import com.codies.ruqyahcounselling.R;
import com.codies.ruqyahcounselling.UI.LoginActivity;
import com.codies.ruqyahcounselling.UI.Patient.PatientAppointmentsFragment;
import com.codies.ruqyahcounselling.Utils.OnItemClickListener;
import com.codies.ruqyahcounselling.Utils.PatientsAdapter;
import com.codies.ruqyahcounselling.Utils.SharedPrefs;
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
public class CounsellorPatientsFragment extends Fragment implements OnItemClickListener {

    static CounsellorPatientsFragment patientsFragment;

    RecyclerView recyclerView;
    PatientsAdapter adapter;
    SharedPrefs sharedPrefs;
    DatabaseReference reference;
    FirebaseDatabase database;
    List<Patient> patients;
    Toolbar toolbar;


    public static CounsellorPatientsFragment newInstance() {
        if (patientsFragment == null) {
            patientsFragment = new CounsellorPatientsFragment();
        }
        return patientsFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_counsellor_patients, container, false);
        recyclerView = view.findViewById(R.id.patientsRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        patients = new ArrayList<>();
        adapter = new PatientsAdapter(getContext(), this);
        recyclerView.setAdapter(adapter);
        sharedPrefs = SharedPrefs.getInstance(getContext());
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        toolbar = view.findViewById(R.id.toolbar2);

        toolbar.inflateMenu(R.menu.admin_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.logoutAdmin:
                        logout();
                        break;
                }
                return false;
            }

        });


        getPatients();


        return view;
    }

    @Override
    public void onClick(String itemId) {
        Toast.makeText(getContext(), itemId, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), PatientDetailActivity.class);
        intent.putExtra("id", itemId);
        startActivity(intent);
    }

    public void getPatients() {
        reference.child("patients").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Patient patient = dataSnapshot1.getValue(Patient.class);
                    patients.add(patient);
                }
                adapter.setList(patients);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void logout() {
        sharedPrefs.clearPrefrences();
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }
}
