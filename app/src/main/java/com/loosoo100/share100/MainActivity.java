package com.loosoo100.share100;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.loosoo100.share100.view.home.HomeFragment;
import com.loosoo100.share100.view.message.MsgFragment;
import com.loosoo100.share100.view.order.OrderFragment;
import com.loosoo100.share100.view.person.PersonFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initNavi();
    }

    private void initNavi() {
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, R.string.navigation_home).setActiveColorResource(R.color.tab_background))
                .addItem(new BottomNavigationItem(R.drawable.ic_book_white_24dp, R.string.navigation_order).setActiveColorResource(R.color.tab_background))
                .addItem(new BottomNavigationItem(R.drawable.ic_music_note_white_24dp, R.string.navigation_msg).setActiveColorResource(R.color.tab_background))
                .addItem(new BottomNavigationItem(R.drawable.ic_tv_white_24dp, R.string.navigation_person).setActiveColorResource(R.color.tab_background))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        setTabSelection(0);
    }

    private void setTabSelection(int i) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (i == 0) {

            transaction.replace(R.id.fl_content, HomeFragment.newInstance(null, null));
        } else if (i == 1) {

            transaction.replace(R.id.fl_content, OrderFragment.newInstance(null, null));
        } else if (i == 2) {

            transaction.replace(R.id.fl_content, MsgFragment.newInstance(null, null));
        } else if (i == 3) {

            transaction.replace(R.id.fl_content, PersonFragment.newInstance(null, null));
        }
        transaction.commit();

    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                setTabSelection(0);
                break;
            case 1:
                setTabSelection(1);
                break;
            case 2:
                setTabSelection(2);
                break;
            case 3:
                setTabSelection(3);
                break;
        }

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
