package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class manual extends AppCompatActivity {

    TextView t;
    TextView p;
    TextView f;
    Button g;
    TextToSpeech mTTS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        t=(TextView) findViewById(R.id.text);
        p=(TextView) findViewById(R.id.text2);
        f=(TextView) findViewById(R.id.text3);
        g=(Button) findViewById(R.id.button2);
        final lotto l=new lotto();
        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {

            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.UK);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        Log.e("TTS", "Language not supported");
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });
        final game f=new game();
        g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                f.ran();
            }
        });
    }
    public void speak(String s)
    {
        String text=s;
        mTTS.setPitch(0.9f);
        mTTS.setSpeechRate(0.5f);
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);

    }

    class lotto extends Thread
    {
        int n=90;
        Random r=new Random();
        int linear(int []a,int x)
        {
            int i;
            for(i=0;i<n;i++)
            {
                if(a[i]==x)
                {
                    return i;
                }
            }
            return -1;
        }
        void remove(int a[],int x)
        {
            int y;
            int k=linear(a,x);
            int i;
            for(i=k;i<n-1;i++)
            {
                a[i]=a[i+1];
            }
            y=n-1;
            if(y!=0)
                g.setText(String.valueOf(y));
            else {
                g.setText("End");
                speak("the game is finished");
            }
            n--;
        }
        void random(int []a)
        {
            int j;
            j=r.nextInt(n);
            t.setText(String.valueOf(a[j]));
            p.append(" "+String.valueOf(a[j]));
            speak(String.valueOf(a[j]));
            remove(a,a[j]);
            if(n<=0)
            {

                g.setEnabled(false);
            }

        }
        void setArr(int []a)
        {
            int k;
            for(k=0;k<n;k++)
            {
                a[k]=k+1;
            }
        }
        public void run()
        {
            int v=1,k;
            int j;
            int []a= new int[n];
            setArr(a);
            lotto l=new lotto();
            while(v<=90)
            {

                random(a);
                try {
                    l.sleep(6000);

                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                v++;
            }
            t.setText(" ");
            f.setText("Finished");
            speak("The Game is Finished");
        }
    }
    class game extends lotto
    {

        int a[]=new int[90];
        game()
        {
            setArr(a);
        }
        void ran()
        {
            random(a);
        }
    }
}

