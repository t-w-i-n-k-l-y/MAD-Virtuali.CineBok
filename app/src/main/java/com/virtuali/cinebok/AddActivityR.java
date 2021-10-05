package com.virtuali.cinebok;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivityR extends AppCompatActivity {

    EditText name , director , description , murl;
    Button btnAdd , btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addr);

        getSupportActionBar().setTitle("Movie management");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = (EditText)findViewById(R.id.txtName);
        director = (EditText)findViewById(R.id.txtDirector);
        description = (EditText)findViewById(R.id.txtDescription);
        murl = (EditText)findViewById(R.id.txtMurl);

        btnAdd = (Button)findViewById(R.id.btnAdd);
//        btnBack = (Button)findViewById(R.id.btnBack);
        btnBack = (Button)findViewById(R.id.btnBack);

      btnAdd.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
                insertData();
                clearAll();
          }
      });

      btnBack.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              finish();
          }
      });


    }

    private void insertData()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("director",director.getText().toString());
        map.put("description",description.getText().toString());
        map.put("murl",murl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("movies").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivityR.this, "Movie inserted successfully!!!", Toast.LENGTH_SHORT).show();
                        Intent intentM = new Intent(AddActivityR.this,MainActivityR.class);
                        startActivity(intentM);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddActivityR.this, "Insertion failed", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void clearAll(){
        name.setText("");
        director.setText("");
        description.setText("");
        murl.setText("");
    }
}