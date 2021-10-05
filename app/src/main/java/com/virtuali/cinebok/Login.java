package com.virtuali.cinebok;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class Login extends AppCompatActivity {

    EditText mEmail , mPassword;
    Button mLoginBtn;
    TextView mCreatebtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.editrEmail);
        mPassword = findViewById(R.id.editrPassword);
        fAuth = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.btnLogin);
        mCreatebtn = findViewById(R.id.gotoRegister);
        progressBar = findViewById(R.id.LprogressBar);



        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, "Please enter email", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Please enter a password", Toast.LENGTH_SHORT).show();
                }

                if (password.length()<6){
                    mPassword.setError("Password mustbe more than 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                //Authenticate the user
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Login successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        }else {
                            Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        mCreatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
                Toast.makeText(Login.this, "Loading register page...", Toast.LENGTH_SHORT).show();
            }
        });



    }

//    public void gotoReg(View view){
//        Intent intent =new Intent(this , Register.class);
//        startActivity(intent);
//    }





    public void myOnClick(View v) {
        Log.d("DEBUG", "CLICKED " + v.getId());
    }
}