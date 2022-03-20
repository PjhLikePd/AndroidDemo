package com.example.retrofitrxjava.network.request;

import com.example.retrofitrxjava.bean.CarBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 定义请求的服务器的API封装类
 */
public interface NetRequest {

    // 网络接口
    @GET("openapi.do?keyfrom=Yanzhikai&key=2032414398&type=data&doctype=json&version=1.1&q=car")
    Observable<CarBean> getCarList();
}
