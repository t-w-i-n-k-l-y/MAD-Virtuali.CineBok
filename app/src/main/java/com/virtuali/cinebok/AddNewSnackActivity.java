package com.virtuali.cinebok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.virtuali.cinebok.model.SnackBeverage;

public class AddNewSnackActivity extends AppCompatActivity {

    EditText et_sb_name_t, et_sb_price_t, et_sb_size_t, et_sb_availability_t, et_url_snack_t;
    Button btn_add1_t;

    SnackBeverage sbObject;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_snack);

        et_sb_name_t = findViewById(R.id.et_sb_name_t);
        et_sb_size_t = findViewById(R.id.et_sb_size_t);
        et_sb_price_t = findViewById(R.id.et_sb_price_t);
        et_sb_availability_t = findViewById(R.id.et_sb_availability_t);
        et_url_snack_t = findViewById(R.id.et_url_snack_t);

        btn_add1_t = findViewById(R.id.btn_add1_t);

        sbObject = new SnackBeverage();

        dbRef = FirebaseDatabase.getInstance().getReference().child("SnackBeverage"); //child -> table name

        btn_add1_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(TextUtils.isEmpty(et_url_snack_t.getText().toString().trim())){
                        Toast.makeText(getApplicationContext(), "Please enter the url", Toast.LENGTH_SHORT).show();
                    }
                    if(TextUtils.isEmpty(et_sb_name_t.getText().toString().trim())){
                        Toast.makeText(getApplicationContext(), "Please enter the item name", Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(et_sb_size_t.getText().toString().trim())){
                        Toast.makeText(getApplicationContext(), "Please enter the item size", Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(et_sb_price_t.getText().toString().trim())){
                        Toast.makeText(getApplicationContext(), "Please enter the item price", Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(et_sb_availability_t.getText().toString().trim())){
                        Toast.makeText(getApplicationContext(), "Please enter the item availability", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        sbObject.setSbName(et_sb_name_t.getText().toString().trim());
                        sbObject.setSbSize(et_sb_size_t.getText().toString().trim());
                        sbObject.setSbPrice(et_sb_price_t.getText().toString().trim());
                        sbObject.setSbAvailability(et_sb_availability_t.getText().toString());
                        sbObject.setSbUrl(et_url_snack_t.getText().toString().trim());

                        dbRef.push().setValue(sbObject);
                        Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();

                        clearControls();
                        Intent intent = new Intent(AddNewSnackActivity.this,SnackListActivity.class);
                        startActivity(intent);
                    }


                }catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Number Format Exception", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    private void clearControls(){
        et_url_snack_t.setText("");
        et_sb_name_t.setText("");
        et_sb_size_t.setText("");
        et_sb_price_t.setText("");
        et_sb_availability_t.setText("");
    }

}