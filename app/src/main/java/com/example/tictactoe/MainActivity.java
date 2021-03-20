package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    
    int activePlayer = 0;
    int[] gameState= {-1,-1,-1,-1,-1,-1,-1,-1,- 1};


    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==-1) {
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
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}