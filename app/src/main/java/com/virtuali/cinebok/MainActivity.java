package com.virtuali.cinebok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // TODO: Your application init goes here.
              //  Intent mInHome = new Intent(MainActivity.this, HomeActivity.class);
                Intent mInHome = new Intent(MainActivity.this, Login.class);
                startActivity(mInHome);
                finish();
            }
        }, 3000);
    }

    public void gotoVIPmovies(View view){
        Intent intent = new Intent(this, VipMoviesActivity.class);
        startActivity(intent);
    }
}

