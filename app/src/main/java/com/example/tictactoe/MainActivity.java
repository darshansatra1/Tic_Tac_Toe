package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    
    int activePlayer = 0;
    int[] gameState= {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    boolean gameActive = true;
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==-1 && gameActive) {
            gameState[tappedCounter]=activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.zero_marker);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.cross_marker);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(400);

            for(int[] winningPosition:winningPositions ){
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]]==gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]]!=-1
                ){
                    gameActive = false;
                    TextView message = (TextView)findViewById(R.id.winnerMessage);
                    String winner = "Player 2";
                    if(gameState[winningPosition[0]]==0){
                        winner = "Player 1";
                    }
                    message.setText(winner+" has won!");
                    LinearLayout linearLayout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    linearLayout.setVisibility(View.VISIBLE);
                }else{
                    boolean gameIsOver = true;
                    for(int counterState : gameState){
                        if(counterState==-1){
                             gameIsOver = false;
                             break;
                        }
                    }
                    if(gameIsOver){
                        TextView message = (TextView)findViewById(R.id.winnerMessage);
                        message.setText("It's a draw");
                        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.playAgainLayout);
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view){
        gameActive = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        Arrays.fill(gameState,-1);
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}