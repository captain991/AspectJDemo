package com.example.administrator.aspectjdemo.permission;

import android.support.v4.app.Fragment;

import com.example.administrator.aspectjdemo.activity.BaseActivity;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by Administrator on 2017/1/23 0023.
 */

public class PermissionContext {
    private Object source;
    private ProceedingJoinPoint joinPoint;

    public PermissionContext(ProceedingJoinPoint joinPoint, Object source) {
        this.joinPoint = joinPoint;
        this.source = source;
        if (joinPoint == null || source == null)
            throw new RuntimeException("joinPoint or source can not be null");
    }

    public BaseActivity getActivity() throws Exception {
        BaseActivity activity = null;
        if (source instanceof BaseActivity) {
            activity = (BaseActivity) source;
        } else if (source instanceof Fragment) {
            activity = (BaseActivity) ((Fragment) source).getActivity();
        } else
            throw new RuntimeException("Wrong Permission Context: @CheckPermission should" +
                    "only be used directly in an activity or a fragment");
        return activity;
    }
}
