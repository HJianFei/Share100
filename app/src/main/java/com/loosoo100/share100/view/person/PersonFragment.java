package com.loosoo100.share100.view.person;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loosoo100.share100.R;
import com.loosoo100.share100.utils.T;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.btn_person_detail)
    LinearLayout mBtnPersonDetail;
    @BindView(R.id.detail_play)
    LinearLayout mDetailPlay;
    @BindView(R.id.my_user)
    LinearLayout mMyUser;
    @BindView(R.id.add_friend)
    LinearLayout mAddFriend;
    @BindView(R.id.my_state)
    LinearLayout mMyState;
    @BindView(R.id.my_enshrine)
    LinearLayout mMyEnshrine;
    @BindView(R.id.my_address)
    LinearLayout mMyAddress;
    @BindView(R.id.system_setting)
    LinearLayout mSystemSetting;
    @BindView(R.id.my_volume)
    LinearLayout mMyVolume;
    @BindView(R.id.my_album)
    LinearLayout mMyAlbum;
    @BindView(R.id.my_wallet)
    LinearLayout mMyWallet;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context mContext;


    public PersonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonFragment newInstance(String param1, String param2) {
        PersonFragment fragment = new PersonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
    public void onAttach(Context context) {
        this.mContext = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.btn_person_detail, R.id.system_setting,
            R.id.detail_play, R.id.my_user, R.id.add_friend,
            R.id.my_state, R.id.my_enshrine, R.id.my_address, R.id.my_volume, R.id.my_album, R.id.my_wallet})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_person_detail:
                intent = new Intent(mContext, PersonDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.system_setting:
                intent = new Intent(mContext, SystemSettingActivity.class);
                startActivity(intent);
                break;
            case R.id.detail_play:
                T.showShort(mContext, "消费明细");
                break;
            case R.id.my_user:
                T.showShort(mContext, "消费明细");
                break;
            case R.id.add_friend:
                T.showShort(mContext, "消费明细");
                break;
            case R.id.my_state:
                T.showShort(mContext, "消费明细");
                break;
            case R.id.my_enshrine:
                T.showShort(mContext, "消费明细");
                break;
            case R.id.my_address:
                T.showShort(mContext, "消费明细");
                break;
            case R.id.my_volume:
                T.showShort(mContext, "消费明细");
                break;
            case R.id.my_album:
                T.showShort(mContext, "消费明细");
                break;
            case R.id.my_wallet:
                T.showShort(mContext, "消费明细");
                break;
        }

    }

}
