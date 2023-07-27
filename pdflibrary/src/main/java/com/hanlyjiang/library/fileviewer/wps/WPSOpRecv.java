package com.hanlyjiang.library.fileviewer.wps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public abstract class WPSOpRecv extends BroadcastReceiver {

    private static final String TAG = "WPSOpRecv";

    protected abstract Class backActivityClass();

    protected abstract void onReceiveWPSSave(Context context, Intent intent);

    void onReceiveWPSClose(Context context, Intent intent) {
        Intent newintent = new Intent(context, backActivityClass());
        newintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(newintent);
    }

    protected abstract void onReceiveWPSBack(Context context, Intent intent);

    @Override
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra(WPSModel.THIRD_PACKAGE);
        boolean isWPSBackToMe = context.getPackageName().contains(stringExtra);
        switch (intent.getAction()) {
            case WPSModel.Reciver.ACTION_BACK:
                Log.d(TAG, WPSModel.Reciver.ACTION_BACK);
                if (isWPSBackToMe) {
                    onReceiveWPSBack(context, intent);
                }
                break;
            case WPSModel.Reciver.ACTION_CLOSE:
                Log.d(TAG, WPSModel.Reciver.ACTION_CLOSE + "/" + isWPSBackToMe);
                if (isWPSBackToMe) {
                    onReceiveWPSClose(context, intent);
                }
                break;
            case WPSModel.Reciver.ACTION_HOME:
                Log.d(TAG, WPSModel.Reciver.ACTION_HOME);
                break;
            case WPSModel.Reciver.ACTION_SAVE:
                Log.d(TAG, WPSModel.Reciver.ACTION_SAVE);
                if (isWPSBackToMe) {
                    onReceiveWPSSave(context, intent);
                }
                break;
            default:
                break;
        }

    }
}

