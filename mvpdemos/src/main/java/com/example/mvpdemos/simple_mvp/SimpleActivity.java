package com.example.mvpdemos.simple_mvp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mvpdemos.R;

public class SimpleActivity extends AppCompatActivity {

    private SimplePresenter simplePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simplemvp);

        simplePresenter = new SimplePresenter(this);
    }

    public void btnGetUserName(View view) {
        simplePresenter.loadData();
    }

    public void showUserName(String userName) {
        TextView textView = findViewById(R.id.tv_showname);
        textView.setText(userName);
    }
}
