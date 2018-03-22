package com.example.android.sinauYo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.net.Uri;



public class LoginActivity extends AppCompatActivity {

    EditText inputUsername, inputPassword;
    Button btnLogin;

    //method berikut akan dijalankan saat aktifitas dimulai
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputUsername = findViewById(R.id.inputUsername);
        inputPassword = findViewById(R.id.inputPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    //Persyaratan login
    public void onLogin(View view) {
        if (inputUsername.getText().toString().equals("sinau") &&
                inputPassword.getText().toString().equals("yo")) {
            //Akan menampilkan toast bila login benar
            Toast.makeText(this, "Login Success!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        } else if (inputUsername.getText().toString().equals("") &&
                inputPassword.getText().toString().equals("")){
            Toast.makeText(this, "Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else {
            //Akan menampilkan toast bila salah
            Toast.makeText(this, "Email / Password salah", Toast.LENGTH_SHORT).show();
            //Akan mengosongkan kolom
            inputUsername.setText("");
            inputPassword.setText("");
        }
    }

    public void onDaftar(View view) {
        Toast.makeText(this, "Activity Daftar belum selesai.", Toast.LENGTH_SHORT).show();
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
