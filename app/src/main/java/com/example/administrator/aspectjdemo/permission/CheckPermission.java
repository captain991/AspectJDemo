package com.example.administrator.aspectjdemo.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/1/23 0023.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPermission {
    //需要检查的权限,应该在Manifest.permission中
    String permission();

    //如果已经被拒绝过,给出需要该权限的理由对应的资源id
    int rationale();

    //请求码
    int requestCode() default 0x00;
}
