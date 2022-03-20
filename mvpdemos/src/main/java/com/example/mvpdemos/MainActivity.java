package com.example.mvpdemos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mvpdemos.simple_mvp.SimpleActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickSimpleMvp(View view) {
        startActivity(new Intent(this, SimpleActivity.class));
    }
}