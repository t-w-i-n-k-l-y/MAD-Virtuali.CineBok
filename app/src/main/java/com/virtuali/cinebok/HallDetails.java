package com.virtuali.cinebok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class HallDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    HallMainAdapter mainAdapter;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_details);

        //bottom app bar function name
        getSupportActionBar().setTitle("Hall Management");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<HallRecyclerModel> options =
                new FirebaseRecyclerOptions.Builder<HallRecyclerModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Hall"), HallRecyclerModel.class)
                        .build();

        mainAdapter = new HallMainAdapter(options);
        recyclerView.setAdapter(mainAdapter);

        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HallAddActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hallsearch,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }
            //
            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str){
        FirebaseRecyclerOptions<HallRecyclerModel> options =
                new FirebaseRecyclerOptions.Builder<HallRecyclerModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Hall").orderByChild("name").startAt(str).endAt(str+"~"), HallRecyclerModel.class)
                        .build();
        mainAdapter =new HallMainAdapter(options);
        mainAdapter.startListening();
        recyclerView.setAdapter(mainAdapter);
    }
}