package com.virtuali.cinebok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.virtuali.cinebok.model.GlobalClass;

public class VipCheckoutActivity extends AppCompatActivity {

    TextView et_ticket_tot_t, et_snack_tot_t, et_tot_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_checkout);

        Intent i = getIntent();
        String ticketTot = i.getExtras().getString("TicketTot");
        int snackTot = GlobalClass.snackTot;
        GlobalClass.snackTot = 0;

        et_snack_tot_t = findViewById(R.id.et_snack_tot_t);
        et_ticket_tot_t = findViewById(R.id.et_ticket_tot_t);
        et_tot_t = findViewById(R.id.et_tot_t);

        et_ticket_tot_t.setText(ticketTot);
        et_snack_tot_t.setText(String.valueOf(snackTot));

        double Subtot = Double.parseDouble(ticketTot) + snackTot;

        et_tot_t.setText(String.valueOf(Subtot));


    }

    public void gotomovieList(View view) {
        Intent intent = new Intent(this, VipMoviesActivity.class);
        startActivity(intent);
    }
}