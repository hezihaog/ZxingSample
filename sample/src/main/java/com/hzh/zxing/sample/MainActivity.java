package com.hzh.zxing.sample;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.hzh.zxing.sample.util.FakeUtil;
import com.hzh.zxing.sample.util.ImageUtil;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends AppCompatActivity {
    private ImageView qrCodeIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qrCodeIv = findViewById(R.id.qr_code_iv);
        ImageView avatarIv = findViewById(R.id.avatar_iv);
        //显示头像
        ImageUtil.displayCircleAvatar(this, FakeUtil.getRandomAvatar(1), avatarIv);
        //使用本地resources制作logo
        //Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        //使用网络图片制作logo
        ImageUtil.loadImage(this, FakeUtil.getRandomAvatar(1), 500, 500, new ImageUtil.LoadCallback() {
            @Override
            public void onLoad(Bitmap bitmap) {
                //将logo与二维码合成一张
                Bitmap qrCodeBitmap = EncodingUtils.createQRCode("http://www.baidu.com", 500, 500, bitmap);
                //设置到控件
                qrCodeIv.setImageBitmap(qrCodeBitmap);
            }

            @Override
            public void onLoadFailed(Exception e) {

            }
        });
    }
}