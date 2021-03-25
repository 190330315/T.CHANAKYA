package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RoomChoice extends AppCompatActivity {

    Button b1;
    Button b2;
    int tp=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_choice);
        b1=(Button) findViewById(R.id.create);
        b2=(Button) findViewById(R.id.join);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(RoomChoice.this,RoomActivity.class);
                startActivity(a);
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b=new Intent(RoomChoice.this,Main3Activity.class);
                startActivity(b);
            }
        });

    }
}
