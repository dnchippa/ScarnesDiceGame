package com.dhanraj.c.scarnesdicegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;



public class MainActivity extends AppCompatActivity {
 int userScore=0,userScoreFinal=0,computerScore=0,computerScoreFinal=0,roll=0,FinalScore=100;
    Button rollbtn,holdbtn,resetbtn;
    TextView textViewUser,textViewComp,diceScore;
    ImageView image;
    Handler timerHandler = new Handler();
    Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rollbtn=(Button) findViewById(R.id.rollBtn);
        holdbtn=(Button) findViewById(R.id.holdBtn);
        resetbtn=(Button) findViewById(R.id.resetBtn);
        textViewUser = (TextView) findViewById(R.id.textUserScore);
        textViewComp = (TextView) findViewById(R.id.textCompScore);
        image = (ImageView) findViewById(R.id.imageView);
        diceScore=(TextView) findViewById(R.id.DiceS);
        rollbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("RollOnClick","Roll Click Entered..");

                RollDice();
                if(roll!=1){
                userScore+=roll;
                }
                else {
                    userScore=0;
                    holdDice();
                    ComputerPlays();
                }
            }
        });

        holdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("HoldOnClick","Roll Click Entered..");

                holdDice();

            }
        });

        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetDice();
            }
        });

    }


    private void resetDice() {
        computerScore=0;
        computerScoreFinal=0;
        userScoreFinal=0;
        userScore=0;
        roll=0;

        textViewUser.setText("0");
        textViewComp.setText("0");
        image.setImageResource(R.drawable.dice1);
        DiceScoreUpdate();
         rollbtn.setEnabled(true);
         holdbtn.setEnabled(true);

    }


    private void holdDice() {
        Log.i("HoldDice","Roll Entered..");

        {
            userScoreFinal+=userScore;
            textViewUser.setText(String.valueOf(userScoreFinal));
            userScore=0;
        }
        Log.i("HoldDice","Roll Entered..");
        if(userScoreFinal>=FinalScore){

            Toast.makeText(getApplicationContext(),"Congratulations You Won!",Toast.LENGTH_LONG).show();
            rollbtn.setEnabled(false);
            holdbtn.setEnabled(false);
        }
        else
            ComputerPlays();
    }

    private void RollDice() {
        int no = r.nextInt(6) + 1;

        if(no==1) {

            image.setImageResource(R.drawable.dice1);
        }
        if(no==2) {
            image.setImageResource(R.drawable.dice2);
        }
        if(no==3) {
            image.setImageResource(R.drawable.dice3);
        }
        if(no==4) {
            image.setImageResource(R.drawable.dice4);
        }
        if(no==5) {
            image.setImageResource(R.drawable.dice5);
        }
        if(no==6) {
            image.setImageResource(R.drawable.dice6);
        }
        roll=no;
        DiceScoreUpdate();
        Log.i("RollDice","Roll Finished..");

    }


    void DiceScoreUpdate(){
        diceScore.setText(String.valueOf(roll));

    }


Runnable runnablefun=new Runnable() {
    @Override
    public void run() {
        RollDice();

        if (roll != 1) {
            computerScore += roll;
            if(r.nextBoolean())
            timerHandler.postDelayed(this,500);
            else {

                computerScoreFinal += computerScore;
                textViewComp.setText(String.valueOf(computerScoreFinal));
                computerScore = 0;

                if(computerScoreFinal>=FinalScore){

                   Toast.makeText(getApplicationContext(),"Computer Wins!",Toast.LENGTH_LONG).show();
                   rollbtn.setEnabled(false);
                   holdbtn.setEnabled(false);
                }
                else
                    {
                    rollbtn.setEnabled(true);
                    holdbtn.setEnabled(true);
                }

            }
        } else {
            computerScore = 0;

            computerScoreFinal += computerScore;
            textViewComp.setText(String.valueOf(computerScoreFinal));
            computerScore = 0;
            rollbtn.setEnabled(true);
            holdbtn.setEnabled(true);


        }


    }
};


    void ComputerPlays() {

        rollbtn.setEnabled(false);
        holdbtn.setEnabled(false);
                timerHandler.postDelayed(runnablefun, 500);




    }
}