package com.example.administrator.aspectjdemo.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;


/**
 * Dialog通用类
 * Created by dds on 2016/12/28.
 * 联信摩贝
 */

public class DialogUtil {
    private static AlertDialog alertdialog;
    private static ProgressDialog progressdialog;

    //只有一个确定按钮
    public static void showDialog(Context context, String title, String content,
                                  ICallBack okCallback) {
        showDialog(context, title, content,"确定", null, null, okCallback, null, null);
    }

    //有一个确定和取消按钮
    public static void showDialog(Context context, String title, String content,
                                  ICallBack okCallback, ICallBack cancelCallback) {
        showDialog(context, title, content, "确定", "取消", null, okCallback, cancelCallback, null);
    }

    //三个按钮 一个确定，一个取消
    public static void showDialog(Context context, String title, String content, String thirdLebal,
                                  ICallBack okCallback, ICallBack cancelCallback, ICallBack thirdCallback) {
        showDialog(context, title, content, "确定", "取消", thirdLebal, okCallback, cancelCallback, thirdCallback);
    }

    /**
     * @param context        Context 传入当前调用该方法的activity实例
     * @param title          String 标题
     * @param content        String 内容
     * @param okLabel        String 确认文字
     * @param cancelLebal    String 取消文字
     * @param thirdLebal     String 不再提示
     * @param okCallback     确认回调
     * @param cancelCallback 取消回调
     * @param thirdCallback
     */
    public static void showDialog(Context context, String title, String content,
                                  String okLabel, String cancelLebal, String thirdLebal,
                                  final ICallBack okCallback, final ICallBack cancelCallback,
                                  final ICallBack thirdCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder
                .setTitle(title)
                .setMessage(content)
                //  .setIcon(android.R.drawable.ic_dialog_alert)  //设置标题图标
                .setPositiveButton(okLabel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        if (null != okCallback) {
                            okCallback.call(null);
                        }
                        if (alertdialog != null && alertdialog.isShowing())
                            alertdialog.hide();
                    }
                })
                .setNegativeButton(cancelLebal, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (null != cancelCallback) {
                            cancelCallback.call(null);
                        }
                    }
                })
                .setNeutralButton(thirdLebal, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (null != thirdCallback) {
                            thirdCallback.call(null);
                        }
                    }
                });
        alertdialog = builder.create();
        alertdialog.show();

    }


    public static void showProgress(Context context, String msg) {
        progressdialog = new ProgressDialog(context);
        progressdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressdialog.setMessage(msg);
        progressdialog.setIndeterminate(true);
        progressdialog.setCancelable(true);
        progressdialog.show();
    }

    /**
     * 关闭ProgressDialog
     */
    public static void closeProgressDialog() {
        try {
            if (progressdialog != null) {
                progressdialog.dismiss();
            }
        } catch (Exception e) {
        }
    }


//    public static void showDialog(Context context, String title,
//                                  String content, int type, final Handler handler) {
//        showDialog(context, title, content, type, null, handler);
//    }


    /**
     * 公共弹窗
     *
     * @param context      :Context 传入当前调用该方法的activity实例
     * @param title        :String 标题
     * @param content      :String 要显示的显示文字
     * @param type         :int 显示类型1：仅为确定，2：有“确定”、“取消”两个操作,5:有三个按钮
     * @param handler      :Handler 传入的需要回调的handler信息，可作为回调方法是用，msg.what = 1时为操作完成状态符
     * @param btnOtherText :String   第三个按钮要显示的内容
     */

//    public static void showDialog(Context context, String title,
//                                  String content, int type, String btnOtherText, final Handler handler) {
//        final Dialog dialog = new Dialog(context, R.style.Dialog_loading);
//        dialog.setCancelable(true);
//        // 设置像是内容模板
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.global_dialog_tpl, null);
//        Button btnConfirm = (Button) view.findViewById(R.id.btn_confirm);// 确认
//        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);// 取消
//        Button btnOther = (Button) view.findViewById(R.id.btn_other);//第三个按钮
//        TextView dialogTitle = (TextView) view.findViewById(R.id.global_dialog_title);// 标题
//        TextView titleLine = (TextView) view.findViewById(R.id.global_dialog_title_line);
//        View divider_cancel = view.findViewById(R.id.divider_cancel);// 伴随“取消”出现的分割线
//        View divider_other = view.findViewById(R.id.divider_other);//伴随“其他”出现的分割线
//        TextView textView = (TextView) view.findViewById(R.id.setting_account_bind_text);
//
//        // 设置对话框的宽度
//        Window dialogWindow = dialog.getWindow();
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.width = (int) (context.getResources().getDisplayMetrics().density * 288);
//        dialogWindow.setAttributes(lp);
//        // 设置显示类型
//        if (type != 1 && type != 2 && type != 3 && type != 5) {
//            type = 1;
//        }
//        dialogTitle.setText(title);// 设置标题
//        textView.setText(content); // 设置提示内容
//
//        // 确认按钮操作
//        btnConfirm.setVisibility(View.VISIBLE);
//        btnConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (handler != null) {
//                    Message msg = handler.obtainMessage();
//                    msg.what = 1;
//                    handler.sendMessage(msg);
//                }
//                dialog.dismiss();
//            }
//        });
//        // 取消按钮事件
//        if (type == 2 || type == 3 || type == 5) {
//            btnCancel.setVisibility(View.VISIBLE);
//            divider_cancel.setVisibility(View.VISIBLE);
//            btnCancel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (handler != null) {
//                        Message msg = handler.obtainMessage();
//                        msg.what = 0;
//                        handler.sendMessage(msg);
//                    }
//                    dialog.dismiss();
//                }
//            });
//        }
//        if (type == 3) {
//            dialogTitle.setVisibility(View.GONE);
//            titleLine.setVisibility(View.GONE);
//        }
//
//        if (4 == type) {
//            divider_cancel.setVisibility(View.VISIBLE);
//            dialogTitle.setVisibility(View.VISIBLE);
//            titleLine.setVisibility(View.VISIBLE);
//            btnCancel.setVisibility(View.VISIBLE);
//            btnCancel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (handler != null) {
//                        Message msg = handler.obtainMessage();
//                        msg.what = 0;
//                        handler.sendMessage(msg);
//                    }
//                    dialog.dismiss();
//                }
//            });
//        }
//        if (type == 5) {
//            if (btnOtherText != null) {
//                btnOther.setText(btnOtherText);
//            }
//            btnOther.setVisibility(View.VISIBLE);
//            divider_other.setVisibility(View.VISIBLE);
//            btnOther.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (handler != null) {
//                        Message msg = handler.obtainMessage();
//                        msg.what = 2;
//                        handler.sendMessage(msg);
//                    }
//                    dialog.dismiss();
//                }
//            });
//        }
//
//        dialog.addContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT));
//        dialog.setCancelable(true);// 点击返回键关闭
//        dialog.setCanceledOnTouchOutside(false);// 点击外部关闭
//        dialog.show();
//    }


}
