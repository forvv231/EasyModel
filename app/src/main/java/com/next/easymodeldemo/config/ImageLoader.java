package com.next.easymodeldemo.config;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.next.easymodeldemo.R;
import com.next.easymodeldemo.base.BaseApplication;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by Jue on 2016/11/20.
 */

public class ImageLoader {

    public static int PLACE_HOLDER = R.mipmap.ic_launcher;


    public static void defaultWith(Context context, String imageUrl, ImageView imageView) {
        if (!TextUtils.isEmpty(imageUrl))
            Glide.with(context).load(imageUrl).apply(options).into(imageView);
    }

    public static void defaultWith(Context context, int imageUrl, ImageView imageView) {
        Glide.with(context).load(imageUrl).apply(options).into(imageView);
    }

    public static void headWith(Context context, String imageUrl, ImageView imageView) {
        if (!TextUtils.isEmpty(imageUrl))
            Glide.with(context).load(imageUrl).apply(headOption).into(imageView);
    }

    public static void headWith(Context context, Integer imageUrl, ImageView imageView) {
        Glide.with(context).load(imageUrl).apply(headOption).into(imageView);
    }


    public static void cornerWith(Context context, String imageUrl, ImageView imageView, int radius) {
        Glide.with(context).load(imageUrl).apply(new RequestOptions().bitmapTransform(new RoundedCorners(radius)).dontAnimate().placeholder(PLACE_HOLDER)).into(imageView);
    }

    public static void cornerWith(Context context, int imageUrl, ImageView imageView, int radius) {
        Glide.with(context).load(imageUrl).apply(new RequestOptions().bitmapTransform(new RoundedCorners(radius))).into(imageView);
    }

    public static void cornerWith(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context).load(imageUrl).apply(new RequestOptions().bitmapTransform(new RoundedCorners(10)).dontAnimate().placeholder(PLACE_HOLDER)).into(imageView);
    }

    public static void cornerWith(Context context, int imageUrl, ImageView imageView) {
        Glide.with(context).load(imageUrl).apply(new RequestOptions().bitmapTransform(new RoundedCorners(10)).dontAnimate().placeholder(PLACE_HOLDER)).into(imageView);
    }

    public static RequestOptions options = new RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    public static RequestOptions headOption = new RequestOptions()
            .transform(new CircleCrop())
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .dontAnimate()
            .placeholder(R.mipmap.ic_launcher);


    public static class GlideImageLoader extends com.youth.banner.loader.ImageLoader {
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */

            //Glide 加载图片简单用法
            Glide.with(BaseApplication.getInstance()).load(path).apply(options).into(imageView);
        }
    }
}
