package com.example.googleservices;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class texttospeech extends AppCompatActivity {
    Button bt;
    EditText tv;
    TextToSpeech t1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.texttospeech);
        tv = findViewById(R.id.et);
        bt = findViewById(R.id.bt);
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR){
                    t1.setLanguage(Locale.US);
                }
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                String toSpeak = tv.getText().toString();
                Toast.makeText(texttospeech.this,toSpeak,Toast.LENGTH_SHORT).show();
                t1.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
            }
        });
    }
    public void onPause(){
        if (t1!=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }
}
