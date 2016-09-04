package com.loosoo100.share100.view.person;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.loosoo100.share100.R;

/**
 * description: 更改个人信息
 * author: HJianFei
 * date: 2016/9/4 19:46
 * update: 2016/9/4
 */
public class ChangeInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        String tag = getIntent().getExtras().getString("TAG");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (tag.equals("alter_name")) {
            toolbar.setTitle(R.string.change_name);

        } else {
            toolbar.setTitle(R.string.change_signal);
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //获取MenuInflater对象
        MenuInflater inflater = getMenuInflater();
        // 使用自定义的ＸＭＬ菜单文件
        inflater.inflate(R.menu.change_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_ok:
                Toast.makeText(ChangeInfoActivity.this, "完成", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
