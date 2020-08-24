package com.example.recycleviewitemdragdemo;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        StatusBarUtils.setColor(this, Color.rgb(250,250,250));
        initDate();
        initView();
    }
    protected abstract int getLayout();
    protected abstract void initDate();
    protected abstract void initView();
}
