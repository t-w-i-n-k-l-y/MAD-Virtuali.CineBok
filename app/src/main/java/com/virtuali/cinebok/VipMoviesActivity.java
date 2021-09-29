package com.virtuali.cinebok;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.virtuali.cinebok.model.ScheduleVip;

import java.util.ArrayList;

public class VipMoviesActivity extends AppCompatActivity {

    RecyclerView recview;
    VipMovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_movies);
        setTitle("Search here..");

        recview = (RecyclerView) findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ScheduleVip> options =
                new FirebaseRecyclerOptions.Builder<ScheduleVip>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("test_t"), ScheduleVip.class)
                        .build();

        adapter = new VipMovieAdapter(options);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu, menu);

        MenuItem item = menu.findItem(R.id.search_snack_t);

        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

    private void processsearch(String s) {
        FirebaseRecyclerOptions<ScheduleVip> options =
                new FirebaseRecyclerOptions.Builder<ScheduleVip>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("test_t")
                                .orderByChild("mName")
                                .startAt(s)
                                .endAt(s + "\uf8ff"), ScheduleVip.class)
                                .build();

        adapter = new VipMovieAdapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);

    }

    //to move to booking activity - pass tPrice
//    public void gotomovieDet(View view) {
//        Intent intent = new Intent(this, VipBookingActivity.class);
//        startActivity(intent);
//    }
}