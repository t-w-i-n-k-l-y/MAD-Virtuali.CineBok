package com.virtuali.cinebok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
    }
    public void gotosnackmanage(View view){
        Intent intent = new Intent(this, SnackMenuActivity.class);
        startActivity(intent);
    }
    public void gotoHallManage(View view){
        Intent intent = new Intent(this, HallDetails.class);
        startActivity(intent);
    }
}