package com.example.mvpdemos.car_mvp;

import com.example.mvpdemos.base.BaseModel;
import com.example.mvpdemos.base.BasePresenter;
import com.example.mvpdemos.base.BaseView;

public interface CarContract {
    interface Presenter extends BasePresenter{
        void getData();
    }

    interface View extends BaseView<Presenter> {
        void showData(String data);
    }

    interface Model extends BaseModel{
        
    }
}
