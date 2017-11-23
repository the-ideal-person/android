package com.example.precentscreen;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by wlb on 2017/11/22.
 * Description :
 */

public class PrecentRelativeLayout extends RelativeLayout {

    public PrecentRelativeLayout(Context context) {
        super(context);
    }

    public PrecentRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PrecentRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PrecentRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        得到当前布局控件的宽高
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        int height = View.MeasureSpec.getSize(heightMeasureSpec);

        //测量出子控件的宽高进行改变
        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
            ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
//            把已经得到的布局参数进行更改
            float widthPrecent = 0;
            float heightPrecent = 0;
            if (layoutParams instanceof PrecentRelativeLayout.LayoutParams) {
//                获取到布局文件上的内容
                widthPrecent = ((LayoutParams) layoutParams).getWidthPrecent();
                heightPrecent = ((LayoutParams) layoutParams).getHeightPrecent();

            }
            //设置ziview 的布局
            if (widthPrecent > 0) {
                layoutParams.width = (int) (width * widthPrecent);
            }
            if (heightPrecent > 0) {
                layoutParams.height = (int) (height * heightPrecent);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    /**
     * @param attrs
     * @return
     */
    //    需要把自定义的属性封装进去
    public RelativeLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        //这里返回自己设置好的布局参数
        return new LayoutParams(getContext(), attrs);
    }

    public static class LayoutParams extends RelativeLayout.LayoutParams {
        private float widthPrecent;
        private float heightPrecent;


        /**
         * @param c     这里把自定义属性加入
         * @param attrs
         */
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray array = c.obtainStyledAttributes(attrs, R.styleable.PercentRelativeLayout);
            widthPrecent = array.getFloat(R.styleable.PercentRelativeLayout_layout_widthPrecent, this.getWidthPrecent());
            heightPrecent = array.getFloat(R.styleable.PercentRelativeLayout_layout_heightPrecent, this.getHeightPrecent());
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(RelativeLayout.LayoutParams source) {
            super(source);
        }

        public float getHeightPrecent() {
            return heightPrecent;
        }

        public void setHeightPrecent(float heightPrecent) {
            this.heightPrecent = heightPrecent;
        }

        public float getWidthPrecent() {
            return widthPrecent;
        }

        public void setWidthPrecent(float widthPrecent) {
            this.widthPrecent = widthPrecent;
        }
    }
}
