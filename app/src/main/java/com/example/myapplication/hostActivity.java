package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class hostActivity extends AppCompatActivity {

    EditText e,h;
    Button bu;
    String s,host;
    ImageButton img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        e=findViewById(R.id.tex);
        bu=findViewById(R.id.but1);
        h=findViewById(R.id.usser);
        img=findViewById(R.id.imageButton);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent i=getIntent();
        h.setText(i.getStringExtra("host"));
        host=h.getText().toString();
        e.setText(i.getStringExtra("id").toString());
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e.getText().toString().length() >0){
                    s=e.getText().toString();
                    Log.d("vams",e.getText().toString());
                    Intent it=new Intent(hostActivity.this,playActivity.class);
                    it.putExtra("id",e.getText().toString());
                    it.putExtra("host",host);
                    startActivity(it);
                }
            }
        });
    }

}
