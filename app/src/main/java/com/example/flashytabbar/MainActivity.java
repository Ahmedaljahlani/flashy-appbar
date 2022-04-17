package com.example.flashytabbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private TabFlashyAnimator tabFlashAnimator;
    private final String[] titles = new String[]{"Events", "Highlights", "Search", "Settings"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mFragmentList.add(new TabFragment(titles[0]));
        mFragmentList.add(new TabFragment(titles[1]));
        mFragmentList.add(new TabFragment(titles[2]));
        mFragmentList.add(new TabFragment(titles[3]));

        ViewPager viewPager = findViewById(R.id.view_pager);
        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        };
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabFlashAnimator = new TabFlashyAnimator(tabLayout);
        tabFlashAnimator.addTabItem(titles[0], R.drawable.ic_events);
        tabFlashAnimator.addTabItem(titles[1], R.drawable.ic_highlights);
        tabFlashAnimator.addTabItem(titles[2], R.drawable.ic_search);
        tabFlashAnimator.addTabItem(titles[3], R.drawable.ic_settings, R.color.colorAccent,getResources().getDimension(R.dimen.big_text));
        tabFlashAnimator.highLightTab(0);
        viewPager.addOnPageChangeListener(tabFlashAnimator);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tabFlashAnimator.setBadge(1, 2);
            }
        }, 1000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tabFlashAnimator.setBadge(20, 2);
            }
        }, 2000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tabFlashAnimator.setBadge(200, 2);
            }
        }, 3000);



//        Button select = findViewById(R.id.click_me);
//        select.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                tabFlashAnimator.onPageSelected(1);
//                viewPager.setCurrentItem(1);
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        tabFlashAnimator.onStart((TabLayout) findViewById(R.id.tabLayout));
    }

    @Override
    protected void onStop() {
        super.onStop();
        tabFlashAnimator.onStop();
    }
}