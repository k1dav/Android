package com.example.k1dave6412.countdebtv1;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
    String id;
    EditText editText;
    WarpClient myGame;
    //AsyncApp42ServiceApi asyncService;


    public void onStart() {
        super.onStart();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

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
            intent.putExtra("User", id);
            this.startActivity(intent);
        } else {
            Log.d("ERRORRRRR1223:", String.valueOf(connectEvent.getResult()));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WarpClient.initialize("028787cc650776dc57338c958c98e1db04e83f9f22747ae90f70f350e153bd8b", "2f4a6eef4702d8afb9e78892bc971be38f020b85e4009c5b61b96102da42bfd9");

        start = (Button) findViewById(R.id.start);
        rule = (Button) findViewById(R.id.rule);
        multiBtn = (Button) findViewById(R.id.multi);
        try{
            myGame = WarpClient.getInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        myGame.addConnectionRequestListener(this);


        /*
        asyncService = AsyncApp42ServiceApi.instance();
        asyncService.getMyWarpClient().addConnectionRequestListener(MainActivity.this);*/

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


                                //asyncService.getMyWarpClient().connectWithUserName(editText.getText().toString());
                                myGame.connectWithUserName(editText.getText().toString());
                                ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "", "signing in..");
                            }
                        })
                        .show();
            }
        });
    }
}
