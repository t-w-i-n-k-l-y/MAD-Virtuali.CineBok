package com.virtuali.cinebok;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.virtuali.cinebok.model.SnackBeverage;

import java.util.ArrayList;

public class SnackListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    SnackBeverageAdapter myAdapter;
    ArrayList<SnackBeverage> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_list);

        recyclerView = findViewById(R.id.recview);
        database = FirebaseDatabase.getInstance().getReference().child("SnackBeverage");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new SnackBeverageAdapter(this, list);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.searchmenu, menu);

        MenuItem item = menu.findItem(R.id.search_snack_t);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str){
//        database = FirebaseDatabase.getInstance().getReference().child("SnackBeverage");
        database.orderByChild("sbName").startAt(str).endAt(str + "~").addValueEventListener(new ValueEventListener() {
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
                myAdapter = new SnackBeverageAdapter(list);
                myAdapter.notifyDataSetChanged();
//                recyclerView.setAdapter(myAdapter);


            }



            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {

            }
        });
    }
}