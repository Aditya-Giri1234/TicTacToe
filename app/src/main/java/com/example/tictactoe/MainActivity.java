package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button friend,computer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION );
        friend=findViewById(R.id.friend);
        computer=findViewById(R.id.computer);

        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent  intent=new Intent(MainActivity.this,Friend.class);
              startActivity(intent);

            }
        });
        computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent  intent=new Intent(MainActivity.this,Computer.class);
              startActivity(intent);

            }
        });
    }
}