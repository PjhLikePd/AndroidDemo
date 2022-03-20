package com.example.mvpdemos.base;

public interface BaseModel {
    interface GetDataCallBack {
        void onDataLoad(String result);

        void onDataNotAvailable();
    }

    void getData(GetDataCallBack callBack);
}
