package com.example.tools.activities;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.example.tools.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SoundRecorder extends AppCompatActivity {
    PagerSlidingTabStrip tabs;
    ViewPager viewPager;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_recorder);

        tabs.findViewById(R.id.tabs);
        viewPager.findViewById(R.id.viewpager);
        toolbar.findViewById(R.id.toolbar);

        viewPager .setAdapter(new MyTabAdapter(getSupportFragmentManager()));
        tabs.setViewPager(viewPager);
        setSupportActionBar(toolbar);
    }
}
//https://www.youtube.com/watch?v=sMCet8AYKTs
// watch this totorial to compelete sound recorder Part 5