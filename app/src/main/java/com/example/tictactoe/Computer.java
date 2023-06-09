package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class Computer extends AppCompatActivity {

    public static final String human="HUMAN";
    public static final String computer="COMPUTER";
    LinearLayout first,second,one,two,three,fourth,fifth,six,seven,eight,nine;
    ImageView oneImage,twoImage,threeImage,fourthImage,fifthImage,sixImage,sevenImage,eightImage,nineImage;

    TextView firstPlayer,secondPlayer;
    EditText user;

    int gameState[]=new int[]{2,2,2,2,2,2,2,2,2}; // 0==computer , 1=human


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


        firstPlayer=findViewById(R.id.firstPlayer);
        secondPlayer=findViewById(R.id.secondPlayer);
        secondPlayer.setText("Computer");
        Dialog choose=new Dialog(this);
        choose.setContentView(R.layout.computer_pop);
        choose.setCancelable(false);
        Button submit=choose.findViewById(R.id.computer_pop_button);
        user=choose.findViewById(R.id.user);
        choose.show();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user.getText().length() == 0) {
                    user.setError("Enter a name");
                }
                    else {

                        firstPlayer.setText(user.getText().toString());
                        choose.dismiss();
                    }

            }
        });
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
                if (oneImage.getVisibility() != View.VISIBLE) {
                    move(view.findViewById(R.id.oneImage), 0);
                }
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (twoImage.getVisibility() != View.VISIBLE) {
                    move(view.findViewById(R.id.twoImage),  1);
                }
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (threeImage.getVisibility() != View.VISIBLE) {
                    move(view.findViewById(R.id.threeImage),  2);
                }
            }
        });
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fourthImage.getVisibility() != View.VISIBLE) {
                    move(view.findViewById(R.id.fourthImage), 3);
                }
            }
        });
        fifth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (fifthImage.getVisibility() != View.VISIBLE) {
                    move(view.findViewById(R.id.fifthImage), 4);
                }
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sixImage.getVisibility() != View.VISIBLE) {
                    move(view.findViewById(R.id.sixImage), 5);
                }
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sevenImage.getVisibility() != View.VISIBLE) {
                    move(view.findViewById(R.id.sevenImage), 6);
                }
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (eightImage.getVisibility() != View.VISIBLE) {
                    move(view.findViewById(R.id.eightImage), 7);
                }
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nineImage.getVisibility() != View.VISIBLE) {
                    move(view.findViewById(R.id.nineImage), 8);
                }
            }
        });


    }

    public  void move(ImageView view,int index)  {

        view.setImageResource(R.drawable.cross);
        view.setVisibility(View.VISIBLE);

        gameState[index]=1;
        if(space(gameState)<=5)
        winCheck();


        //First Time
        if(space(gameState)!=0) {

                bestMove();

                if (space(gameState) <= 5)
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
                System.out.println("i -> "+i+" , move -> "+move+" , Score -> "+score+" , bestScore -> "+bestScore+" , score>bestScore -> "+(score>bestScore));
                if(score>bestScore){
                    bestScore = score;
                    move=i;
                }
            }
        }
        System.out.println(" move -> "+move);
        place(move+1);
        gameState[move]=0;
    }
    private  int minmax( int depth,int player) {
        int result = checkWin(depth);
        if(result!=0){
            return result;
        }

        if(space(gameState)==0) return 0;




                if (player==0) {
                    int max=Integer.MIN_VALUE;
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
                    int min=Integer.MAX_VALUE;
                    for(int i=0;i<gameState.length;i++) {
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
        switch (win()){
            case 1:
                if(user.length()>6)
                    afterMatchText.setText("Hooray , "+user.getText().toString().substring(0,6)+" won the game !");
                else
                    afterMatchText.setText("Hooray , "+user.getText()+" won the game !");
                dialog.show();
                break;
            case -1:
                afterMatchText.setText("Hooray , Computer won the game !");
                dialog.show();
                break;


        }


        if(space(gameState)==0){
            afterMatchText.setText("Game is tie !");
            dialog.show();
        }

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent = getIntent();
                finish();
                MainActivity.main.finish();
                startActivity(intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
            }
        });


    }

    public int checkWin(int depth){
        for(int i=0;i<Winpos.length;i++){

            if( gameState[Winpos[i][0]]!=2 && gameState[Winpos[i][0]]==gameState[Winpos[i][1]] &&  gameState[Winpos[i][1]] == gameState[Winpos[i][2]]){

                if(gameState[Winpos[i][0]]==0) {
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
        MainActivity.main.finish();
    }
}



