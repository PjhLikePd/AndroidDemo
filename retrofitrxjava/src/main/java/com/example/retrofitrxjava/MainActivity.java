package com.example.retrofitrxjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofitrxjava.bean.CarBean;
import com.example.retrofitrxjava.common.Config;
import com.example.retrofitrxjava.network.NetWorkManger;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private CompositeDisposable mDdisposables;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv_data);
        mDdisposables = new CompositeDisposable();
    }

    public void btnClick(View view) {
        Disposable disposable = NetWorkManger.getRequest().getCarList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carBean -> {
                    Toast.makeText(MainActivity.this, "get success..........", Toast.LENGTH_SHORT).show();
                    Log.d(Config.BASE_TAG, "accept: get success.......");
                    textView.setText(String.format("data: %s", carBean.getQuery()));
                }, throwable -> {
                    Toast.makeText(MainActivity.this, "get fail..........", Toast.LENGTH_SHORT).show();
                    Log.d(Config.BASE_TAG, "accept: get fail.......");
                });
        mDdisposables.add(disposable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDdisposables.dispose();
    }
}
