package com.example.colorguessgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity3 extends AppCompatActivity {
    ConstraintLayout constraintLayout;
    public static final String Extra_Name_MainAct3 = "com.example.colorguessgame.mainactivity3";
    ArrayList<String> userChoice = new ArrayList<>();
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,submit,reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        constraintLayout = findViewById(R.id.constraintLayout);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        submit = findViewById(R.id.submit);
        reset = findViewById(R.id.reset);
        Intent intent = getIntent();
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(3000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity3.this,R.raw.buttonsound2);
                mediaPlayer.start();
                String randomArray[] = intent.getStringArrayExtra(MainActivity2.Extra_Name_Mainact2);

                for(int i=0; i<randomArray.length; i++){
                    // Player lost
                    if(!Objects.equals(randomArray[i], userChoice.get(i))){
                        MediaPlayer mediaPlayer1 = MediaPlayer.create(MainActivity3.this,R.raw.losesound2);
                        mediaPlayer1.start();
                        Intent intent1 = new Intent(MainActivity3.this,MainActivity4.class);
                        intent1.putExtra(Extra_Name_MainAct3,randomArray);
                        startActivity(intent1);
//                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
//                        builder.setCancelable(true);
//                        builder.setTitle("OOPS!\nYou choosed wrong sequence");
//                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                dialogInterface.cancel();
//                            }
//                        });
//                        builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                gameReset();
//                                dialogInterface.cancel();
//                                Intent intent1 = new  Intent(MainActivity3.this,MainActivity2.class);
//                                startActivity(intent1);
//                            }
//                        });
//                        builder.show();
                        break;
//                        submit.setText("You loose");
                    }
                    // Player won
                    else if(i==randomArray.length-1){
                        MediaPlayer mediaPlayer2 = MediaPlayer.create(MainActivity3.this,R.raw.winsound2);
                        mediaPlayer2.start();
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                        builder.setCancelable(true);
                        builder.setTitle("Great!\nYou choosed correct sequence.");
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MediaPlayer mediaPlayer1 = MediaPlayer.create(MainActivity3.this,R.raw.buttonsound2);
                                mediaPlayer1.start();
                                dialogInterface.cancel();
                            }
                        });
                        builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MediaPlayer mediaPlayer1 = MediaPlayer.create(MainActivity3.this,R.raw.buttonsound2);
                                mediaPlayer1.start();
                                gameReset();
                                dialogInterface.cancel();
                                Intent intent1 = new Intent(MainActivity3.this,MainActivity2.class);
                                startActivity(intent1);
                            }
                        });
                        builder.show();
//                        submit.setText("You won");
                    }
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity3.this,R.raw.buttonsound2);
                mediaPlayer.start();
                gameReset();
            }
        });

    }
    public void gameReset(){
        userChoice.clear();
        userIterator=0;
        btn1.setAlpha(1);
        btn2.setAlpha(1);
        btn3.setAlpha(1);
        btn4.setAlpha(1);
        btn5.setAlpha(1);
        btn5.setAlpha(1);
        btn6.setAlpha(1);
        btn7.setAlpha(1);
    }
    int userIterator=0;
    public void onClick(View view){
        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity3.this,R.raw.buttonsound);
        mediaPlayer.start();
        String tappedColor = (String) view.getTag();
        userChoice.add(userIterator,tappedColor);
        userIterator++;
        view.setAlpha(0.25f);
    }
}