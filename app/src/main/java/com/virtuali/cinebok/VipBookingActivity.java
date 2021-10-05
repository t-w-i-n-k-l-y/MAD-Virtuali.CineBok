package com.virtuali.cinebok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.virtuali.cinebok.model.GlobalClass;
import com.virtuali.cinebok.model.ScheduleVip;
import com.virtuali.cinebok.model.SnackBeverage;
import com.virtuali.cinebok.model.VipBooking;

public class VipBookingActivity extends AppCompatActivity {

    EditText et_no_tickets_t;
    Button btn_confirm1_t, btn_yes_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_booking);


        Intent i = getIntent();
        String ticketPrice = i.getExtras().getString("ticketPrice");

        et_no_tickets_t = findViewById(R.id.et_no_tickets_t);
        btn_confirm1_t = findViewById(R.id.btn_confirm1_t);
        btn_yes_t = findViewById(R.id.btn_yes_t);

        btn_confirm1_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(TextUtils.isEmpty(et_no_tickets_t.getText().toString().trim())){
                        Toast.makeText(getApplicationContext(), "Please enter no of tickets", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        VipBooking vb = new VipBooking();
                        vb.setNoTickets(et_no_tickets_t.getText().toString().trim());
                        vb.settPrice(ticketPrice);

                        GlobalClass.snackTot = 0;
                        String tot = vb.getTicketTot();

                        Intent intent = new Intent(VipBookingActivity.this,VipCheckoutActivity.class);
                        intent.putExtra("TicketTot", tot);
                        startActivity(intent);
                    }
                }catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Number Format Exception", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_yes_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(TextUtils.isEmpty(et_no_tickets_t.getText().toString().trim())){
                        Toast.makeText(getApplicationContext(), "Please enter no of tickets", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        VipBooking vb = new VipBooking();
                        vb.setNoTickets(et_no_tickets_t.getText().toString().trim());
                        vb.settPrice(ticketPrice);

                        String tot = vb.getTicketTot();

                        Intent intent = new Intent(VipBookingActivity.this, SnackBeverageActivity.class);
                        intent.putExtra("TicketTot", tot);
                        startActivity(intent);
                    }
                }catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Number Format Exception", Toast.LENGTH_SHORT).show();
                }
            }
        });
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