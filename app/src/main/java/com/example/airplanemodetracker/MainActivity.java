package com.example.airplanemodetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;

public class MainActivity extends AppCompatActivity {
    private Button bttn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextClock clock = (TextClock) findViewById(R.id.clock1);


        bttn = (Button) findViewById(R.id.button);
        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }

    private void openActivity2() {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }
}
