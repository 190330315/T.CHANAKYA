package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {

    ImageButton img;
    TextView textView,t2;
    HashMap<String,String> h = new HashMap<String,String>();
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView =findViewById(R.id.tresp);
        t2=findViewById(R.id.text2);
        img=findViewById(R.id.imageButton);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        FirebaseDatabase fire =FirebaseDatabase.getInstance();
        final DatabaseReference ref=fire.getReference("players");
        final Object obj=new Object();
        SharedPreferences sp=getSharedPreferences("id",MODE_PRIVATE);
        Intent i=getIntent();
        String e=i.getStringExtra("id");
        Log.d("vams",e);
        Toast.makeText(getApplicationContext(),e,Toast.LENGTH_LONG);
        Log.d("vams",e);
        tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    tts.setPitch(0.9f);
                    tts.setLanguage(Locale.UK);
                    tts.setSpeechRate(0.7f);
                }
            }
        });
        ref.child(e).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            for(DataSnapshot d:snapshot.getChildren()){
                               if(d.getKey().toString().equalsIgnoreCase("number")){
                                   load(d.getValue().toString());
                               }
                               if(d.getKey().toString().equalsIgnoreCase("history")){
                                   history(d.getValue().toString());
                               }
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



    }

    void speak(String s){
        tts.speak(s,TextToSpeech.QUEUE_FLUSH,null);
    }
    void load(String s){
        textView.setText(s);
        speak(s);
    }
    void history(String s){
        t2.setText(s);
    }
}
