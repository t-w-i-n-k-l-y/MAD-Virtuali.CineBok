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

public class AddSchedule extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    Button bt1,bt2,bt3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_schedule);
        getSupportActionBar().setTitle("   Schedule Management");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ed1=(EditText)findViewById(R.id.ET1_p);
        ed2=(EditText)findViewById(R.id.ET2_p);
        ed3=(EditText)findViewById(R.id.ET3_p);
        ed4=(EditText)findViewById(R.id.ET4_p);

        bt1=(Button)findViewById(R.id.button1_p);
        bt2=(Button)findViewById(R.id.button2_p);
        bt3=(Button)findViewById(R.id.button3_p);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertschedule();
                clear();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opengetDetails();

            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opengetDetails();

            }
        });


}

 public void opengetDetails(){
     Intent intent=new Intent(this,getDetails.class);
     startActivity(intent);
 }


    private void insertschedule()
    {
     Map<String,Object> map=new HashMap<>();
     map.put("Movie",ed1.getText().toString());
        map.put("Hall",ed2.getText().toString());
        map.put("Time",ed3.getText().toString());
        map.put("Duration",ed4.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Schedule").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddSchedule.this,"Data inserted", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {
                        Toast.makeText(AddSchedule.this,"Data not inserted", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private  void clear(){
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed4.setText("");
    }
}