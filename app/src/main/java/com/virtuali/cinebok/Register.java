package com.virtuali.cinebok;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText mFullname , mEmail , mPassword ,mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullname = findViewById(R.id.editrName);
        mEmail = findViewById(R.id.editrEmail);
        mPassword = findViewById(R.id.editrPassword);
        mPhone = findViewById(R.id.editrphone);
        mRegisterBtn = findViewById(R.id.btnRegister);
        mLoginBtn = findViewById(R.id.gotoLogin);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivityR.class));

        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this, "Please enter email", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this, "Please enter a password", Toast.LENGTH_SHORT).show();
                }

                if (password.length()<6){
                    mPassword.setError("Password mustbe more than 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //register the user
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Login.class));
                        }else{
                            Toast.makeText(Register.this, "Error is occured", Toast.LENGTH_SHORT).show();
                        }

                    }
                });



            }
        });


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
                Toast.makeText(Register.this, "Loading login page...", Toast.LENGTH_SHORT).show();

            }
        });

    }
}