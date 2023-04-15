package com.example.colorguessgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {
    RecyclerView recyclerView;
    Button playAgain;
    TextView textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        playAgain = findViewById(R.id.playAgain);
        textView3 = findViewById(R.id.textView3);
        textView3.setText("OOPS!, You choose the wrong order.\nThe correct order is displayed below.");
        Intent intent = getIntent();
        String randomArray[] = intent.getStringArrayExtra(MainActivity3.Extra_Name_MainAct3);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CustomAdapter c = new CustomAdapter(randomArray);
        recyclerView.setAdapter(c);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity4.this,R.raw.buttonsound2);
                mediaPlayer.start();
                Intent intent1 = new Intent(MainActivity4.this,MainActivity2.class);
                startActivity(intent1);
            }
        });
    }
}