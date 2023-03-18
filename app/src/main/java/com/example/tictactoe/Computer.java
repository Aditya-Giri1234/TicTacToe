package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Computer extends AppCompatActivity {

    public static final String human="HUMAN";
    public static final String computer="COMPUTER";
    LinearLayout first,second,one,two,three,fourth,fifth,six,seven,eight,nine;
    ImageView oneImage,twoImage,threeImage,fourthImage,fifthImage,sixImage,sevenImage,eightImage,nineImage;

    TextView secondPlayer;

    int gameState[]=new int[]{2,2,2,2,2,2,2,2,2};


    int[][] Winpos = new int[][]{{0,1,2},{3,4,5},{6,7,8},
            {0,4,8},{2,4,6},{0,3,6},
            {1,4,7},{2,5,8}};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }
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




        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move(view.findViewById(R.id.oneImage),0,0);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move(view.findViewById(R.id.twoImage),0,1);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move(view.findViewById(R.id.threeImage),0,2);
            }
        });
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move(view.findViewById(R.id.fourthImage),1,0);
            }
        });
        fifth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                move(view.findViewById(R.id.fifthImage),1,1);
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               move(view.findViewById(R.id.sixImage),1,2);
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move(view.findViewById(R.id.sevenImage),2,0);
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               move(view.findViewById(R.id.eightImage),2,1);
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move(view.findViewById(R.id.nineImage),2,2);
            }
        });


    }

    public  void move(ImageView view,int i,int j)  {

        view.setImageResource(R.drawable.cross);
        view.setVisibility(View.VISIBLE);

        gameState[i*3+j]=1;
        if(space(gameState)<=5)
        winCheck();


        //First Time

        if(space(gameState)==8) {
           first();
        }

         else {
            // now choose best move for computer

            bestMove();

            if(space(gameState)<=5)
            winCheck();

        }
        
    }
    public void bestMove(){
        int bestScore = Integer.MIN_VALUE;
        int move=-1;
        for(int i =0;i<gameState.length;i++){
            if(gameState[i]==2){
                gameState[i] =0 ;
                int score = minmax(0,1);
                gameState[i] = 2;
                if(score>bestScore){
                    bestScore = score;
                   move=i;
                }
            }
        }
        System.out.println(move);
        place(move+1);
        gameState[move+1]=0;
    }
    private  int minmax( int depth,int player) {
        int result = checkWin(depth);
        if(result!=0){
            return result;
        }

        if(space(gameState)==0) return 0;


        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;

                if (player==0) {
                    for (int i = 0; i < gameState.length; i++) {
                        if (gameState[i] == 2) {
                            gameState[i] = player;
                            int curentScore = minmax(depth + 1, 1);
                            gameState[i] = 2;

                            max = Math.max(max, curentScore);

                        }
                    }
                    return max;
                }
               else {
                  for(int i=0;i<9;i++) {
                      if(gameState[i]==2) {
                          gameState[i] = player;
                          int currentScore = minmax(depth + 1, 0);
                          gameState[i]=2;
                          min = Math.min(min, currentScore);
                      }
                  }
                  return min;

            }

    }







    public void winCheck(){

        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.won_tie);
        dialog.setCancelable(false);


        TextView afterMatchText=dialog.findViewById(R.id.afterMatchText);
        Button restart=dialog.findViewById(R.id.restart);
        Button exit=dialog.findViewById(R.id.exit);
        if(win()==1){

            afterMatchText.setText("Hooray , Human won the game !");
            dialog.show();

        }

        if(win()==-1){
            afterMatchText.setText("Hooray , Computer won the game !");
            dialog.show();
        }

        if(space(gameState)==0){
            afterMatchText.setText("Game is tie !");
            dialog.show();
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

    public int checkWin(int depth){
        for(int i=0;i<Winpos.length;i++){

            if( gameState[Winpos[i][0]]!=2 && gameState[Winpos[i][0]]==gameState[Winpos[i][1]] &&  gameState[Winpos[i][1]] == gameState[Winpos[i][2]]){

                if(gameState[Winpos[i][0]]==1) {
                    return 10-depth;
                }else{
                    return depth-10;
                }

            }



        }
        return 0;
    }

    public int win(){
        for(int i=0;i<Winpos.length;i++){

            if( gameState[Winpos[i][0]]!=2 && gameState[Winpos[i][0]]==gameState[Winpos[i][1]] &&  gameState[Winpos[i][1]] == gameState[Winpos[i][2]]){

                if(gameState[Winpos[i][0]]==1) {
                    return 1;
                }else{
                    return -1;
                }

            }



        }
        return 0;
    }



    public void first(){
        int value=new Random().nextInt(8);

        while(value==position(gameState)){
            value=new Random().nextInt(8);

        }

        place(value+1);
        Toast tost=Toast.makeText(getApplicationContext(),Integer.toString(value),Toast.LENGTH_SHORT);
        tost.setGravity(Gravity.CENTER,0,0);
        tost.show();

    }

    public int position(int gameState[]){
        int pos=-1;
        for(int i=0;i<9;i++){

                if(gameState[i]==1) {
                    pos = i;
                    break;
                }

        }
        return pos;
    }

    public int space(int arr[]){
        int sum=0;
        for(int i=0;i<9;i++)
        {
            if(arr[i]==2)
                sum++;
        }
        return sum;
    }

    public  void place(int i){

        switch (i){
            case 1:
                oneImage.setImageResource(R.drawable.zero);
                oneImage.setVisibility(View.VISIBLE);
                break;
            case 2:
                twoImage.setImageResource(R.drawable.zero);
                twoImage.setVisibility(View.VISIBLE);
                break;
            case 3:
                threeImage.setImageResource(R.drawable.zero);
                threeImage.setVisibility(View.VISIBLE);
                break;
            case 4:
                fourthImage.setImageResource(R.drawable.zero);
               fourthImage.setVisibility(View.VISIBLE);
                break;
            case 5:
                fifthImage.setImageResource(R.drawable.zero);
               fifthImage.setVisibility(View.VISIBLE);
                break;
            case 6:
                sixImage.setImageResource(R.drawable.zero);
              sixImage.setVisibility(View.VISIBLE);
                break;
            case 7:
                sevenImage.setImageResource(R.drawable.zero);
              sevenImage.setVisibility(View.VISIBLE);
                break;
            case 8:
                eightImage.setImageResource(R.drawable.zero);
               eightImage.setVisibility(View.VISIBLE);
                break;
            case 9:
                nineImage.setImageResource(R.drawable.zero);
               nineImage.setVisibility(View.VISIBLE);
                break;


        }
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}



