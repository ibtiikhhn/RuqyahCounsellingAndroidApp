package com.codies.ruqyahcounselling.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codies.ruqyahcounselling.Models.Patient;
import com.codies.ruqyahcounselling.R;
import com.codies.ruqyahcounselling.UI.Patient.PatientMainActivity;
import com.codies.ruqyahcounselling.Utils.SharedPrefs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.File;

public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "SIGNUPACTIVITY";

    CircularImageView profileImg;
    FloatingActionButton addProfilePhoto;
    EditText emailET;
    EditText nameEt;
    EditText phoneEt;
    EditText passwordEt;
    Button signUpBT;
    TextView loginTV;

    String name;
    String email;
    String phone;
    String password;
    String imageUrl;
    public static final int IMAGECHOOSERCODE = 1;
    Uri imageUri;
    String fileExtension;

    Patient patient;
    TextView privacyPolicy;

    String userId;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    StorageReference storageReference;
    ProgressDialog progressDialog;

    SharedPrefs sharedPrefs;

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();
        sharedPrefs =  SharedPrefs.getInstance(getApplicationContext());
        Intent intent = getIntent();
        patient = (Patient) intent.getSerializableExtra("patient");


        progressDialog = new ProgressDialog(SignUpActivity.this);
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
//        userId = auth.getCurrentUser().getUid();
        storageReference = FirebaseStorage.getInstance().getReference("profileImages");

        addProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStoragePermissionGranted()) {
                    openImageChooser();
                } else {
                    isStoragePermissionGranted();
                }

            }
        });

        signUpBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEt.getText().toString();
                email = emailET.getText().toString();
                phone = phoneEt.getText().toString();
                password = passwordEt.getText().toString();

                if (TextUtils.isEmpty(email) || !email.matches(emailPattern)) {

                    Toast.makeText(getApplicationContext(), "Enter Valid Email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter Name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(getApplicationContext(), "Enter Phone!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter Password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }


                progressDialog.setMessage("Creating Account..");
                progressDialog.show();

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    progressDialog.hide();
                                    Toast.makeText(SignUpActivity.this, "Authentication failed : " + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                    Log.i(TAG, "onComplete: "+task.getException());

                                } else {

                                    userId = task.getResult().getUser().getUid();
                                    patient.setUserId(userId);
                                    patient.setName(name);
                                    patient.setImageUrl(imageUrl);
                                    patient.setEmail(email);
                                    patient.setPhoneNumber(phone);
                                    addToFirebase(patient);

                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "onFailure: " + e.getLocalizedMessage());
                    }
                });

            }
        });

        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });

        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://ruqyah-counselling.flycricket.io/privacy.html";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

    }


    public void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGECHOOSERCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGECHOOSERCODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            Glide.with(this).load(imageUri).into(profileImg);
            fileExtension = getFileExtension(imageUri);
            uploadFile(imageUri,fileExtension);
        }
    }

    public void uploadFile(Uri imageUri, String extension) {

        final StorageReference storageReference = this.storageReference.child(System.currentTimeMillis() + "." + extension);
        storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        imageUrl = uri.toString();
                        Toast.makeText(SignUpActivity.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "onFailure: " + e.getLocalizedMessage());
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //tell user that uploading failed
                Toast.makeText(SignUpActivity.this, "Failed To Upload : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                //keep the user updated about this task
                double val = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    imgProgressBar.setProgress((int) val, true);
                } else {
//                    imgProgressBar.setProgress((int) val);
                }
            }
        });
    }


    public String getFileExtension(Uri uri) {
        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            //If scheme is a content
            final MimeTypeMap mime = MimeTypeMap.getSingleton();
            return mime.getExtensionFromMimeType(getContentResolver().getType(uri));
        } else {
            //If scheme is a File
            return MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(uri.getPath())).toString());
        }
    }

    public void initViews(){
        profileImg = findViewById(R.id.profileImgSignup);
        addProfilePhoto = findViewById(R.id.addprofilePhotoSignup);
        emailET = findViewById(R.id.emailETSignup);
        nameEt = findViewById(R.id.nameEtSignup);
        phoneEt = findViewById(R.id.phoneNumberEtSignUp);
        passwordEt = findViewById(R.id.passwordEtSignup);
        signUpBT = findViewById(R.id.signUpButtonSignup);
        loginTV = findViewById(R.id.haveAccountTVSignUp);
        privacyPolicy = findViewById(R.id.privacyPolicy);
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                openImageChooser();
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            openImageChooser();
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
            openImageChooser();
        }
    }

    public void addToFirebase(Patient patient) {

        databaseReference.child("patients").child(userId).setValue(patient).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
//                Log.i(TAG, "onSuccess: " + aVoid.toString());
//                sharedPrefs.loginAsUser(true);
                progressDialog.hide();
                progressDialog = null;

                sharedPrefs.loginAsUser(true, userId);
                startActivity(new Intent(SignUpActivity.this, PatientMainActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i(TAG, "onFailure: " + e.getLocalizedMessage());
                Toast.makeText(SignUpActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
