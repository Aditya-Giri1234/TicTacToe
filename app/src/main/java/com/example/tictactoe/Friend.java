package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
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
int [][] board=new int[3][3];
boolean firstMove=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }

        Dialog choose=new Dialog(this);
        choose.setContentView(R.layout.friend_pop);
        choose.setCancelable(false);
        Button submit=choose.findViewById(R.id.friend_pop_button);
        player1=choose.findViewById(R.id.player1);
        player2=choose.findViewById(R.id.player2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player1.getText().equals("")){
                    player1.setError("Enter a name");
                }
                if(player2.equals("")){
                    player2.setError("Enter a name");
                }
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


        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move(view.findViewById(R.id.oneImage),firstMove,first,second);
                board[0][0]=1;
                int win=check(board);
                winCheck(win,firstMove,player1.getText().toString(),player2.getText().toString());
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move(view.findViewById(R.id.twoImage),firstMove,first,second);
                board[0][1]=1;
                int win=check(board);
                winCheck(win,firstMove,player1.getText().toString(),player2.getText().toString());
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move(view.findViewById(R.id.threeImage),firstMove,first,second);
                board[0][2]=1;
                int win=check(board);
                winCheck(win,firstMove,player1.getText().toString(),player2.getText().toString());
            }
        });
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move(view.findViewById(R.id.fourthImage),firstMove,first,second);
                board[1][0]=1;
                int win=check(board);
                winCheck(win,firstMove,player1.getText().toString(),player2.getText().toString());
            }
        });
        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move(view.findViewById(R.id.fifthImage),firstMove,first,second);
                board[1][1]=1;
                int win=check(board);
                winCheck(win,firstMove,player1.getText().toString(),player2.getText().toString());

            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move(view.findViewById(R.id.sixImage),firstMove,first,second);
                board[1][2]=1;
                int win=check(board);
                winCheck(win,firstMove,player1.getText().toString(),player2.getText().toString());
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move(view.findViewById(R.id.sevenImage),firstMove,first,second);
                board[2][0]=1;
                int win=check(board);
                winCheck(win,firstMove,player1.getText().toString(),player2.getText().toString());
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move(view.findViewById(R.id.eightImage),firstMove,first,second);
                board[2][1]=1;
                int win=check(board);
                winCheck(win,firstMove,player1.getText().toString(),player2.getText().toString());
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move(view.findViewById(R.id.nineImage),firstMove,first,second);
                board[2][2]=1;
                int win=check(board);
                winCheck(win,firstMove,player1.getText().toString(),player2.getText().toString());
            }
        });
    }

    public void move(ImageView view,boolean move,LinearLayout first,LinearLayout second){
        if(firstMove){
            view.setImageResource(R.drawable.cross);
            view.setTag(1);
            view.setVisibility(View.VISIBLE);
            second.setBackgroundResource(R.drawable.tic_turn);
            first.setBackgroundResource(R.drawable.tictactoe_back);
            firstMove=false;
        }
        else{
            view.setImageResource(R.drawable.zero);
            view.setTag(0);
            view.setVisibility(View.VISIBLE);
            first.setBackgroundResource(R.drawable.tic_turn);
            second.setBackgroundResource(R.drawable.tictactoe_back);
            firstMove=true;
        }
    }

    public int check(int [][]board){

           if(oneImage.getTag()==twoImage.getTag()&&twoImage.getTag()==threeImage.getTag())
               return 1;
           if(fourthImage.getTag()==fifthImage.getTag()&&fifthImage.getTag()==sixImage.getTag())
               return 1;
           if(sevenImage.getTag()==eightImage.getTag()&&eightImage.getTag()==nineImage.getTag())
               return 1;
           if(oneImage.getTag()==fourthImage.getTag()&&fourthImage.getTag()==sevenImage.getTag())
               return 1;
           if(twoImage.getTag()==fifthImage.getTag()&&fifthImage.getTag()==eightImage.getTag())
               return 1;
           if(threeImage.getTag()==sixImage.getTag()&&sixImage.getTag()==nineImage.getTag())
               return 1;

        if(oneImage.getTag()==fifthImage.getTag()&&fifthImage.getTag()==nineImage.getTag())
            return 1;
        if(threeImage.getTag()==fifthImage.getTag()&&fifthImage.getTag()==sevenImage.getTag())
            return 1;




        boolean ok=true;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j]==0)
                {
                    ok=false;
                    break;
                }
            }
            if(!ok)
                break;
        }
        if(ok){
            return 0;
        }
        else{
            return -1;
        }
    }

    public void winCheck(int win,boolean firstMove,String player1,String player2){

        if(win==-1){
            return;
        }

        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.won_tie);
        dialog.setCancelable(false);
        dialog.show();

        TextView afterMatchText=dialog.findViewById(R.id.afterMatchText);
        Button restart=dialog.findViewById(R.id.restart);
        Button exit=dialog.findViewById(R.id.exit);
        if(win==1){
            String name="";
            if(!firstMove){
                name=player1;
            }
            else{
                name=player2;
            }
            afterMatchText.setText("Hooray , "+name+" won the game !");

        }
        if(win==0){
            afterMatchText.setText("Game is tie !");
        }

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}