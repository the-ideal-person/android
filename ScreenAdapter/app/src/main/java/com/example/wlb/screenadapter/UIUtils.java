package com.example.wlb.screenadapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by wlb on 2017/11/20.
 * Description :
 * 用来按美工的基准值生成真是设备上的需要的宽高值
 */

public class UIUtils {
    private static UIUtils ourInstance;

    /**
     * @return
     */
    public static UIUtils getOurInstance(Context context) {
        if (ourInstance == null) {
            synchronized (UIUtils.class) {
                if (ourInstance == null) {
                    ourInstance = new UIUtils(context);
                }
            }
        }
        return ourInstance;
    }

    public static final int STANDARD_WIDTH = 1080;
    public static final int STANDARD_HEIGHT = 1872;//减去上面的状态栏，
    private static final String DIMEN_CLASS = "com.android.internal.R$dimen";//如果状态栏不是48，则可反射这个类得到
    //实际设备的分辨率是480 800
    public float displayMetricsWidth;
    public float displayMetricsHeight;

    //初始化
    private UIUtils(Context context) {
        //获取屏幕的真是宽高
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (displayMetricsWidth == 0.0F || displayMetricsHeight == 0.0F) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            //获取到状态栏的高度
            int systemBarHight = getSystemBarHeight(context);
            //处理真是宽高的问题
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {//横
                this.displayMetricsHeight = displayMetrics.widthPixels - systemBarHight;
                this.displayMetricsWidth = displayMetrics.heightPixels;
            } else {//竖
                this.displayMetricsWidth = displayMetrics.widthPixels;
                this.displayMetricsHeight = displayMetrics.heightPixels;
            }
        }
    }

    private int getSystemBarHeight(Context context) {

        return getValue(context, "com.android.internal.R$dimen",
                "system_bar_height", 48);
    }

    /**
     * @param context
     * @param attrGroupClass    安卓源码中找到的存放围度的类
     * @param attrName 状态栏的名字
     * @param defValue            默认值
     * @return
     */
    private int getValue(Context context, String attrGroupClass, String attrName, int defValue) {
        try {
            Class e = Class.forName(attrGroupClass);
            Object object = e.newInstance();
            Field field = e.getField(attrName);
            //获取的事是一个ID
            int x = Integer.parseInt(field.get(object).toString());
            return context.getResources().getDimensionPixelOffset(x);
        } catch (Exception e) {
            return defValue;
        }
    }

    //开始获取缩放后的结果
    public float getWidth(float width) {
        return width * this.displayMetricsWidth / 1080.0F;
    }

    public float getHeight(float height) {
        return height * this.displayMetricsHeight / 1872.0F;
    }

    public int getWidth(int width) {
        return (int) (width * this.displayMetricsWidth / 1080);
    }

    public int getHeight(int height) {
        return (int) (height * this.displayMetricsHeight / 1872);
    }
}
