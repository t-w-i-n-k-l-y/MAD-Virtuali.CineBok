package com.virtuali.cinebok;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.virtuali.cinebok.model.ScheduleVip;

import java.util.ArrayList;

public class VipMoviesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    VipMovieAdapter myAdapter;
    ArrayList<ScheduleVip> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_movies);

        recyclerView = findViewById(R.id.recview);
        database = FirebaseDatabase.getInstance().getReference().child("test_t");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new VipMovieAdapter(this, list);
        recyclerView.setAdapter(myAdapter);

        //--------------------------working start------------------------------------------
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    if (dataSnapshot.hasChildren()) {
                        ScheduleVip sv = new ScheduleVip();
                        sv.setmName(dataSnapshot.child("mName").getValue().toString());
                        sv.sethName(dataSnapshot.child("hName").getValue().toString());
                        sv.setDuration(dataSnapshot.child("duration").getValue().toString());
                        sv.setDate(dataSnapshot.child("date").getValue().toString());
                        sv.setTime(dataSnapshot.child("time").getValue().toString());
                        sv.settPrice(dataSnapshot.child("tPrice").getValue().toString());

                        list.add(sv);
                    } else
                        Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();


                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {

            }
        });
        //--------------------------------------working end---------------------------------------------

    }

    //to move to booking activity - pass tPrice
    public void gotomovieDet(View view){
        Intent intent = new Intent(this, VipBookingActivity.class);
        intent.putExtra("tPrice", 1000);

        startActivity(intent);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.searchmenu, menu);
//
//        MenuItem item = menu.findItem(R.id.search_snack_t);
//        SearchView searchView = (SearchView) item.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                txtSearch(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String query) {
//                txtSearch(query);
//                return false;
//            }
//        });
//
//        return super.onCreateOptionsMenu(menu);
//    }

//    private void txtSearch(String str){
////        database = FirebaseDatabase.getInstance().getReference().child("SnackBeverage");
//        database.orderByChild("sbName").startAt(str).endAt(str + "~").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//
//                    if (dataSnapshot.hasChildren()) {
//                        SnackBeverage sb = new SnackBeverage();
//                        sb.setSbName(dataSnapshot.child("sbName").getValue().toString());
//                        sb.setSbSize(dataSnapshot.child("sbSize").getValue().toString());
//                        sb.setSbPrice(dataSnapshot.child("sbPrice").getValue().toString());
//                        sb.setSbAvailability(dataSnapshot.child("sbAvailability").getValue().toString());
//                        sb.setSbUrl(dataSnapshot.child("sbUrl").getValue().toString());
//
//                        list.add(sb);
//                    } else
//                        Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
//
//
//                }
//                myAdapter = new SnackBeverageAdapter(list);
//                myAdapter.notifyDataSetChanged();
////                recyclerView.setAdapter(myAdapter);
//
//
//            }
//
//
//
//            @Override
//            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {
//
//            }
//        });
//    }
}



//    et_ID = findViewById(R.id.et_ID);
//    et_name = findViewById(R.id.et_name);
//    et_address = findViewById(R.id.et_address);
//    et_ConNo = findViewById(R.id.et_phone);
//
//    btn_save = findViewById(R.id.btn_save);
//    btn_show = findViewById(R.id.btn_show);
//    btn_update = findViewById(R.id.btn_update);
//    btn_delete = findViewById(R.id.btn_delete);
//
//    sb1 = new SnackBeverage();
//
//    dbRef = FirebaseDatabase.getInstance().getReference().child("Student"); //child = table name in real time database
//
//        btn_show.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Student").child("Student1");
//                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
//                        if(datasnapshot.hasChildren()){
//                            et_ID.setText(datasnapshot.child("stdID").getValue().toString());
//                            et_name.setText(datasnapshot.child("name").getValue().toString());
//                            et_address.setText(datasnapshot.child("address").getValue().toString());
//                            et_ConNo.setText(datasnapshot.child("conNo").getValue().toString());
//                        }
//                        else
//                            Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//            }
//        });
//
//
//
//    }
//method to clear user inputs
//    private void clearControls(){
//        et_ID.setText("");
//        et_name.setText("");
//        et_address.setText("");
//        et_ConNo.setText("");
//    }
