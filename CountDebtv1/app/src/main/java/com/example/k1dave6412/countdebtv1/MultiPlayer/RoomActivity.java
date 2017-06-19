package com.example.k1dave6412.countdebtv1.MultiPlayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.k1dave6412.countdebtv1.R;

public class RoomActivity extends AppCompatActivity {

    Button roomAddBtn ,ranRoomBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room);
        roomAddBtn = (Button) findViewById(R.id.RoomAddButton);
        ranRoomBtn = (Button) findViewById(R.id.RanRoomAddButton);




    }
}
