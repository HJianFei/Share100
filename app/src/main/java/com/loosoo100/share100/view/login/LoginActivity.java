package com.loosoo100.share100.view.login;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loosoo100.share100.MainActivity;
import com.loosoo100.share100.R;
import com.loosoo100.share100.presenter.login.LoginPresenter;
import com.loosoo100.share100.presenter.login.LoginPresenterImpl;
import com.loosoo100.share100.utils.SnackBarUtils;
import com.loosoo100.share100.view.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * description: 登录页面的界面实现
 * author: HJianFei
 * date: 2016/9/7 11:50
 * update: 2016/9/7
 */
public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_go)
    Button btGo;
    @BindView(R.id.cv)
    CardView cv;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private LoginPresenter mLoginPresenter;
    private SweetAlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginPresenter = new LoginPresenterImpl(this);
    }

    /**
     * 登录注册按钮的点击事件
     *
     * @param view
     */
    @OnClick({R.id.bt_go, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            //注册
            case R.id.fab:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setExitTransition(null);
                    getWindow().setEnterTransition(null);
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                    startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
                } else {
                    startActivity(new Intent(this, RegisterActivity.class));
                }
                break;
            //登录
            case R.id.bt_go:
                if (TextUtils.isEmpty(etUsername.getText().toString())) {
                    SnackBarUtils.makeShort(etUsername, "用户名不能为空").show();
                    return;
                }
                if (TextUtils.isEmpty(etPassword.getText().toString())) {
                    SnackBarUtils.makeShort(etPassword, "密码不能为空").show();
                    return;
                }
                mLoginPresenter.validateCredentials(etUsername.getText().toString().trim(), etPassword.getText().toString().trim());
                break;
        }
    }

    /**
     * 登陆失败
     */

    @Override
    public void failToLogin() {
        mDialog.dismiss();
        mDialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE);
        mDialog.setTitleText("登录失败，登录");
        mDialog.show();

    }

    /**
     * 登录成功，跳转到主页面
     */
    @Override
    public void navicateToHome() {
        Intent i2 = new Intent(this, MainActivity.class);
        startActivity(i2);
        this.finish();
    }

    /**
     * 显示加载对话框
     */

    @Override
    public void showProgress() {
        mDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        mDialog.setTitleText("登录中...");
        mDialog.show();
    }

    /**
     * 隐藏加载对话框
     */

    @Override
    public void hideProgress() {
        if (null != mDialog) {
            mDialog.dismiss();
        }

    }

    /**
     * 页面销毁
     */
    @Override
    protected void onDestroy() {
        mLoginPresenter.onDestroy();
        super.onDestroy();
    }
}
