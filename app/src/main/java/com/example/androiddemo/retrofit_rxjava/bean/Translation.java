package com.example.androiddemo.retrofit_rxjava.bean;

import android.util.Log;

public class Translation {
    private static final String TAG = Translation.class.getName();
    private int status;

    private content content;
    private static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }

    //定义 输出返回数据 的方法
    public void show() {
        Log.d("RxJava", content.out );
    }

}
