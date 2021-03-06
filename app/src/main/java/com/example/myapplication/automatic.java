package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class automatic extends AppCompatActivity {

    TextView t,sec;
    TextView p;
    TextView f;
    SeekBar sb;
    Button b;
    TextToSpeech mTTS;
    final lotto l=new lotto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automatic);
        t=(TextView) findViewById(R.id.text);
        sec=(TextView) findViewById(R.id.sec);
        p=(TextView) findViewById(R.id.text2);
        f=(TextView) findViewById(R.id.text3);
        b=(Button) findViewById(R.id.play);
        sb=(SeekBar)findViewById(R.id.seekBar);
        b.setEnabled(false);
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
        final Boolean[] ok = {true};
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int d;
                if(ok[0] ==true){
                    ok[0] =false;
                    b.setEnabled(true);
                }
                sec.setText(sb.getProgress()+" sec");
                d=(int)sb.getProgress()*1000;
                l.setD(d);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        final game f=new game();
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                l.start();
                b.setEnabled(false);
                sb.setEnabled(false);

            }
        });

    }
    @Override
    public void onBackPressed() {
        this.l.close();
        Log.d("fin","finish");
        finish();
        super.onBackPressed();
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
        int d;
        Random r=new Random();
        void setD(int x)
        {
            this.d=x;
        }
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


        }
        void setArr(int []a)
        {
            int k;
            for(k=0;k<n;k++)
            {
                a[k]=k+1;
            }
        }
        Boolean exit=false;
        public void run()
        {
            int v=1,k;
            int j;
            int []a= new int[n];
            setArr(a);
            lotto l=new lotto();
            while (v <= 90 && exit==false) {

                random(a);
                try {
                    l.sleep(d);

                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                v++;
            }
            t.setText(" ");
            f.setText("Finished");
            if(exit!=true) {
                speak("The Game is Finished");
            }
            finish();
        }
        void close(){
            this.exit=true;
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

