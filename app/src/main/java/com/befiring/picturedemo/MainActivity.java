package com.befiring.picturedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by WangMeng on 2018/3/29.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_matrix).setOnClickListener(this);
        findViewById(R.id.btn_api).setOnClickListener(this);
        findViewById(R.id.btn_pixel).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_matrix:
                startActivity(new Intent(this,FirstActivity.class));
                break;
            case R.id.btn_api:
                startActivity(new Intent(this,SecondActivity.class));
                break;
            case R.id.btn_pixel:
                startActivity(new Intent(this,PixelActivity.class));
                break;
        }
    }
}
