package com.glumes.androidimageprocess.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by zhaoying on 2017/3/27.
 */

public class ImageHelper {

    /**
     * 设置图像矩阵
     * @param bitmap
     * @param hue   色调
     * @param saturation    饱和度
     * @param lum   亮度
     * @return
     */
    public static Bitmap handleImageEffect(Bitmap bitmap ,float hue ,float saturation , float lum){

        // 安卓中不允许修改原图，必须通过原图创建一个同样大小的 Bitmap，并将原图绘制到该 Bitmap 中，以一个副本的形式来修改图像
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();

        // 设置色度，0、1、2 分别代表 Red、Green、Blue 三种颜色的处理，第二个参数是需要处理的值
        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0,hue);
        hueMatrix.setRotate(1,hue);
        hueMatrix.setRotate(2,hue);

        // 饱和度设置，参数即代表颜色饱和度的值，当饱和度为 0 时，图像就变成了灰度图像了
        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        // 亮度设置，当亮度为 0 时，图像就变味全黑了
        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum,lum,lum,1);

        // 封装了矩阵乘法运算，postConcat 方法来将矩阵的作用效果混合，从而叠加处理效果
        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));

        // 将原图绘制到创建的副本 Bitmap 中去
        canvas.drawBitmap(bitmap,0,0,paint);

        return bmp ;
    }
}
