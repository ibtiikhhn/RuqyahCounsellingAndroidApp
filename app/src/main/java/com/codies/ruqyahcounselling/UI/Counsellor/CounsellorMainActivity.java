package com.codies.ruqyahcounselling.UI.Counsellor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.codies.ruqyahcounselling.R;
import com.codies.ruqyahcounselling.UI.Patient.PatientAppointmentsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CounsellorMainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    CounsellorPatientsFragment counsellorPatientsFragment;
    CounsellerChatsFragment counsellerChatsFragment;
    CounsellorAppointmentsFragment counsellorAppointmentsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counsellor_home);

        bottomNav = findViewById(R.id.bottomNavigationViewAdmin);
        counsellorPatientsFragment = CounsellorPatientsFragment.newInstance();
        counsellerChatsFragment = CounsellerChatsFragment.newInstance();
        counsellorAppointmentsFragment = CounsellorAppointmentsFragment.getInstance();
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerAdmin, counsellorPatientsFragment).commit();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {

                        case R.id.patients:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerAdmin, counsellorPatientsFragment).commit();
                            break;
                        case R.id.chat_counsellor:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerAdmin, counsellerChatsFragment).commit();
                            break;
                        case R.id.appointments_counsellor:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerAdmin, counsellorAppointmentsFragment).commit();
                            break;
                        default:
                            break;
                    }

                    return true;
                }
            };
}
