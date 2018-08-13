package com.iigo.fanloadingview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.iigo.library.FanLoadingView;

public class MainActivity extends AppCompatActivity {
    private FanLoadingView fanLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fanLoadingView = findViewById(R.id.flv_loading);
    }

    public void onStart(View view) {
        fanLoadingView.start();
    }

    public void onStop(View view) {
        fanLoadingView.stop();
    }

    public void onResume(View view) {
        fanLoadingView.resume();
    }

    public void onPause(View view) {
        fanLoadingView.pause();
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        ViewGroup.LayoutParams layoutParams = fanLoadingView.getLayoutParams();
//        layoutParams.width = 200;
//        layoutParams.height = 100;
//        fanLoadingView.setLayoutParams(layoutParams);
//
//        return super.onKeyDown(keyCode, event);
//    }
}
