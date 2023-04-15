package com.example.colorguessgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    public static final String Extra_Name = "com.example.colorguessgame.mainactivity";
    TextView textView;
    TextInputEditText name;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.calmmusic);
        mediaPlayer.start();
        ConstraintLayout constraintLayout = findViewById(R.id.mainLayout);
        name = findViewById(R.id.name);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(3000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        Intent intent = new Intent(this,MainActivity2.class);


        button.setTranslationY(50);
        button.animate().alpha(1f).translationYBy(-50).setStartDelay(200).setDuration(2000);

        button.animate().scaleYBy(0.3f).scaleXBy(0.3f).setDuration(3000);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MediaPlayer mediaPlayer1 = MediaPlayer.create(MainActivity.this,R.raw.transitionsound);
                mediaPlayer1.start();
                String nameText = name.getText().toString();
                intent.putExtra(Extra_Name,nameText);
                startActivity(intent);
                mediaPlayer.stop();
            }
        });
    }
}