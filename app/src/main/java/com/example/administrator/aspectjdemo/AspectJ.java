package com.example.administrator.aspectjdemo;

import android.content.pm.PackageManager;
import android.os.Build;

import com.example.administrator.aspectjdemo.permission.CheckPermission;
import com.example.administrator.aspectjdemo.permission.PermissionContext;
import com.example.administrator.aspectjdemo.activity.BaseActivity;
import com.example.administrator.aspectjdemo.util.DialogUtil;
import com.example.administrator.aspectjdemo.util.ICallBack;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by Administrator on 2017/1/23 0023.
 * AOP面向切片的统一处理的地方
 */
@Aspect
public class AspectJ {
    public static final String POINT_CUT_CHECK_PERMISSION =
            "execution(@com.example.administrator.aspectjdemo.RunTimePermission.CheckPermission * *(..))";
//    @Pointcut(POINT_CUT_CHECK_PERMISSION)
//    public void checkPermission() {
//    }

    @Around(POINT_CUT_CHECK_PERMISSION)
    public Object checkPermission(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object object = null;
        if (Build.VERSION.SDK_INT >= 23) {
            Object source = joinPoint.getThis();
            PermissionContext pc = new PermissionContext(joinPoint, source);
            final BaseActivity activity = pc.getActivity();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            CheckPermission permissionAnnotation = methodSignature.getMethod().
                    getAnnotation(CheckPermission.class);
            final String permission = permissionAnnotation.permission();
            final String rationale = activity.getString(permissionAnnotation.rationale());
            final int requestCode = permissionAnnotation.requestCode();
            if (PackageManager.PERMISSION_GRANTED != activity.checkSelfPermission(permission)) {
                if (activity.shouldShowRequestPermissionRationale(permission)) {
                    DialogUtil.showDialog(activity, null, rationale, new ICallBack() {
                        @Override
                        public void call(Object object) {
                            if (Build.VERSION.SDK_INT < 23)
                                return;
                            activity.setProceedingJoinPoint(joinPoint);
                            activity.requestPermissions(new String[]{permission}, requestCode);
                        }
                    });
                } else {
                    activity.setProceedingJoinPoint(joinPoint);
                    activity.requestPermissions(new String[]{permission}, requestCode);
                }
                return null;
            }
        }

        object = joinPoint.proceed();
        return object;
    }
}
