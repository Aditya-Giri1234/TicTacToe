package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Friend extends AppCompatActivity {
LinearLayout first,second,one,two,three,fourth,fifth,six,seven,eight,nine;
ImageView oneImage,twoImage,threeImage,fourthImage,fifthImage,sixImage,sevenImage,eightImage,nineImage;
EditText player1,player2;
TextView firstPlayer,secondPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        Dialog choose=new Dialog(this);
        choose.setContentView(R.layout.friend_pop);
        choose.setCancelable(false);
        Button submit=choose.findViewById(R.id.friend_pop_button);
        player1=choose.findViewById(R.id.player1);
        player2=choose.findViewById(R.id.player2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstPlayer.setText(player1.getText());
                secondPlayer.setText(player2.getText());
                choose.dismiss();
            }
        });
        firstPlayer=findViewById(R.id.firstPlayer);
        secondPlayer=findViewById(R.id.secondPlayer);

        first= findViewById(R.id.first);
        second=findViewById(R.id.second);
        one=findViewById(R.id.one);
        two=findViewById(R.id.two);
        three=findViewById(R.id.three);
        fourth=findViewById(R.id.fourth);
        fifth=findViewById(R.id.fifth);
        six=findViewById(R.id.six);
        seven=findViewById(R.id.seven);
        eight=findViewById(R.id.eight);
        nine=findViewById(R.id.nine);

        oneImage=findViewById(R.id.oneImage);
        twoImage=findViewById(R.id.twoImage);
        threeImage=findViewById(R.id.threeImage);
        fourthImage=findViewById(R.id.fourthImage);
        fifthImage=findViewById(R.id.fifthImage);
        sixImage=findViewById(R.id.sixImage);
        sevenImage=findViewById(R.id.sevenImage);
        eightImage=findViewById(R.id.eightImage);
        nineImage=findViewById(R.id.nineImage);




        choose.show();
    }
}