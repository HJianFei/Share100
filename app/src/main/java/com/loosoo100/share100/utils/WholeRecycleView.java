package com.loosoo100.share100.utils;

import android.content.Context;
import android.util.AttributeSet;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * 创建时间： 2016/9/8.
 * 作者：HJianFei
 * 功能描述：
 */

public class WholeRecycleView extends XRecyclerView {
    public WholeRecycleView(Context context) {
        super(context);
    }

    public WholeRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WholeRecycleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
