package com.example.administrator.douyinhuadong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.douyinhuadong.activity.DouyinActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mStartDouyinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStartDouyinButton  = findViewById(R.id.bt_start_douyin);
        mStartDouyinButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mStartDouyinButton){
            startActivity(new Intent(this,DouyinActivity.class));
        }
    }
}
