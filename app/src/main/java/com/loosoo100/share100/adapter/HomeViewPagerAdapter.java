package com.loosoo100.share100.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.loosoo100.share100.R;

/**
 * 创建时间： 2016/9/9.
 * 作者：HJianFei
 * 功能描述：
 */

public class HomeViewPagerAdapter extends StaticPagerAdapter {

    public String[] imgs = {"http://www.gdmuseum.com/attachment/201608/23/2_1471930640o6zj.jpg", "http://www.gdmuseum.com/attachment/201608/25/2_1472109135zmm2.jpg", "http://www.gdmuseum.com/attachment/201607/4/2_146759397911wW.jpg", "http://www.gdmuseum.com/attachment/201605/31/2_1464738703UtSt.jpg"};


    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(container.getContext())
                .load(imgs[position])
                .placeholder(R.drawable.img4)
                .error(R.drawable.img1)
                .into(view);
        return view;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }
}
