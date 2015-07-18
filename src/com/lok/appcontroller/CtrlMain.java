package com.lok.appcontroller;

import com.lok.appcontroller.model.FileLoaderBaseVersion;

import de.robv.android.xposed.IXposedHookCmdInit;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class CtrlMain implements IXposedHookZygoteInit, IXposedHookLoadPackage,
        IXposedHookCmdInit {

    public static final String TAG = "CtrlMain";
    public static String ctrlFilePath = "/data/setting.json";

    public static void handling(
            XC_LoadPackage.LoadPackageParam paramLoadPackageParam) {
        Log.d(TAG, new Exception().getStackTrace()[0].getMethodName());
        try {
            FileLoaderBaseVersion initLoder = new FileLoaderBaseVersion(
                    ctrlFilePath);
            initLoder.start();
            initLoder.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Utils.handleIMEI(paramLoadPackageParam);
    }

    public void handleLoadPackage(
            XC_LoadPackage.LoadPackageParam paramLoadPackageParam)
            throws Throwable {
        Log.d(TAG, new Exception().getStackTrace()[0].getMethodName());
        handling(paramLoadPackageParam);
    }

    public void initCmdApp(IXposedHookCmdInit.StartupParam paramStartupParam)
            throws Throwable {
        Log.d(TAG, new Exception().getStackTrace()[0].getMethodName());
        try {
            FileLoaderBaseVersion initLoder = new FileLoaderBaseVersion(
                    ctrlFilePath);
            initLoder.start();
            initLoder.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void initZygote(IXposedHookZygoteInit.StartupParam paramStartupParam)
            throws Throwable {
        // Log.d(TAG, new Exception().getStackTrace()[0].getMethodName());
    }

    // private static Handler ctrlMainHandler = new Handler() {
    // public void handleMessage(Message msg) {
    // switch (msg.what) {
    // case FileLoader.LOAD_FILE_BEGIN:
    // Log.d(TAG, "Begin load param");
    // break;
    // case FileLoader.LOAD_FILE_FAILED:
    // Bundle failedData = msg.getData();
    // Log.d(TAG,
    // "Load param failed:"
    // + failedData.getString("ERROR_INFO"));
    // break;
    // case FileLoader.LOAD_FILE_SUCCESS:
    // Bundle successData = msg.getData();
    // Log.d(TAG,
    // "Load original params success:"
    // + successData.getString("SUCCESS_DATA"));
    // break;
    // }
    // }
    // };
}