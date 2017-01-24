package com.example.administrator.aspectjdemo.activity;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.aspectjdemo.permission.CheckPermission;
import com.example.administrator.aspectjdemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @CheckPermission(permission = Manifest.permission.CAMERA,
            rationale = R.string.permission_rationale)
    public void click(View view) {
    }

}
