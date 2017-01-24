package com.example.administrator.aspectjdemo.activity;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by Administrator on 2017/1/24 0024.
 */

public class BaseActivity extends AppCompatActivity {
    public ProceedingJoinPoint joinPoint = null;

    public void setProceedingJoinPoint(ProceedingJoinPoint joinPoint) {
        this.joinPoint = joinPoint;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PackageManager.PERMISSION_GRANTED != grantResults[0]) {
            Toast.makeText(this, "您未授予" + permissions[0] + "权限,部分功能可能无法正常使用", Toast.LENGTH_LONG).show();
        } else if (joinPoint != null) {
            try {
                joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}
