package com.codies.ruqyahcounselling.UI.Patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.codies.ruqyahcounselling.R;
import com.codies.ruqyahcounselling.UI.LoginActivity;
import com.codies.ruqyahcounselling.Utils.SharedPrefs;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class PatientMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNav;
    PatientAppointmentsFragment patientAppointmentsFragment;
    PatientChatsFragment patientChatsFragment;
    PatientMoreFragment moreFragment;
    private DrawerLayout drawer;
    SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_home);

        drawer = findViewById(R.id.drawer_layout);
        sharedPrefs = SharedPrefs.getInstance(getApplicationContext());
        bottomNav = findViewById(R.id.bottomNavigationViewUser);
        patientAppointmentsFragment = PatientAppointmentsFragment.newInstance();
        patientChatsFragment = PatientChatsFragment.newInstance();
        moreFragment = PatientMoreFragment.newInstance();
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerUser, patientChatsFragment).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {

                        case R.id.chat:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerUser, patientChatsFragment).commit();
                            break;
                        case R.id.schedule:

                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerUser, patientAppointmentsFragment).commit();
                            break;
                        case R.id.more:
//                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerUser, moreFragment).commit();
                            drawer.openDrawer(Gravity.LEFT);
                            break;
                        default:
                            break;
                    }

                    return true;
                }
            };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mailUs:
                Uri uri = Uri.parse("mailto:" + "ruqyahcounselling@gmail.com")
                        .buildUpon()
                        .appendQueryParameter("subject", "Mail From Ruqyah Counselling App")
                        .appendQueryParameter("body", "Salam..")
                        .build();

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(Intent.createChooser(emailIntent, "HELP"));
                break;

            case R.id.nav_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Get Counselling now by downloading this app now: http://play.google.com/store/apps/details?id=" + getApplication().getPackageName());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            case R.id.privacyPolicy:
                String url = "https://ruqyah-counselling.flycricket.io/privacy.html";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;

            case R.id.logout:
                sharedPrefs.clearPrefrences();
                startActivity(new Intent(PatientMainActivity.this, LoginActivity.class));
                finish();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
