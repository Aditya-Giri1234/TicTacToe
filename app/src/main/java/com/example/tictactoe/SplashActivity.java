package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;


import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
TextView tic,tac,toe;
ImageView smile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY );

        tic=findViewById(R.id.tic);
        tac=findViewById(R.id.tac);
        toe=findViewById(R.id.toe);
        smile=findViewById(R.id.smile);

        Animation animationTic=AnimationUtils.loadAnimation(this,R.anim.horizontal_left);
        Animation animationTac=AnimationUtils.loadAnimation(this,R.anim.up);
        Animation animationToe=AnimationUtils.loadAnimation(this,R.anim.horizontal_right);
tic.startAnimation(animationTic);
tac.startAnimation(animationTac);
toe.startAnimation(animationToe);
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
             smile.setVisibility(View.VISIBLE);
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       startActivity(new Intent(getApplicationContext(),MainActivity.class));
                       finish();
                   }
               },2000);
           }
       },4000);


    }
}