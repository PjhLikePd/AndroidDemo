package com.example.mvpdemos.car_mvp;

import com.example.mvpdemos.base.BaseModel;

public class CarPresenter implements CarContract.Presenter{

    CarContract.View view;
    CarModel carModel;

    public CarPresenter(CarContract.View view) {
        this.view = view;
        carModel = new CarModel();
        view.setPresenter(this);
    }

    @Override
    public void getData() {
        carModel.getData(new BaseModel.GetDataCallBack() {
            @Override
            public void onDataLoad(String result) {
                view.showData(result);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void start() {
        getData();
    }
}
