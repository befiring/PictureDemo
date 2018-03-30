package com.befiring.picturedemo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by WangMeng on 2018/3/29.
 */

public class ImageHelper {

    public static Bitmap handleImageEffect(Bitmap oriBmp, Bitmap bmp, float hue, float saturation, float lum) {
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();

        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
//        hueMatrix.setRotate(1, hue);
//        hueMatrix.setRotate(2, hue);

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(oriBmp, 0, 0, paint);

        return bmp;
    }


    /**
     * 底片效果
     *
     * @param mBitmapSrc 图片源
     * @return Bitmap
     */
    private Bitmap negativeFilm(Bitmap mBitmapSrc) {
        // RGBA的最大值
        final int MAX_VALUE = 255;
        int width = mBitmapSrc.getWidth();
        int height = mBitmapSrc.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        int pixR;
        int pixG;
        int pixB;

        int pixColor;

        int newR;
        int newG;
        int newB;

        int[] pixels = new int[width * height];
        mBitmapSrc.getPixels(pixels, 0, width, 0, 0, width, height);
        int pos = 0;
        for (int i = 1, length = height - 1; i < length; i++) {
            for (int k = 1, len = width - 1; k < len; k++) {
                pos = i * width + k;
                pixColor = pixels[pos];

                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);

                newR = MAX_VALUE - pixR;
                newG = MAX_VALUE - pixG;
                newB = MAX_VALUE - pixB;

                newR = Math.min(MAX_VALUE, Math.max(0, newR));
                newG = Math.min(MAX_VALUE, Math.max(0, newG));
                newB = Math.min(MAX_VALUE, Math.max(0, newB));

                pixels[pos] = Color.argb(MAX_VALUE, newR, newG, newB);
            }
        }

        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    //冰冻效果
    public static Bitmap IceImage(Bitmap bmp) {
        int width = bmp.getWidth();
        int height = bmp.getHeight();

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        int pixColor;
        int r, g, b, r1, g1, b1;
        int[] pixels = new int[width * height];
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);

        for (int i = 0; i < height; i++) {
            for (int k = 0; k < width; k++) {
                //获取前一个像素颜色
                pixColor = pixels[width * i + k];
                r = Color.red(pixColor);
                g = Color.green(pixColor);
                b = Color.blue(pixColor);
                //红色
                r1 = r - g - b;
                r1 = r1 * 3 / 2;

                //绿色
                g1 = g - b - r;
                g1 = g1 * 3 / 2;

                //蓝色
                b1 = b - g - r;
                b1 = b1 * 3 / 2;

                //检查各点像素值是否超出范围
                if (r1 < 0) {
                    r1 = 0;
                } else if (r1 > 255) {
                    r1 = 255;
                }

                if (g1 < 0) {
                    g1 = 0;
                } else if (g1 > 255) {
                    g1 = 255;
                }

                if (b1 < 0) {
                    b1 = 0;
                } else if (b1 > 255) {
                    b1 = 255;
                }

                pixels[width * i + k] = Color.argb(255, r1, g1, b1);
            }
        }
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    /**
     * 浮雕效果
     * 算法原理：(前一个像素点RGB-当前像素点RGB+127)作为当前像素点RGB值
     * 在ABC中计算B点浮雕效果(RGB值在0~255)
     * B.r = C.r - B.r + 127
     * B.g = C.g - B.g + 127
     * B.b = C.b - B.b + 127
     */
    public Bitmap reliefImage(Bitmap mBitmapSrc) {
        int width = mBitmapSrc.getWidth();
        int height = mBitmapSrc.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        int pixColor = 0;
        int pixR = 0;
        int pixG = 0;
        int pixB = 0;
        int newR = 0;
        int newG = 0;
        int newB = 0;
        int[] pixels = new int[width * height];
        mBitmapSrc.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 1; i < height - 1; i++) {
            for (int k = 1; k < width - 1; k++) {
                //获取前一个像素颜色
                pixColor = pixels[width * i + k];
                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);
                //获取当前像素
                pixColor = pixels[(width * i + k) + 1];
                newR = Color.red(pixColor) - pixR + 127;
                newG = Color.green(pixColor) - pixG + 127;
                newB = Color.blue(pixColor) - pixB + 127;
                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));
                pixels[width * i + k] = Color.argb(255, newR, newG, newB);
            }
        }
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

}
