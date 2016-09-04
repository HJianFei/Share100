package com.loosoo100.share100.view.person;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.loosoo100.share100.R;
import com.loosoo100.share100.view.base.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonDetailActivity extends AppCompatActivity implements BaseView {

    @BindView(R.id.alter_name)
    RelativeLayout alter_name;
    @BindView(R.id.alter_sex)
    RelativeLayout alter_sex;
    @BindView(R.id.alter_qrcode)
    RelativeLayout alter_qrcode;
    @BindView(R.id.alter_signal)
    RelativeLayout alter_signal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @OnClick({R.id.alter_name, R.id.alter_sex, R.id.alter_qrcode, R.id.alter_signal})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.alter_name:
                intent = new Intent(this, ChangeInfoActivity.class);
                intent.putExtra("TAG", "alter_name");
                startActivity(intent);
                break;
            case R.id.alter_sex:
                break;
            case R.id.alter_qrcode:
                break;
            case R.id.alter_signal:
                intent = new Intent(this, ChangeInfoActivity.class);
                intent.putExtra("TAG", "alter_signal");
                startActivity(intent);
                break;
        }

    }
}
