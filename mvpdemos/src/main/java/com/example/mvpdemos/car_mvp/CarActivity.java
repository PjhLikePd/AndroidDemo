package com.example.mvpdemos.car_mvp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mvpdemos.R;

public class CarActivity extends AppCompatActivity implements CarContract.View {
    private CarPresenter carPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        carPresenter = new CarPresenter(this);
    }

    public void btnGetCarName(View view) {
        carPresenter.start();
    }

    @Override
    public void showData(String data) {
        TextView textView = findViewById(R.id.tv_showname);
        textView.setText(String.format("car name is: %s", data));
    }

    @Override
    public void setPresenter(CarContract.Presenter presenter) {
        if (carPresenter == null) {
            carPresenter = (CarPresenter) presenter;
        }
    }
}