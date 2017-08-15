package com.example.jjh.test;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.jjh.test.fragments.Test2Fragment;
import com.example.jjh.test.fragments.TestFragment;

public class MainActivity extends AppCompatActivity {

    private FrameLayout flContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getAttributes().width = WindowManager.LayoutParams.MATCH_PARENT;

        getWindow().getAttributes().height = WindowManager.LayoutParams.MATCH_PARENT;

        flContents = (FrameLayout)findViewById(R.id.flContents);

        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.flContents, new Test2Fragment());
                ft.commitAllowingStateLoss();
            }
        });

        findViewById(R.id.btnPrev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.flContents, new TestFragment());
                ft.commitAllowingStateLoss();
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.flContents, new TestFragment());
        ft.commitAllowingStateLoss();

        hideSystemUI();
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int i) {
                hideSystemUI();
            }
        });
    }

    private void hideSystemUI() {
      getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                //| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION //ADJUST RESIZE 인 경우 주석처리해야지만 Resize 적용 됨.
                //| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN //ADJUST RESIZE 인 경우 주석처리해야지만 Resize 적용 됨.
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_IMMERSIVE);

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if( hasFocus ) {
            hideSystemUI();
        }
    }
}
