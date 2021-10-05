/*package com.virtuali.cinebok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HallAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_add);
    }
}
*/


package com.virtuali.cinebok;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class HallAddActivity extends AppCompatActivity {

    EditText name,description,hurl;
    Button btnAdd,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_add);

        name=(EditText)findViewById(R.id.txtHall);
        description =(EditText)findViewById(R.id.txtDescription);
        hurl = (EditText)findViewById(R.id.txtImageUrl);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnBack=(Button)findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                clearAll();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void insertData(){
        Map<String,Object>map = new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("description",description.getText().toString());
        map.put("hurl",hurl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Hall").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(HallAddActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(HallAddActivity.this, "Error while Insertion", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void clearAll(){
        name.setText("");
        description.setText("");
        hurl.setText("");
    }
}