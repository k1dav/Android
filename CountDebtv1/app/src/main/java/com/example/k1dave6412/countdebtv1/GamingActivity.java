package com.example.k1dave6412.countdebtv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.k1dave6412.countdebtv1.GameActivity.host_id;
import static com.example.k1dave6412.countdebtv1.GameActivity.players;

public class GamingActivity extends AppCompatActivity {

    Button returnButton, passButton, addButton, nextPlayerButton;
    ImageView nowPlayers;
    TextView playerMs, nowNumber;
    int order_id = 0;
    int direction = 1, nnum = 0, btn_count = 0, ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaming);

        nowPlayers = (ImageView) findViewById(R.id.nowPlayer);
        playerMs = (TextView) findViewById(R.id.PlayerMsg);
        nowNumber = (TextView) findViewById(R.id.nowNum);
        returnButton = (Button) findViewById(R.id.ReturnBtn);
        passButton = (Button) findViewById(R.id.PassBtn);
        addButton = (Button) findViewById(R.id.addNumBtn);
        nextPlayerButton = (Button) findViewById(R.id.nextBtn);


        Bundle bundle = getIntent().getExtras();
        ans = bundle.getInt("ans");
        order_id = host_id;

        nowNumber.setText(String.valueOf(nnum));
        playerMs.setText(getResources().getString(R.string.choseMsg, players[order_id].getName()));
        choose_player_pic(order_id);
        buttonTimesCheck();

        addButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nextPlayerButton.getVisibility() == View.INVISIBLE) {
                    nextPlayerButton.setVisibility(View.VISIBLE);
                }
                btn_count += 1;
                nnum += 1;
                nowNumber.setText(String.valueOf(nnum));

                if (nnum == ans) {
                    addButton.setVisibility(View.INVISIBLE);
                    passButton.setVisibility(View.INVISIBLE);
                    returnButton.setVisibility(View.INVISIBLE);
                    nextPlayerButton.setVisibility(View.INVISIBLE);

                    String toastMsg = players[order_id].getName() + "，你輸了！遊戲即將重新開始！";
                    Toast.makeText(GamingActivity.this, toastMsg, Toast.LENGTH_LONG).show();
                    host_id += 1;
                    btn_count = 0;

                    Intent i = new Intent(GamingActivity.this, GameActivity.class);
                    startActivity(i);
                    GamingActivity.this.finish();

                }
                if (btn_count == 3) {
                    addButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        nextPlayerButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPlayer();
            }
        });

        returnButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                direction *= -1;
                players[order_id].useReturn();
                nextPlayer();
            }
        });

        passButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                players[order_id].usePass();
                nextPlayer();
            }
        });
    }

    public void choose_player_pic(int id) {
        switch (id) {
            case 0:
                nowPlayers.setImageResource(R.drawable.playericon);
                break;
            case 1:
                nowPlayers.setImageResource(R.drawable.player2icon);
                break;
            case 2:
                nowPlayers.setImageResource(R.drawable.player3icon);
                break;
            case 3:
                nowPlayers.setImageResource(R.drawable.player4icon);
                break;
        }
    }

    public void buttonTimesCheck() {
        if (players[order_id].getReturnTimes() > 0) {
            returnButton.setVisibility(View.VISIBLE);
        } else {
            returnButton.setVisibility(View.INVISIBLE);
        }

        if (players[order_id].getPassTimes() > 0) {
            passButton.setVisibility(View.VISIBLE);
        } else {
            passButton.setVisibility(View.INVISIBLE);
        }
    }

    public void nextPlayer() {
        order_id = (order_id + direction) % players.length;
        if (order_id < 0) {
            order_id += players.length;
        }
        buttonTimesCheck();

        playerMs.setText(getResources().getString(R.string.choseMsg, players[order_id].getName()));
        choose_player_pic(order_id);
        addButton.setVisibility(View.VISIBLE);
        btn_count = 0;
        nextPlayerButton.setVisibility(View.INVISIBLE);
    }

}
