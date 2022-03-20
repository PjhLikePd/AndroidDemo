package com.example.mvpdemos.simple_mvp;

public class SimpleModel {

    private LoadListenter mLoadListenter;

    public SimpleModel(LoadListenter mLoadListenter) {
        this.mLoadListenter = mLoadListenter;
    }

    public void loadModel() {
        mLoadListenter.success("tim");
    }

    interface LoadListenter {
        void success(String userName);
    }
}
