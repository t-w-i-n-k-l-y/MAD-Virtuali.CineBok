package com.virtuali.cinebok;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class getDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_movie_det);
        Objects.requireNonNull(getSupportActionBar()).setTitle("   Movie-Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=(RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Schedule> options =
                new FirebaseRecyclerOptions.Builder<Schedule>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Schedule"), Schedule.class)
                        .build();

        movieAdapter=new MovieAdapter(options);
        recyclerView.setAdapter(movieAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
         movieAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        movieAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_sched, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                txtSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                txtSearch(s);
                return false;
            }
        });




        return super.onCreateOptionsMenu(menu);
    }

     private void  txtSearch(String str)
     {
         FirebaseRecyclerOptions<Schedule> options =
                 new FirebaseRecyclerOptions.Builder<Schedule>()
                         .setQuery(FirebaseDatabase.getInstance().getReference().child("Schedule").orderByChild("Movie").startAt(str).endAt(str+"~"), Schedule.class)
                         .build();

         movieAdapter=new MovieAdapter(options);
         movieAdapter.startListening();
         recyclerView.setAdapter(movieAdapter);



     }
}
