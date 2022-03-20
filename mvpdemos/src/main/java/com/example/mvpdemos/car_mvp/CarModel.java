package com.example.mvpdemos.car_mvp;

public class CarModel implements CarContract.Model{
    @Override
    public void getData(GetDataCallBack callBack) {
        // todo 跟云端或者本地数据库交互

        callBack.onDataLoad("宝马 X5");
    }
}
