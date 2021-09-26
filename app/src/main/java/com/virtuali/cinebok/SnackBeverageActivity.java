package com.virtuali.cinebok;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.virtuali.cinebok.model.SnackBeverage;

import java.util.ArrayList;

public class SnackBeverageActivity extends AppCompatActivity {

    RecyclerView recview;
    SnackBeverageAdapterCus adapter;
    FloatingActionButton fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_beverage);
        setTitle("Search here..");

        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<SnackBeverage> options =
                new FirebaseRecyclerOptions.Builder<SnackBeverage>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("SnackBeverage"), SnackBeverage.class)
                        .build();

        adapter=new SnackBeverageAdapterCus(options);
        recview.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.searchmenu,menu);

        MenuItem item=menu.findItem(R.id.search_snack_t);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s) {

                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processsearch(String s)
    {
        FirebaseRecyclerOptions<SnackBeverage> options =
                new FirebaseRecyclerOptions.Builder<SnackBeverage>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("SnackBeverage").orderByChild("sbName").startAt(s).endAt(s+"\uf8ff"), SnackBeverage.class)
                        .build();

        adapter=new SnackBeverageAdapterCus(options);
        adapter.startListening();
        recview.setAdapter(adapter);

    }

    public void gotocheckout(View view){
        Intent intent = new Intent(this, VipCheckoutActivity.class);
        startActivity(intent);
    }}
