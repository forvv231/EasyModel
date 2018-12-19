package com.next.easymodel.util;

import android.content.Context;
import android.view.WindowManager;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Jue on 2016/3/29.
 * 常用小工具类
 */
public class EasyUtil {

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }


    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeith(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context,float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static void toast(Context context,String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }


    public static int compareTime(String time1) {
        //如果想比较日期则写成"yyyy-MM-dd"就可以了
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //将字符串形式的时间转化为Date类型的时间
        Date a = null;
        try {
            a = sdf.parse(time1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Date类的一个方法，如果a早于b返回true，否则返回false
       /* if(a.before(b))
            return true;
        else
            return false;  */
        int day = (int) ((new Date().getTime() - a.getTime()) / (1000 * 60 * 60 * 24));
        if (day < 1)
            return 1;
        else if (day < 7)
            return 2;
        else
            return 3;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String LongtoDate(String endtime) {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(new Long(endtime) * 1000));
    }

    public static String toDate(String limit) {
        int day = Integer.parseInt(limit);
        if (day > 30) {
            return day / 30 + "个月";
        } else {
            return day + "天";
        }
    }


    /**
     * 将文件转为byte[]
     *
     * @param filePath 文件路径
     * @return
     */
    public static byte[] getBytes(String filePath) {
        File file = new File(filePath);
        ByteArrayOutputStream out = null;
        try {
            FileInputStream in = new FileInputStream(file);
            out = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int i = 0;
            while ((i = in.read(b)) != -1) {

                out.write(b, 0, b.length);
            }
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] s = out.toByteArray();
        return s;

    }
}
