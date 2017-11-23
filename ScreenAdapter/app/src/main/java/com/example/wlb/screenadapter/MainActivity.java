package com.example.wlb.screenadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView one = findViewById(R.id.textView);
        TextView two = findViewById(R.id.textView2);
        ViewCalculateUtil.setViewLinearLayoutParam(one,1040,100,10,10,10,10);
        ViewCalculateUtil.setViewLinearLayoutParam(two,100,200,30,10,10,10);

    }
}
