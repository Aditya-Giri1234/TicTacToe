package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button friend,computer;
public static Activity main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main=this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }

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