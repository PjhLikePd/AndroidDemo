package com.example.androiddemo.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());

        initView();

        initData();
    }

    protected void initView() {

    }

    protected abstract int setLayout();

    protected void initData(){

    }
}
