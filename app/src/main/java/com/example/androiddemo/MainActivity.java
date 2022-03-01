package com.example.androiddemo;

import android.content.Intent;
import android.view.View;

import com.example.androiddemo.base.BaseActivity;
import com.example.androiddemo.retrofit_rxjava.RetrofitRxjavaActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        findViewById(R.id.btn_retrofit_rxjava).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_retrofit_rxjava:
                beginStartActivity(RetrofitRxjavaActivity.class);
                break;
            default:
                break;
        }
    }
    private void beginStartActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}