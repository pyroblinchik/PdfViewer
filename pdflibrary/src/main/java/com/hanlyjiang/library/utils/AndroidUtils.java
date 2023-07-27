package com.hanlyjiang.library.utils;

import android.content.Context;
import android.widget.Toast;


public class AndroidUtils {

    public static void showToast(Context context, String msg) {
        if (context == null) return;
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, int msgResId) {
        if (context == null) return;
        Toast.makeText(context, context.getResources().getString(msgResId), Toast.LENGTH_SHORT).show();
    }
}
