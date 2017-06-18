package com.example.k1dave6412.countdebtv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Button back = (Button)findViewById(R.id.back);
        TextView Text = (TextView)findViewById(R.id.scrollText);
        Text.setMovementMethod(ScrollingMovementMethod.getInstance());

        back.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroActivity.this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                IntroActivity.this.finish();
            }
        });
    }
}
