package com.example.k1dave6412.countdebtv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.k1dave6412.countdebtv1.GameActivity.players;

/**
 * Created by 偉德 on 2017/6/19.
 */

public class rank extends AppCompatActivity {
    ImageView nowPlayer, imageView5, imageView, imageView2, imageView3, imageView4;
    TextView PlayerMsg, textView7, textView6, textView8, textView9, textView10, textView11, textView13, textView12;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rank);

        nowPlayer = (ImageView)findViewById(R.id.nowPlayer);
        imageView5 = (ImageView)findViewById(R.id.imageView5);
        imageView = (ImageView)findViewById(R.id.imageView);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);
        imageView4 = (ImageView)findViewById(R.id.imageView4);

        PlayerMsg = (TextView)findViewById(R.id.PlayerMsg);
        textView7 = (TextView)findViewById(R.id.textView7);
        textView6 = (TextView)findViewById(R.id.textView6);
        textView8 = (TextView)findViewById(R.id.textView8);
        textView9 = (TextView)findViewById(R.id.textView9);
        textView10 = (TextView)findViewById(R.id.textView10);
        textView11 = (TextView)findViewById(R.id.textView11);
        textView13 = (TextView)findViewById(R.id.textView13);
        textView12 = (TextView)findViewById(R.id.textView12);

        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(rank.this, MainActivity.class);
                startActivity(i);
                rank.this.finish();
            }
        });

        for (int i = 0; i <= players.length - 2; i++) {
            for (int j = i + 1; j <= players.length - 1; j++) {
                if (players[i].getScore() < players[j].getScore()) {
                    Player t;
                    t = players[i];
                    players[i] = players[j];
                    players[j] = t;
                }
            }
        }

        textView7.setText(players[0].getName());
        textView6.setText(Integer.toString(players[0].getScore()));
        textView8.setText(players[1].getName());
        textView9.setText(Integer.toString(players[1].getScore()));
        textView10.setText(players[2].getName());
        textView11.setText(Integer.toString(players[2].getScore()));
        textView13.setText(players[3].getName());
        textView12.setText(Integer.toString(players[3].getScore()));

    }
}