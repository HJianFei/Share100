package com.loosoo100.share100.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.loosoo100.share100.R;
import com.loosoo100.share100.utils.T;

/**
 * 创建时间： 2016/9/10.
 * 作者：HJianFei
 * 功能描述：
 */

public class HomeRecommendViewPager extends PagerAdapter {

    public String[] imgs = {"http://www.gdmuseum.com/attachment/201608/23/2_1471930640o6zj.jpg", "http://www.gdmuseum.com/attachment/201608/25/2_1472109135zmm2.jpg", "http://www.gdmuseum.com/attachment/201607/4/2_146759397911wW.jpg", "http://www.gdmuseum.com/attachment/201605/31/2_1464738703UtSt.jpg"};

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View view = View.inflate(container.getContext(), R.layout.home_recommend_viewpager_item, null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_home_item);
        Glide.with(container.getContext())
                .load(imgs[position])
                .placeholder(R.drawable.img4)
                .error(R.drawable.img1)
                .into(iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.showShort(container.getContext(), "initHomeRecommend：" + position);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

