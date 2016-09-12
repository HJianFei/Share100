package com.loosoo100.share100.view.home;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.util.RecyclerViewUtils;
import com.github.jdsjlzx.view.LoadingFooter;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.loosoo100.share100.R;
import com.loosoo100.share100.adapter.CommonAdapter;
import com.loosoo100.share100.adapter.HomeRecommendViewPager;
import com.loosoo100.share100.adapter.HomeViewPagerAdapter;
import com.loosoo100.share100.adapter.ViewHolder;
import com.loosoo100.share100.utils.T;
import com.loosoo100.share100.view.base.BaseFragment;
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.RotateDownPageTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class HomeFragment extends BaseFragment implements HomeView, View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Context mContext;
    @BindView(R.id.location)
    TextView mLocation;
    @BindView(R.id.get_location)
    LinearLayout mGetLocation;
    @BindView(R.id.search_seller)
    ImageView mSearchSeller;
    private String mParam1;
    private String mParam2;
    @BindView(R.id.home_recyclerView)
    LRecyclerView mRecyclerView;
    private CommonAdapter<String> mAdapter;
    private List<String> listDatas = new ArrayList<>();
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;


    public HomeFragment() {
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        this.mContext = context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        for (int i = 0; i < 10; i++) {
            listDatas.add("item" + i);

        }
        mAdapter = new CommonAdapter<String>(mContext, R.layout.home_recyclerview_item, listDatas) {
            @Override
            public void setData(ViewHolder holder, String s) {
                holder.setText(R.id.tv_home_item, s);
            }
        };
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view) {
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mContext, mAdapter);
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        //主页的ViewPager广播轮播条
        View header_viewpager = LayoutInflater.from(mContext).inflate(R.layout.home_head_view_viewpager, (ViewGroup) view.findViewById(android.R.id.content), false);
        RollPagerView mHomeViewpager = (RollPagerView) header_viewpager.findViewById(R.id.home_view_pager);
        initHomeViewPager(mHomeViewpager);
        //主页的商品分类
        View header_category = LayoutInflater.from(mContext).inflate(R.layout.home_head_view_category, (ViewGroup) view.findViewById(android.R.id.content), false);
        initHomeCategory(header_category);
        //主页的推荐商家
        View header_recommend = LayoutInflater.from(mContext).inflate(R.layout.home_head_view_recommend, (ViewGroup) view.findViewById(android.R.id.content), false);
        ViewPager mHomeRecommendViewPager = (ViewPager) header_recommend.findViewById(R.id.vp_viewpager_recommend);
        initHomeRecommend(mHomeRecommendViewPager);

        RecyclerViewUtils.setHeaderView(mRecyclerView, header_viewpager);
        RecyclerViewUtils.setHeaderView(mRecyclerView, header_category);
        RecyclerViewUtils.setHeaderView(mRecyclerView, header_recommend);

        mRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {
                RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.Normal);
            }

            @Override
            public void onScrollUp() {
            }

            @Override
            public void onScrollDown() {
            }

            @Override
            public void onBottom() {
                LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(mRecyclerView);
                if (state == LoadingFooter.State.Loading) {
                    Log.d(TAG, "the state is Loading, just wait..");
                    return;
                }
            }

            @Override
            public void onScrolled(int distanceX, int distanceY) {
            }

        });
        mLRecyclerViewAdapter.setOnItemClickListener(new com.github.jdsjlzx.interfaces.OnItemClickListener() {


            @Override
            public void onItemClick(View view, int i) {
                T.showShort(mContext, "点击了item:" + i);
            }

            @Override
            public void onItemLongClick(View view, int i) {

            }
        });

//        mRecyclerView.setRefreshing(true);
    }

    private void initHomeCategory(View header_category) {
        header_category.findViewById(R.id.home_recommend_near_shopping).setOnClickListener(this);
        header_category.findViewById(R.id.home_recommend_hotel).setOnClickListener(this);
        header_category.findViewById(R.id.home_recommend_beauty).setOnClickListener(this);
        header_category.findViewById(R.id.home_recommend_leisure).setOnClickListener(this);
        header_category.findViewById(R.id.home_recommend_food).setOnClickListener(this);
        header_category.findViewById(R.id.home_recommend_service_for_life).setOnClickListener(this);
        header_category.findViewById(R.id.home_recommend_supermarket).setOnClickListener(this);
        header_category.findViewById(R.id.home_recommend_could_shopping).setOnClickListener(this);
        header_category.findViewById(R.id.home_recommend_store).setOnClickListener(this);
        header_category.findViewById(R.id.home_recommend_more).setOnClickListener(this);

    }

    /**
     * 商家推荐
     *
     * @param mHomeRecommendViewPager
     */
    private void initHomeRecommend(ViewPager mHomeRecommendViewPager) {
        mHomeRecommendViewPager.setPageMargin(50);
        mHomeRecommendViewPager.setOffscreenPageLimit(5);
        mHomeRecommendViewPager.setAdapter(new HomeRecommendViewPager());
        mHomeRecommendViewPager.setPageTransformer(true, new RotateDownPageTransformer(new AlphaPageTransformer(new ScaleInTransformer())));

    }

    /**
     * Home 页面的广播轮播条
     *
     * @param mHomeViewpager
     */

    private void initHomeViewPager(RollPagerView mHomeViewpager) {
        mHomeViewpager.setPlayDelay(3000);
        mHomeViewpager.setAdapter(new HomeViewPagerAdapter());
        mHomeViewpager.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                T.showShort(mContext, "initHomeViewPager：" + position);
            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void initViewPager() {

    }

    @Override
    public void onClick(View v) {
        T.showShort(mContext, "initHomeRecommend" + v.getId());

    }
}
