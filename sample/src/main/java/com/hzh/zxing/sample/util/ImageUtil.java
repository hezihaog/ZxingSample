package com.hzh.zxing.sample.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.hzh.zxing.sample.R;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Package: com.hzh.zxing.sample.util
 * FileName: ImageUtil
 * Date: on 2017/12/28  下午7:58
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */

public class ImageUtil {
    /**
     * 显示圆角图片
     */
    public static void displayCircleAvatar(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context).load(imageUrl).placeholder(R.mipmap.default_avatar).error(R.mipmap.default_avatar).bitmapTransform(new CropCircleTransformation(context)).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    /**
     * 下载图片，转换成Bitmap返回
     */
    public static void loadImage(Context context, String imageUrl, final int reqWidth, final int reqHeight, final LoadCallback callback) {
        Glide.with(context.getApplicationContext()).load(imageUrl).asBitmap().into(new SimpleTarget<Bitmap>(reqWidth, reqHeight) {

            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                if (callback != null) {
                    callback.onLoad(resource);
                }
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                if (callback != null) {
                    callback.onLoadFailed(e);
                }
            }
        });
    }

    public interface LoadCallback {
        /**
         * 加载成功
         *
         * @param bitmap 加载回来的bitmap
         */
        void onLoad(Bitmap bitmap);

        /**
         * 加载失败
         *
         * @param e 异常对象
         */
        void onLoadFailed(Exception e);
    }
}