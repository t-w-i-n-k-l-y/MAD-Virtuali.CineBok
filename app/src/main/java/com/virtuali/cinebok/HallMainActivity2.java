/*package com.virtuali.cinebok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HallMainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_main2);
    }
}
*/




package com.virtuali.cinebok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HallMainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_main2);
        getSupportActionBar().setTitle("Hall Management");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void sendDetails2(View view){
        Intent intent = new Intent(this,HallMainActivity3.class);
        startActivity(intent);
    }
}