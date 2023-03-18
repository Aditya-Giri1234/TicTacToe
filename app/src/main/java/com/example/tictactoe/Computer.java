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
     int [][] board=new int[3][3];
    Cell computerMove;
    TextView secondPlayer;

    int empty[]=new int[9];



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
        board[i][j]=1;
        empty[i*3+j]=1;
        if(space(empty)<=5)
        winCheck();


        //First Time

        if(space(empty)==8) {
           first();
        }

         else {
            // now choose best move for computer

            minmax(0,computer);
            System.out.println(computerMove==null);
            place(computerMove.row, computerMove.col);

            if(space(empty)<=5)
            winCheck();

        }
        
    }

    private  int minmax( int depth,String player) {
       if(computerWon())  return 10;
       if(humanWon()) return -10;
       if(space(empty)==0) return 0;

//        System.out.println(empty);

        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;

        for(int i=0;i<empty.length;i++){
            if(empty[i]==0) {
                Cell cell = new Cell(i / 3, i% 3);

                if (player.equalsIgnoreCase(computer)) {
                    board[cell.row][cell.col] = -1;
                    empty[cell.row * 3 + cell.col] = 1;
                    int curentScore = minmax(depth + 1, human);

                    max = Math.max(max, curentScore);
                    if (curentScore >= 0) {

                        if (depth == 0)
                            computerMove = cell;
                    }
                    if(curentScore<0){
                        if(depth==0){
                            computerMove=cell;
                        }
                    }
                    if (curentScore == 1) {
                        board[cell.row][cell.col] = 0;
                        empty[cell.row * 3 + cell.col] = 0;
                        break;
                    }

                } else {
                    board[cell.row][cell.col] = 1;
                    empty[cell.row * 3 + cell.col] = 1;
                    int currentScore = minmax(depth + 1, computer);
                    min = Math.min(min, currentScore);

                    if (currentScore == -1) {
                        board[cell.row][cell.col] = 0;
                        empty[cell.row * 3 + cell.col] = 0;
                        break;
                    }
                }

                board[cell.row][cell.col] = 0;
                empty[cell.row * 3 + cell.col] = 0;
            }
        }

        return player.equals(computer)?(max-depth):(min+depth);


    }

    private ArrayList<Integer> findSpace(int[] empty) {
        ArrayList<Integer> temp=new ArrayList<>();
        for(int i=0;i< 9;i++){
            if(empty[i]==0){
                temp.add(i);
            }
        }

        return  temp;
    }

    public  boolean computerWon(){

        if(board[0][0]==board[0][1]&&board[0][1]==board[0][2]&&board[0][0]==-1)
            return true;
        if(board[1][0]==board[1][1]&&board[1][1]==board[1][2]&&board[1][0]==-1)
            return true;
        if(board[2][0]==board[2][1]&&board[2][1]==board[2][2]&&board[2][0]==-1)
            return true;
        if(board[0][0]==board[1][0]&&board[1][0]==board[2][0]&&board[0][0]==-1)
            return true;
        if(board[0][1]==board[1][1]&&board[1][1]==board[2][1]&&board[0][1]==-1)
            return true;
        if(board[0][2]==board[1][2]&&board[1][2]==board[2][2]&&board[0][2]==-1)
            return true;
        if(board[0][0]==board[1][1]&&board[1][1]==board[2][2]&&board[0][0]==-1)
            return true;
        if(board[0][2]==board[1][1]&&board[1][1]==board[2][0]&&board[0][2]==-1)
            return true;

        return false;

    }

    public  boolean humanWon(){

        if(board[0][0]==board[0][1]&&board[0][1]==board[0][2]&&board[0][0]==1)
            return true;
        if(board[1][0]==board[1][1]&&board[1][1]==board[1][2]&&board[1][0]==1)
            return true;
        if(board[2][0]==board[2][1]&&board[2][1]==board[2][2]&&board[2][0]==1)
            return true;
        if(board[0][0]==board[1][0]&&board[1][0]==board[2][0]&&board[0][0]==1)
            return true;
        if(board[0][1]==board[1][1]&&board[1][1]==board[2][1]&&board[0][1]==1)
            return true;
        if(board[0][2]==board[1][2]&&board[1][2]==board[2][2]&&board[0][2]==1)
            return true;
        if(board[0][0]==board[1][1]&&board[1][1]==board[2][2]&&board[0][0]==1)
            return true;
        if(board[0][2]==board[1][1]&&board[1][1]==board[2][0]&&board[0][2]==1)
            return true;

            return false;

    }

    public void winCheck(){

        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.won_tie);
        dialog.setCancelable(false);


        TextView afterMatchText=dialog.findViewById(R.id.afterMatchText);
        Button restart=dialog.findViewById(R.id.restart);
        Button exit=dialog.findViewById(R.id.exit);
        if(humanWon()){

            afterMatchText.setText("Hooray , Human won the game !");
            dialog.show();

        }

        if(computerWon()){
            afterMatchText.setText("Hooray , Computer won the game !");
            dialog.show();
        }

        if(space(empty)==0){
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



    public void first(){
        int value=new Random().nextInt(8);

        while(value==position(board)){
            value=new Random().nextInt(8);

        }

        place(value/3,value%3);
        Toast tost=Toast.makeText(getApplicationContext(),Integer.toString(value),Toast.LENGTH_SHORT);
        tost.setGravity(Gravity.CENTER,0,0);
        tost.show();

    }

    public int position(int board[][]){
        int pos=-1;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j]==1) {
                    pos = (i * 3 + j);
                    break;
                }
            }
            if(pos!=-1)
                break;
        }
        return pos;
    }

    public int space(int arr[]){
        int sum=0;
        for(int i=0;i<9;i++)
        {
            if(arr[i]==0)
                sum++;
        }
        return sum;
    }

    public  void place(int i,int j){
        Toast.makeText(this, "i -> "+i+" , j-> "+j, Toast.LENGTH_SHORT).show();
        board[i][j]=-1;
        empty[i*3+j]=1;
        switch (i*3+j+1){
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

class Cell{
    public int row;
    public int col;

    public Cell(int row,int col){
        this.row=row;
        this.col=col;
    }
}

