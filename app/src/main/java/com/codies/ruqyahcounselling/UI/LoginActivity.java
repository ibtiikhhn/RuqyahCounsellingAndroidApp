package com.codies.ruqyahcounselling.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codies.ruqyahcounselling.PatientRegistration.GenderActivity;
import com.codies.ruqyahcounselling.R;
import com.codies.ruqyahcounselling.UI.Counsellor.CounsellorMainActivity;
import com.codies.ruqyahcounselling.UI.Patient.PatientMainActivity;
import com.codies.ruqyahcounselling.Utils.SharedPrefs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText emailEt;
    EditText passwordEt;
    TextView forgotPasswordTV;
    TextView donthaveAccountTV;
    Button loginBT;

    private static final String TAG = "LoginActivity";

    private Context mContext;
    private EditText mEmail, mPassword;
    private FirebaseAuth auth;
    SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        mEmail = findViewById(R.id.emailEtLogin);
        mPassword = findViewById(R.id.passwordEtLogin);
        mContext = LoginActivity.this;

        sharedPrefs = SharedPrefs.getInstance(this);
        initViews();


        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                if (email.equals("admin") && password.equals("admin")) {
                    sharedPrefs.loginAsAdmin(true, null);
                    startActivity(new Intent(LoginActivity.this, CounsellorMainActivity.class));
                    finish();
                }

                if (email.isEmpty()) {
                    Toast.makeText(mContext, "Invalid Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty()) {
                    Toast.makeText(mContext, "Empty Password", Toast.LENGTH_SHORT).show();
                } else {

                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                sharedPrefs.loginAsUser(true,auth.getCurrentUser().getUid());
                                Intent intent = new Intent(LoginActivity.this, PatientMainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(mContext, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(mContext, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

        donthaveAccountTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, GenderActivity.class));
            }
        });

        forgotPasswordTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });
    }


    public void initViews() {
        emailEt = findViewById(R.id.emailEtLogin);
        passwordEt = findViewById(R.id.passwordEtLogin);
        forgotPasswordTV = findViewById(R.id.forgotPasswordTvLogin);
        donthaveAccountTV = findViewById(R.id.dontHaveAccountTVLogin);
        loginBT = findViewById(R.id.loginBtLogin);
    }
}
