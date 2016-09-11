package com.loosoo100.share100.view.home;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.loosoo100.share100.R;
import com.loosoo100.share100.adapter.CommonAdapter;
import com.loosoo100.share100.adapter.HomeRecommendViewPager;
import com.loosoo100.share100.adapter.HomeViewPagerAdapter;
import com.loosoo100.share100.utils.T;
import com.loosoo100.share100.view.base.BaseFragment;
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.RotateDownPageTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.rv_home_store)
    XRecyclerView mRecyclerView;
    private CommonAdapter<String> mAdapter;
    private List<String> listData = new ArrayList<>();
    private int refreshTime = 0;
    private int times = 0;


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
        mAdapter = new CommonAdapter<String>(mContext, R.layout.home_recyclerview_item, listData) {
            @Override
            public void setData(com.loosoo100.share100.adapter.ViewHolder holder, String s) {
                holder.setText(R.id.tv_home_item, s);
            }
        };
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        //主页的ViewPager广播轮播条
        View header_viewpager = View.inflate(mContext, R.layout.home_head_view_viewpager, null);
        RollPagerView mHomeViewpager = (RollPagerView) header_viewpager.findViewById(R.id.home_view_pager);
        initHomeViewPager(mHomeViewpager);
        mRecyclerView.addHeaderView(header_viewpager);
        //主页的商品分类
        View header_category = View.inflate(mContext, R.layout.home_head_view_category, null);
        initHomeCategory(header_category);
        mRecyclerView.addHeaderView(header_category);
        //主页的推荐商家
        View header_recommend = View.inflate(mContext, R.layout.home_head_view_recommend, null);
        ViewPager mHomeRecommendViewPager = (ViewPager) header_recommend.findViewById(R.id.vp_viewpager_recommend);
        initHomeRecommend(mHomeRecommendViewPager);
        mRecyclerView.addHeaderView(header_recommend);
        //设置页面下拉刷新，下拉加载更多的监听器
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {

            //下拉刷新
            @Override
            public void onRefresh() {
                refreshTime++;
                times = 0;
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        listData.clear();
                        for (int i = 0; i < 3; i++) {
                            listData.add("item" + i + "after " + refreshTime + " times of refresh");
                        }
                        mAdapter.notifyDataSetChanged();
                        mRecyclerView.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            //上拉加载更多
            @Override
            public void onLoadMore() {
                if (times < 2) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for (int i = 0; i < 3; i++) {
                                listData.add("item" + (1 + listData.size()));
                            }
                            mRecyclerView.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for (int i = 0; i < 9; i++) {
                                listData.add("item" + (1 + listData.size()));
                            }
                            mRecyclerView.noMoreLoading();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }
                times++;

            }
        });
        //设置适配器
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setRefreshing(true);
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
