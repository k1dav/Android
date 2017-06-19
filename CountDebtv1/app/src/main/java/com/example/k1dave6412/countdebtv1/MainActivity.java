package com.example.k1dave6412.countdebtv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.ConnectEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ConnectionRequestListener;


public class MainActivity extends AppCompatActivity {
    Button start, rule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WarpClient.initialize("028787cc650776dc57338c958c98e1db04e83f9f22747ae90f70f350e153bd8b", "2f4a6eef4702d8afb9e78892bc971be38f020b85e4009c5b61b96102da42bfd9");

        start = (Button) findViewById(R.id.start);
        rule = (Button) findViewById(R.id.rule);

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

    }

    /*
    3Defining Listener Callbacks:It is recommended that you should implement request
    listeners which will be called by WarpClient with the result of the requests. WarpClient
     comprises different listeners for different type of requests. Here is a simple implementation
     of connection request listener.

    public abstract class MyConnectionListener implements ConnectionRequestListener {
        @Override
        public void onConnectDone(ConnectEvent event) {
            if (event.getResult() == WarpResponseResultCode.SUCCESS) {
                System.out.println("yipee I have connected");
            }
        }

        @Override
        public void onDisconnectDone(ConnectEvent event) {
            System.out.println("On Disconnected invoked");
        }
    }
    */

    /*
    4Adding Listeners.Once the request listeners are implemented,
    they need to be added to WarpClient instance as follows:
    WarpClient myGame = WarpClient.getInstance();
    myGame.addConnectionRequestListener(new
    MyConnectionListener());
    */

    /*5Connecting to the AppWarp Server:Once you have supplied all the listeners you just need
    to call the connectWithUserName() method of WarpClient to connect to AppWarp server and
    join the Zone by giving in the user name as the parameter with which the client wishes
    to join the online application.

    WarpClient.getInstance().connectWithUserName("Jonathan");
     */

}
