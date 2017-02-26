package com.example.can.testedittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView v= (TextView) findViewById(R.id.tv);
        v.setFocusableInTouchMode(true);
        v.setFocusable(true);
        v.requestFocus();
    }
}
