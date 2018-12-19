package com.next.easymodeldemo.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/6/1.
 */

public class BaseApplication extends Application {

    static BaseApplication instance;


    public static List<Activity> activityList = new ArrayList<Activity>();


    @Override
    public void onCreate() {
        super.onCreate();

        initCallBack();

        instance = this;

        initLogger();

    }

    private void initCallBack() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(final Activity activity, Bundle savedInstanceState) {

                /*ActivityBean bean = new ActivityBean();
                Unbinder unbinder = ButterKnife.bind(activity);
                bean.setUnbinder(unbinder);
                activity.getIntent().putExtra("ActivityBean", bean);

                if (activity.findViewById(R.id.titleBar) != null) {
                    ((EasyTitleBar) activity.findViewById(R.id.titleBar)).getBackLayout().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            activity.onBackPressed();
                        }
                    });
                }*/
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }

        });
    }


 /*   private void initEasyTitleBar() {
        EasyTitleBar.init()
                .backIconRes(R.mipmap.return_icon)
                .backgroud(ContextCompat.getColor(instance, R.color.white))
                .titleSize(18)
                .showLine(true)
                .lineColor(ContextCompat.getColor(instance, R.color.lineColor))
                .menuTextSize(15)
                .titleColor(Color.parseColor("#222222"))
                .menuTextColor(Color.parseColor("#222222"))
                .titleBarHeight(48);
    }*/



    public static BaseApplication getInstance() {
        return instance;
    }

   /* @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this);

    }*/




    private void initLogger() {
        Logger.init("TL")                 // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // default LogLevel.FULL
                .methodOffset(2)// default 0
        /*.logAdapter(new Android())*/; //default AndroidLogAdapter
    }


    public void removeAllActivity() {
        for (int i = 0; i < activityList.size(); i++) {
            if (null != activityList.get(i)) {
                // 关闭存放在activityList集合里面的所有activity
                activityList.get(i).finish();
            }
        }
    }


    /**
     * 获取渠道名
     *
     * @param context 此处习惯性的设置为activity，实际上context就可以
     * @return 如果没有获取成功，那么返回值为空
     */
    public static String getChannelName(Context context) {
        if (context == null) {
            return null;
        }
        String channelName = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                //注意此处为ApplicationInfo 而不是 ActivityInfo,因为友盟设置的meta-data是在application标签中，而不是某activity标签中，所以用ApplicationInfo
                ApplicationInfo applicationInfo = packageManager.
                        getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelName = String.valueOf(applicationInfo.metaData.get("UMENG_CHANNEL"));
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return channelName;
    }

    @TargetApi(9)
    protected void setStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
    }


}
