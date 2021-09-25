package com.virtuali.cinebok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SnackMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_menu);
    }

    public void gotosnackList(View view){
        Intent intent = new Intent(this, SnackListActivity.class);
        startActivity(intent);
    }

    public void gotosnackAdd(View view){
        Intent intent = new Intent(this, AddNewSnackActivity.class);
        startActivity(intent);
    }
}