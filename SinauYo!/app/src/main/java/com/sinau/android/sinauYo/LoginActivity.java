package com.sinau.android.sinauYo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.net.Uri;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sinau.android.sinauYo.model.User;


public class LoginActivity extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button btnLogin, btnDaftar;
    FirebaseAuth mAuth;

    private static final String TAG = "EmailPassword";

    private DatabaseReference mDatabase;

    //method berikut akan dijalankan saat aktifitas dimulai
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        btnDaftar = findViewById(R.id.btnDaftar);

//        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    //Persyaratan login
    public void login(View view) {
        signIn(mEmail.getText().toString(),mPassword.getText().toString());

    }

    private void signIn(String email,String password) {
        Log.d(TAG,"signIn :" + email);

        if (!validateForm()){
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:" +task.isSuccessful());
                        if (task.isSuccessful()) {


//                            FirebaseUser user = mAuth.getCurrentUser();
//
//                            Intent a = new Intent(MainActivity.this,Home.class);
//                            startActivity(a);

                            onAuthSuccess(task.getResult().getUser());

//                            updateUI(user);
                        } else {

                            // If sign in fails, display a message to the user.

                            Log.w(TAG, "signInWithEmail:failure", task.getException());

                            Toast.makeText(LoginActivity.this, "Authentication failed.",

                                    Toast.LENGTH_SHORT).show();

//                            updateUI(null);
                        }
                    }
                });

    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmail.getText().toString();
        if (TextUtils.isEmpty(email)){
            mEmail.setError("Required");
            valid = false;
        }
        else {
            mEmail.setError(null);
        }

        String password = mPassword.getText().toString();
        if (TextUtils.isEmpty(password)){
            mPassword.setError("Required");
            valid = false;
        } else{
            mPassword.setError(null);
        }
        return valid;
    }


    private void onAuthSuccess(FirebaseUser user) {
        String username = usernameFromEmail(user.getEmail());

        writeNewUser(user.getUid(),username,user.getEmail());

        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
        finish();
    }

    private void writeNewUser(String uid, String username, String email) {
        User userModul6 = new User(username,email);

        mDatabase.child("users").child(uid).setValue(userModul6);         //membuat akun users ke database
    }


    private String usernameFromEmail(String email) {

        if (email.contains("@")){ //memberikan pemisahan berdasarkan tanda at / @
            return email.split("@")[0];
        } else {
            return email;
        }
    }


    public void goDaftar(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void onLupa(View view) {
        Toast.makeText(this, "Fitur Lupa password belum Tersedia.", Toast.LENGTH_SHORT).show();
    }

    public void goInstagram(View view) {
        String instagram = "https://www.instagram.com/sinauyo/";

        // Parse the URI and create the intent.
        Uri webpage = Uri.parse(instagram);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        // Find an activity to hand the intent and start that activity.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void goFacebook(View view) {
        String facebook = "https://www.facebook.com/SinauYoOfficial/";

        // Parse the URI and create the intent.
        Uri webpage = Uri.parse(facebook);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        // Find an activity to hand the intent and start that activity.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }
}
