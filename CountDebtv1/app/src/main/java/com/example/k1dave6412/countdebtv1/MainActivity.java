package com.example.k1dave6412.countdebtv1;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.k1dave6412.countdebtv1.MultiPlayer.RoomActivity;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.ConnectEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ConnectionRequestListener;


public class MainActivity extends AppCompatActivity implements ConnectionRequestListener {
    Button start, rule, multiBtn;
    EditText editText;
    AsyncApp42ServiceApi asyncService;
    MediaPlayer mPlayer;

    @Override
    public void onDisconnectDone(final ConnectEvent arg0) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("ERROR:::", "onDisconnectDone" + arg0.getResult());
            }
        });
    }

    @Override
    public void onInitUDPDone(byte arg0) {
        // TODO Auto-generated method stub
        Log.d("EOOOORO", "onInitUDPDone");
    }

    @Override
    public void onConnectDone(ConnectEvent connectEvent) {
        if (connectEvent.getResult() == WarpResponseResultCode.SUCCESS) {
            Toast.makeText(this, "連線成功！", Toast.LENGTH_SHORT).show();

            this.finish();
            Intent intent = new Intent(this, RoomActivity.class);
            intent.putExtra("User", editText.getText().toString());
            this.startActivity(intent);
        } else {
            Log.d("CONNECT_RESULT:", String.valueOf(connectEvent.getResult()));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);
        rule = (Button) findViewById(R.id.rule);
        multiBtn = (Button) findViewById(R.id.multi);

        asyncService = AsyncApp42ServiceApi.instance();
        asyncService.getMyWarpClient().addConnectionRequestListener(MainActivity.this);
        asyncService.getMyWarpClient().setRecoveryAllowance(10);

        start.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GameActivity.class);
                startActivity(i);
                MainActivity.this.finish();
            }
        });

        rule.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, IntroActivity.class);
                startActivity(i);
            }
        });

        multiBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                final View views = inflater.inflate(R.layout.custom_dialog, null);

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("多人連線…")
                        .setView(views)
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                editText = (EditText) views.findViewById(R.id.IDText);
                                asyncService.getMyWarpClient().connectWithUserName( editText.getText().toString());
                                ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "", "signing in..");
                            }
                        })
                        .show();
            }
        });

        try
        {
            mPlayer = MediaPlayer.create(this, R.raw.music);
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.setLooping(true);
            mPlayer.start();
        }catch (IllegalStateException e)
        {
            e.printStackTrace();
        }
    }
}
