package com.glumes.androidimageprocess.imagecolor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.glumes.androidimageprocess.R;
import com.glumes.androidimageprocess.util.ImageHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageColorActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.seekBar)     // 改变色调
    SeekBar seekBar;
    @BindView(R.id.seekBar2)    // 改变饱和度
    SeekBar seekBar2;
    @BindView(R.id.seekBar3)    // 改变亮度
    SeekBar seekBar3;

    private static final int MAX_VALUE = 255 ;
    private static final int MID_VALUE = 127 ;

    private Bitmap bitmap ;

    private float mHue , mStauration , mLum ; // 色调、饱和度、亮度 三种属性的变化

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_color);
        ButterKnife.bind(this);


        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.test3);

        image.setImageBitmap(bitmap);

        seekBar.setMax(MAX_VALUE);
        seekBar2.setMax(MAX_VALUE);
        seekBar3.setMax(MAX_VALUE);

        seekBar.setProgress(MID_VALUE);
        seekBar2.setProgress(MID_VALUE);
        seekBar3.setProgress(MID_VALUE);

        seekBar.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);
        seekBar3.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.seekBar:
                // 色调的改变
                mHue = (progress - MID_VALUE) * 1.0f / MID_VALUE * 180 ;
                break;
            case R.id.seekBar2:
                // 饱和度的改变
                mStauration = progress * 1.0f / MID_VALUE ;
                break;
            case R.id.seekBar3:
                // 亮度的改变
                mLum = progress * 1.0f / MID_VALUE ;
                break;
            default:
                break;
        }

        image.setImageBitmap(ImageHelper.handleImageEffect(
                bitmap, mHue,mStauration,mLum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
