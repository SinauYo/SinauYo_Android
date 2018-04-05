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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sinau.android.sinauYo.model.User;


public class SignupActivity extends AppCompatActivity {

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

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        btnDaftar = findViewById(R.id.btnDaftar);

//        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    //Persyaratan onDaftar
    public void onDaftar(View view) {
        createAccount(mEmail.getText().toString(),mPassword.getText().toString());
    }

    private void createAccount(String email,String password) {
        Log.d(TAG,"createAccount" + email);

        if (!validateForm()){
            return;
        }


        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Log.d(TAG,"createUserWithEmail:" + task.isSuccessful());
                        if (task.isSuccessful()){
//                            Log.d(TAG,"createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            Toast.makeText(MainActivity.this,"Authentication Success.",Toast.LENGTH_SHORT).show();
                            onAuthSuccess(task.getResult().getUser());

//                            updateUI(user);
                        } else{
                            Log.w(TAG,"createUserWithEmail:failure",task.getException());
                            Toast.makeText(SignupActivity.this,"Sign Up Failed.",Toast.LENGTH_SHORT).show();
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

        startActivity(new Intent(SignupActivity.this,MenuActivity.class));
        finish();
    }

    private String usernameFromEmail(String email) {

        if (email.contains("@")){ //memberikan pemisahan berdasarkan tanda at / @
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    private void writeNewUser(String uid, String username, String email) {
        User user = new User(username,email);

        mDatabase.child("users").child(uid).setValue(user);         //membuat akun users ke database
    }

    public void goLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}
