package com.example.androiddemo.retrofit_rxjava;

import android.util.Log;

import com.example.androiddemo.R;
import com.example.androiddemo.base.BaseActivity;
import com.example.androiddemo.retrofit_rxjava.bean.Translation;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRxjavaActivity extends BaseActivity {

    private static final String TAG = RetrofitRxjavaActivity.class.getName();

    @Override
    protected int setLayout() {
        return R.layout.activity_retrofit;
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();

        /**
         * step1：采用interval（延迟发送）
         * 注：此处主要展示无限次轮询，若要实现有限次轮询，将interval换成intervalRange即可
         */
        Observable.intervalRange(3, 10, 2, 1, TimeUnit.SECONDS)
                /**
                 * step2：每次发送数字前发送1次网络请求（doOnNext()在执行Next事件前调用）
                 * 即每秒产生1个数字前，就发送1次网络请求，从而实现轮询需求
                 */
                .doOnNext(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.d(TAG, "accept: 第 " + aLong + " 次轮询");

                /**
                 * step3：通过Retrofit发送网络请求
                 */
                // a.创建Retrofit对象
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://fy.iciba.com/") // 设置网络请求url
                        .addConverterFactory(GsonConverterFactory.create()) // 设置使用Gson解析
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                        .build();

                // b.创建网络请求接口的实例
                GetRequest_Interface requestInterface = retrofit.create(GetRequest_Interface.class);

                // c.采用Observable<...>形式对网络请求进行封装
                Observable<Translation>observable = requestInterface.getCall();
                // d.通过线程切换发送网络请求
                observable.subscribeOn(Schedulers.io()) // 切换到IO线程进行网络请求
                        .observeOn(AndroidSchedulers.mainThread()) // 切换到主线程处理请求结果
                        .subscribe(new Observer<Translation>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Translation value) {
                        value.show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: 请求失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

            }
        }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long value) {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: 对Error事件做成响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: 对Complete事件作出响应");
            }
        });
    }
}
