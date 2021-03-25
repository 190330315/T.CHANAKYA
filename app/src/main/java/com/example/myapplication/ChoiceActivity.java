package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoiceActivity extends AppCompatActivity {

    Button b1;
    Button b2;
    int tp=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        b1=(Button) findViewById(R.id.play);
        b2=(Button) findViewById(R.id.room);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(ChoiceActivity.this,tim.class);
                startActivity(a);
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b=new Intent(ChoiceActivity.this,RoomChoice.class);
                startActivity(b);
            }
        });

    }
}
