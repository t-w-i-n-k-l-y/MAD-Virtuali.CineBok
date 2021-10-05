package com.virtuali.cinebok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HallMainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_main5);
        getSupportActionBar().setTitle("Hall Management");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}