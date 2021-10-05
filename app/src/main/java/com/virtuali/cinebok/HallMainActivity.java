/*package com.virtuali.cinebok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HallMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_main);
    }
}
*/

package com.virtuali.cinebok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HallMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Hall Management");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void SendDetails(View view){
        Intent intent = new Intent(this,HallMainActivity2.class);
        startActivity(intent);
    }
}

