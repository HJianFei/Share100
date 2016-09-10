package com.loosoo100.share100.view.message;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loosoo100.share100.R;
import com.loosoo100.share100.presenter.message.MsgPresenter;
import com.loosoo100.share100.presenter.message.MsgPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * description: 消息页面
 * author: HJianFei
 * date: 2016/9/6 10:00
 * update: 2016/9/6
 */

public class MsgFragment extends Fragment implements MsgView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Context mContext;
    @BindView(R.id.rv_message)
    RecyclerView mRecyclerView;
    private SweetAlertDialog mDialog;
    private MsgPresenter mMsgPresenter;


    public MsgFragment() {
    }

    public static MsgFragment newInstance(String param1, String param2) {
        MsgFragment fragment = new MsgFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_msg, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mMsgPresenter = new MsgPresenterImpl(this);
        mMsgPresenter.onStart();

    }

    @Override
    public void showProgress() {
        mDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
        mDialog.setTitleText("数据加载中...");
        mDialog.show();

    }


    @Override
    public void hideProgress() {
        if (null != mDialog) {
            mDialog.dismiss();
        }
    }

    /**
     * 页面第一次可见时数据的加载填充
     */

    @Override
    public void onFirstVisible() {

    }

    /**
     * 下拉刷新
     */

    @Override
    public void Refreshing() {


    }

    /**
     * 上拉加载更多
     */
    @Override
    public void LoadingMore() {

    }

    /**
     * 数据加载失败
     */
    @Override
    public void onError() {
        mDialog = new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE);
        mDialog.setTitleText("数据加载失败");
        mDialog.show();

    }

    @Override
    public void onDestroy() {
        mMsgPresenter.onDestroy();
        super.onDestroy();
    }
}
