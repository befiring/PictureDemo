package com.befiring.picturedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by WangMeng on 2018/3/30.
 */

public class PixelActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView imageView;
    private Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixel);
        imageView = findViewById(R.id.image);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.person);
        imageView.setImageBitmap(bitmap);

        findViewById(R.id.huidu).setOnClickListener(this);
        findViewById(R.id.huaijiu).setOnClickListener(this);
        findViewById(R.id.dipian).setOnClickListener(this);
        findViewById(R.id.bingdong).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.huidu:
                imageView.setImageBitmap(ImageHelper.bitmap2Gray2(bitmap));
                break;
            case R.id.huaijiu:
                imageView.setImageBitmap(ImageHelper.oldImage(bitmap));
                break;
            case R.id.dipian:
                imageView.setImageBitmap(ImageHelper.negativeFilm(bitmap));
                break;
            case R.id.bingdong:
                imageView.setImageBitmap(ImageHelper.reliefImage(bitmap));
                break;
            default:
                break;
        }
    }
}
