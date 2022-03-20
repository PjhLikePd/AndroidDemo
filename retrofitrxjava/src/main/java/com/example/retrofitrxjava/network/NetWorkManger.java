package com.example.retrofitrxjava.network;

import android.util.Log;

import com.example.retrofitrxjava.common.Config;
import com.example.retrofitrxjava.network.request.NetRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Api初始化类
 */
public class NetWorkManger {
    private static NetWorkManger mInstance;
    private static Retrofit retrofit;
    private static volatile NetRequest netRequest = null;

    // 单例模式
    public static NetWorkManger getInstance() {
        if (mInstance == null) {
            synchronized (NetWorkManger.class) {
                if (mInstance == null) {
                    mInstance = new NetWorkManger();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化必要的对象和参数
     */
    public void init() {
        // a.初始化okhttp/日志打印
        // ps：https://www.jianshu.com/p/1463bc223cd8
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> {
            try {
                String realMessage = URLDecoder.decode(message, "utf-8");
                Log.d(Config.BASE_TAG, "https_log -> " + message);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Log.d(Config.BASE_TAG, "https_log -> " + message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        // b.初始化Retrofit
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Config.BASE_URL) // 网络请求的基础地址
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 网络请求适配器，使用rxjava
                .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器
                .build();
    }

    /**
     * 创建网络请求接口的实例
     *
     * @return 网络请求接口的实例
     */
    public static NetRequest getRequest() {
        if (netRequest == null) {
            synchronized (NetRequest.class) {
                if (netRequest == null) {
                    netRequest = retrofit.create(NetRequest.class);
                }
            }
        }
        return netRequest;
    }
}
