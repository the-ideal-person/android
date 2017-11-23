package com.example.wlb.screenadapter;

import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by wlb on 2017/11/22.
 * Description :
 */

public class ViewCalculateUtil {
    //    获取调用层传入的值进行设置
    public static void setViewLinearLayoutParam(View view, int width, int height, int topMargin,
                                                int bottomMargin, int leftMargin, int rightMargin) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (width != LinearLayout.LayoutParams.MATCH_PARENT && width != LinearLayout.LayoutParams.WRAP_CONTENT) {
            layoutParams.width = (int) UIUtils.getOurInstance(JettApplication.getInstance()).getWidth(width);
        } else {
            layoutParams.width = width;
        }
        if (height != LinearLayout.LayoutParams.MATCH_PARENT && height != LinearLayout.LayoutParams.WRAP_CONTENT) {
            layoutParams.height = (int) UIUtils.getOurInstance(JettApplication.getInstance()).getWidth(height);
        } else {
            layoutParams.height = height;
        }
        layoutParams.topMargin = UIUtils.getOurInstance(JettApplication.getInstance()).getHeight(topMargin);
        layoutParams.bottomMargin = UIUtils.getOurInstance(JettApplication.getInstance()).getHeight(bottomMargin);
        layoutParams.leftMargin = UIUtils.getOurInstance(JettApplication.getInstance()).getWidth(leftMargin);
        layoutParams.rightMargin = UIUtils.getOurInstance(JettApplication.getInstance()).getWidth(rightMargin);
        view.setLayoutParams(layoutParams);
    }
}
