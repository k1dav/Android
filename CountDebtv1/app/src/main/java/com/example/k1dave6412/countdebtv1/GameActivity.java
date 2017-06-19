package com.example.k1dave6412.countdebtv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    EditText inputNum;
    Button confirmButton;
    ImageView hostPlayer;
    TextView choseMs;
    static int host_id = 0;
    int ans;
    public static Player[] players = new Player[4];
    public static boolean endGame = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        for (int i = 0; i < players.length; i++) {
            if (host_id == 0) {
                String name = "Player " + (i + 1);
                players[i] = new Player(name);
            } else {
                players[i].initTimes();
            }
        }

        setContentView(R.layout.activity_game);

        if (host_id == players.length) {
            Random ran = new Random();
            host_id = ran.nextInt(4);
            endGame = true;
        }

        choseMs = (TextView) findViewById(R.id.choseMsg);
        choseMs.setText(getResources().getString(R.string.choseMsg, players[host_id].getName()));
        inputNum = (EditText) findViewById(R.id.numberSetText);
        confirmButton = (Button) findViewById(R.id.numberSetButton);
        hostPlayer = (ImageView) findViewById(R.id.playerIcon);
        choose_host_pic(host_id);


        confirmButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans = Integer.parseInt(inputNum.getText().toString());

                Intent i = new Intent(GameActivity.this, GamingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ans", ans);
                i.putExtras(bundle);

                startActivity(i);
                GameActivity.this.finish();

            }
        });
    }

    public void choose_host_pic(int id) {
        switch (id) {
            case 0:
                hostPlayer.setImageResource(R.drawable.playericon);
                break;
            case 1:
                hostPlayer.setImageResource(R.drawable.player2icon);
                break;
            case 2:
                hostPlayer.setImageResource(R.drawable.player3icon);
                break;
            case 3:
                hostPlayer.setImageResource(R.drawable.player4icon);
                break;
        }
    }
}
