package com.virtuali.cinebok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HallMainActivity6 extends AppCompatActivity {


    private Button btnAdmin,btnCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_main6);
        getSupportActionBar().setTitle("Hall Management");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        btnAdmin = (Button)findViewById(R.id.admin);
        btnCustomer = (Button)findViewById(R.id.customer);

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(HallMainActivity6.this, HallAdminLoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });


        btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(HallMainActivity6.this, HallCustomerLoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
}