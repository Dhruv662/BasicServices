package com.example.googleservices;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class speechtotext extends AppCompatActivity {
    private static final int voice_input = 1;
    private TextView tv;
    private Button ib;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speechtotext);
        tv = findViewById(R.id.tv);
        ib = findViewById(R.id.stt);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startVoiceInput();
            }
        });
    }
    private void startVoiceInput(){
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say something");
        try{
            startActivityForResult(i,voice_input);
        }catch (ActivityNotFoundException a){

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK && null!=data){
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            tv.setText(result.get(0));
        }
    }

}
