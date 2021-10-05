/*package com.virtuali.cinebok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HallMainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_main3);
    }
}
*/



package com.virtuali.cinebok;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;

public class HallMainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_main3);
        getSupportActionBar().setTitle("Hall Management");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void sendDetail3(View view){
        Intent intent = new Intent(this,HallMainActivity4.class);
        startActivity(intent);
    }
}