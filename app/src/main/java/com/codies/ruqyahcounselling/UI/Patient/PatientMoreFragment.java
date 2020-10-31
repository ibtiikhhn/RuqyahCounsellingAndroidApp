package com.codies.ruqyahcounselling.UI.Patient;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.codies.ruqyahcounselling.R;
import com.codies.ruqyahcounselling.UI.LoginActivity;
import com.codies.ruqyahcounselling.Utils.SharedPrefs;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatientMoreFragment extends Fragment {


    Button logout;
    static PatientMoreFragment moreFragment;
    public static PatientMoreFragment newInstance() {
        if (moreFragment == null) {
            moreFragment = new PatientMoreFragment();
        }
        return moreFragment;
    }

    SharedPrefs sharedPrefs;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_patient_more, container, false);
        logout = view.findViewById(R.id.logoutBT);
        sharedPrefs = SharedPrefs.getInstance(getContext());
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefs.clearPrefrences();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
        return view;
    }

}
