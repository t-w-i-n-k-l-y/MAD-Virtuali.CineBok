package com.virtuali.cinebok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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



    public void schedulemanage(View view){
        Intent intent = new Intent(this, AddSchedule.class);
        startActivity(intent);
    }

    public void gotomoviemanage(View view){
        Intent intentM = new Intent(this,MainActivityR.class);
        startActivity(intentM);
        Toast.makeText(this, "Loading movie menu", Toast.LENGTH_SHORT).show();
    }


//            mLoginBtn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            startActivity(new Intent(getApplicationContext(),Login.class));
//            Toast.makeText(Register.this, "Loading login page...", Toast.LENGTH_SHORT).show();
//
//        }
//    });


}