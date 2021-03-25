package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Main3Activity extends AppCompatActivity {
    EditText e;
    Button bu;
    ImageButton img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        e=findViewById(R.id.tex);
        bu=findViewById(R.id.but1);
        img=findViewById(R.id.imageButton);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e.getText().toString().length() >0){
                    Log.d("vams",e.getText().toString());
                    Intent it=new Intent(Main3Activity.this,Main2Activity.class);
                    it.putExtra("id",e.getText().toString());
                    it.putExtra("id",e.getText().toString());
                    startActivity(it);
                }
            }
        });
    }
}
