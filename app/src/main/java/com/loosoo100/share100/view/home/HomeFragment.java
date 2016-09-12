package com.loosoo100.share100.view.home;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
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
import com.loosoo100.share100.presenter.home.HomePresenter;
import com.loosoo100.share100.presenter.home.HomePresenterImpl;
import com.loosoo100.share100.utils.T;
import com.loosoo100.share100.view.base.BaseFragment;
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.RotateDownPageTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

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
    //对话框
    private SweetAlertDialog mDialog;
    private HomePresenter mHomePresenter;
    private RollPagerView mHomeViewpager;
    private ViewPager mHomeRecommendViewPager;

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
        for (int i = 0; i < 5; i++) {
            listDatas.add("item:" + i);

        }
        mHomePresenter = new HomePresenterImpl(this);
        mHomePresenter.firstInitHomeView();
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view) {

        mAdapter = new CommonAdapter<String>(mContext, R.layout.home_recyclerview_item, listDatas) {
            @Override
            public void setData(ViewHolder holder, String s) {
                holder.setText(R.id.tv_home_item, s);
            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mContext, mAdapter);
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        //主页的ViewPager广播轮播条
        View header_viewpager = LayoutInflater.from(mContext).inflate(R.layout.home_head_view_viewpager, (ViewGroup) view.findViewById(android.R.id.content), false);
        mHomeViewpager = (RollPagerView) header_viewpager.findViewById(R.id.home_view_pager);
        //主页的商品分类
        View header_category = LayoutInflater.from(mContext).inflate(R.layout.home_head_view_category, (ViewGroup) view.findViewById(android.R.id.content), false);
        initHomeCategory(header_category);
        //主页的推荐商家
        View header_recommend = LayoutInflater.from(mContext).inflate(R.layout.home_head_view_recommend, (ViewGroup) view.findViewById(android.R.id.content), false);
        mHomeRecommendViewPager = (ViewPager) header_recommend.findViewById(R.id.vp_viewpager_recommend);
        RecyclerViewUtils.setHeaderView(mRecyclerView, header_viewpager);
        RecyclerViewUtils.setHeaderView(mRecyclerView, header_category);
        RecyclerViewUtils.setHeaderView(mRecyclerView, header_recommend);

        mRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {
                RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.Normal);
                mHomePresenter.refreshHomeView();
            }

            @Override
            public void onScrollUp() {
            }

            @Override
            public void onScrollDown() {
            }

            @Override
            public void onBottom() {
                // loading more
                RecyclerViewStateUtils.setFooterViewState(getActivity(), mRecyclerView, 5, LoadingFooter.State.Loading, null);
                mHomePresenter.loadMoreHomeView();
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

    @Override
    public void showProgress() {
        mDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
        mDialog.setTitleText("数据加载中");
        mDialog.show();


    }

    @Override
    public void hideProgress() {
        if (null != mDialog) {
            mDialog.dismiss();
        }
        mRecyclerView.refreshComplete();
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void showError() {
        mRecyclerView.refreshComplete();
        mAdapter.notifyDataSetChanged();
        mDialog = new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE);
        mDialog.setTitleText("数据加载失败");
        mDialog.show();

    }

    @Override
    public void showEmpty() {
        mRecyclerView.refreshComplete();
        mAdapter.notifyDataSetChanged();
        mDialog = new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE);
        mDialog.setTitleText("没有数据");
        mDialog.show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_recommend_near_shopping:
                T.showShort(mContext, "附近购物");
                break;
            case R.id.home_recommend_hotel:
                T.showShort(mContext, "酒店");
                break;
            case R.id.home_recommend_beauty:
                T.showShort(mContext, "丽人");
                break;
            case R.id.home_recommend_leisure:
                T.showShort(mContext, "休闲娱乐");
                break;
            case R.id.home_recommend_food:
                T.showShort(mContext, "美食");
                break;
            case R.id.home_recommend_service_for_life:
                T.showShort(mContext, "生活服务");
                break;
            case R.id.home_recommend_supermarket:
                T.showShort(mContext, "分享超市");
                break;
            case R.id.home_recommend_could_shopping:
                T.showShort(mContext, "分享云购");
                break;
            case R.id.home_recommend_store:
                T.showShort(mContext, "分享便利店");
                break;
            case R.id.home_recommend_more:
                T.showShort(mContext, "更多");
                break;
        }

    }

    @Override
    public void initHomeViewPager() {
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
    public void initHomeRecommend() {
        mHomeRecommendViewPager.setPageMargin(50);
        mHomeRecommendViewPager.setOffscreenPageLimit(5);
        mHomeRecommendViewPager.setAdapter(new HomeRecommendViewPager());
        mHomeRecommendViewPager.setPageTransformer(true, new RotateDownPageTransformer(new AlphaPageTransformer(new ScaleInTransformer())));

    }

    @Override
    public void initHomeRecyclerView(List<String> object) {
        listDatas.addAll(object);
        mRecyclerView.refreshComplete();
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroy() {
        mHomePresenter.onHomeViewDestroy();
        super.onDestroy();
    }
}
