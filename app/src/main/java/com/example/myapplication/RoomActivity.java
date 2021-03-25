package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class RoomActivity extends AppCompatActivity {

    ImageButton img;
    EditText name;
    TextView t;
    Button b,b2;
    TextToSpeech tts;
    int _id;
    String host;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        SharedPreferences sp=getSharedPreferences("info",MODE_PRIVATE);
        final SharedPreferences.Editor editor=sp.edit();
        name=findViewById(R.id.name);
        t=findViewById(R.id.noob);
        b=findViewById(R.id.but);
        b2=findViewById(R.id.buteq);
        img=findViewById(R.id.imageButton);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final int[] id = new int[1];
        final Random r=new Random();
        final HashMap h=new HashMap();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("players");

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().length() > 0) {
                    final String u_name = name.getText().toString();
                    host=u_name;
                    h.put("host", u_name);
                    h.put("number","hi");
                    h.put("history","");
                    int i=r.nextInt(999999);
                    if(i<100000){
                        i+=100000;
                    }
                    id[0] =i;
                    _id=i;
                    editor.putString("_id",String.valueOf(i));
                    myRef.child(String.valueOf(i)).setValue(h).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            String s="ID is "+id[0];
                            t.setText(s);
                            name.setText("");
                            b2.setEnabled(true);
                        }
                    });
                }
                else {
                    tts= new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            if (status == TextToSpeech.SUCCESS) {
                                tts.setLanguage(Locale.UK);
                                tts.setPitch(0.9f);
                                tts.setSpeechRate(0.7f);
                                String s="Please enter name";
                                tts.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                            }
                        }
                    });

                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RoomActivity.this,hostActivity.class);
                i.putExtra("id",String.valueOf(_id));
                i.putExtra("host",host);
                Log.d("vams","passing"+host+" "+_id);
                startActivity(i);
            }
        });
    }

}
