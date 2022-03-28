package com.sneha.sih_2022_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextView login, signup;
    TextInputEditText email, password;
    Button action;
    TextInputLayout confirmpasswordLayout;

    Boolean isSignUp = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.txt_login);
        signup = findViewById(R.id.txt_signup);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        //confirmPassword = findViewById(R.id.confirmpassword);
        confirmpasswordLayout = findViewById(R.id.condirmPassword);

        action = findViewById(R.id.action);

        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(MainActivity.this,SelectLocation.class));
            finish();
        }

        login.setOnClickListener(v-> {
            isSignUp = true;
            confirmpasswordLayout.setVisibility(View.GONE);
            login.setBackground(getResources().getDrawable(R.drawable.text_selected));
            login.setTextColor(getResources().getColor(R.color.white));
            signup.setTextColor(getResources().getColor(R.color.black));
            signup.setBackground(getResources().getDrawable(R.drawable.text_unselelcted));
            action.setText("Log In");
        });

        signup.setOnClickListener(v-> {
            isSignUp=false;
            confirmpasswordLayout.setVisibility(View.VISIBLE);
            login.setTextColor(getResources().getColor(R.color.black));
            signup.setTextColor(getResources().getColor(R.color.white));
            signup.setBackground(getResources().getDrawable(R.drawable.text_selected));
            login.setBackground(getResources().getDrawable(R.drawable.text_unselelcted));
            action.setText("Sign Up");
        });



        action.setOnClickListener(v-> {
            if (isSignUp){
                handleSignUp();
            }else {
                handleLogin();
            }
        });
    }

    private void handleSignUp(){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                startActivity(new Intent(MainActivity.this,SelectLocation.class));
                Toast.makeText(MainActivity.this, "Signed up successfully", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(MainActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleLogin(){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                startActivity(new Intent(MainActivity.this,SelectLocation.class));
                Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(MainActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}