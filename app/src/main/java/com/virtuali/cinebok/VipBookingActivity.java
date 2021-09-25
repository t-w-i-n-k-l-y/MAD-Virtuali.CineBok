package com.virtuali.cinebok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VipBookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_booking);

        Intent intent = getIntent();
        final String tPrice = intent.getStringExtra("tPrice");
    }

    public void gotoCheckout(View view){
        Intent intent = new Intent(this, VipCheckoutActivity.class);
        startActivity(intent);
    }

    public void gotosnack(View view){
        Intent intent = new Intent(this, SnackBeverageActivity.class);
        startActivity(intent);
    }
}