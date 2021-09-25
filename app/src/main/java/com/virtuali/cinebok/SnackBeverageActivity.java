package com.virtuali.cinebok;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.virtuali.cinebok.model.SnackBeverage;

import java.util.ArrayList;

public class SnackBeverageActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    SnackBeverageAdapterCus myAdapter;
    ArrayList<SnackBeverage> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_beverage);

        recyclerView = findViewById(R.id.recview);
        database = FirebaseDatabase.getInstance().getReference().child("SnackBeverage");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new SnackBeverageAdapterCus(this, list);
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    if (dataSnapshot.hasChildren()) {
                        SnackBeverage sb = new SnackBeverage();
                        sb.setSbName(dataSnapshot.child("sbName").getValue().toString());
                        sb.setSbSize(dataSnapshot.child("sbSize").getValue().toString());
                        sb.setSbPrice(dataSnapshot.child("sbPrice").getValue().toString());
                        sb.setSbAvailability(dataSnapshot.child("sbAvailability").getValue().toString());
                        sb.setSbUrl(dataSnapshot.child("sbUrl").getValue().toString());

                        list.add(sb);
                    } else
                        Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();


                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {

            }
        });



    }

    public void gotocheckout(View view){
        Intent intent = new Intent(this, VipCheckoutActivity.class);
        startActivity(intent);
    }
}
