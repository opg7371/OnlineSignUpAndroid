package com.homeliv.piyush.homeliv_admin;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created by piyush on 21/1/16.
 */
public class Tools {

    public static void updateDebugLabelB(final String message) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(
                new Runnable() {
                    @Override
                    public void run() {

                    }
                }
        );

    }

    public static void Toast(final String message, final Context context) {

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(
                new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

}