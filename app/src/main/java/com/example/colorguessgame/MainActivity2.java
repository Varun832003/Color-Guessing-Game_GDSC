package com.example.colorguessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity2 extends AppCompatActivity {
    public static final String Extra_Name_Mainact2 = "com.example.colorguessgame.mainactivity2";
    public static final String Extra_Name_MainAct2 = "com.example.colorguessgame.MainActivity2";
    TextView textView2;
    String color[] = {"red","blue","green","black","yellow","orange","pink"};
    String randomArray[] = new String[color.length];
    ArrayList<String> randomList = new ArrayList<>();
    HashMap<Integer,String> hashMap = new HashMap<>();
    Button colorBox;
    static final int WAITTIME = 1000;
    int currentColor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Random random = new Random(0);
        setContentView(R.layout.activity_main2);
        colorBox = findViewById(R.id.colorBox);
        textView2 = findViewById(R.id.textView2);
        Intent intent = getIntent();
        String playerName = intent.getStringExtra(MainActivity.Extra_Name);
        textView2.setText("Get Ready "+playerName+"!\nRemember the Order of Colours.");
        if(playerName==null){
            textView2.setText("Get Ready!\nRemember the order of colors.");
        }
        for(int i=0; i<color.length; i++){
            hashMap.put(i,color[i]);
        }
        int i=0;
        while(!hashMap.isEmpty()){
            int key = random.nextInt(7);
            if(hashMap.containsKey(key)){
//                randomArray[i]=hashMap.get(key);
                randomList.add(i,hashMap.get(key));
                hashMap.remove(key);
                i++;
            }

        }
        Collections.shuffle(randomList);
        displayColors();
    }
    public void displayColors(){
        new Thread() {
            public void run() {
                while(currentColor<color.length) {
                    try {
                        Thread.sleep(WAITTIME);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
//                    currentColor++;
                    if (currentColor > randomList.size())
                        currentColor = 0;
                    MainActivity2.this.runOnUiThread(new Runnable() {
                        public void run() {
                            if(currentColor<color.length){
                                if(Objects.equals(randomList.get(currentColor), "red")){
                                    colorBox.setBackgroundColor(getResources().getColor(R.color.red));
                                } if (Objects.equals(randomList.get(currentColor), "blue")) {
                                    colorBox.setBackgroundColor(getResources().getColor(R.color.blue));
                                } if (Objects.equals(randomList.get(currentColor), "green")) {
                                    colorBox.setBackgroundColor(getResources().getColor(R.color.green));
                                }if (Objects.equals(randomList.get(currentColor), "black")) {
                                    colorBox.setBackgroundColor(getResources().getColor(R.color.black));
                                }if (Objects.equals(randomList.get(currentColor), "yellow")) {
                                    colorBox.setBackgroundColor(getResources().getColor(R.color.yellow));
                                }if (Objects.equals(randomList.get(currentColor), "orange")) {
                                    colorBox.setBackgroundColor(getResources().getColor(R.color.orange));
                                }if (Objects.equals(randomList.get(currentColor), "pink")) {
                                    colorBox.setBackgroundColor(getResources().getColor(R.color.pink));
                                }
                                currentColor++;
                            }else{
                                for(int i=0; i<randomList.size(); i++){
                                    randomArray[i] = randomList.get(i);
                                }
                                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                                intent.putExtra(Extra_Name_Mainact2,randomArray);
                                startActivity(intent);
                                MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity2.this,R.raw.transitionsound);
                                mediaPlayer.start();
                            }

                        }
                    });
                }
            }
        }.start();
        if(currentColor==7){
            return;
        }
    }

}