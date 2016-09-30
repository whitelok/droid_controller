package com.lok.appcontroller;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class CtrlMain implements IXposedHookZygoteInit, IXposedHookLoadPackage{

    public static final String TAG = "CtrlMain";

    public static void handling(
            XC_LoadPackage.LoadPackageParam paramLoadPackageParam) {
        Log.d(TAG, new Exception().getStackTrace()[0].getMethodName());

        // change IMEI if has
        Utils.handleIMEI(paramLoadPackageParam);
        // change IMSI if has
        Utils.handleIMSI(paramLoadPackageParam);
        // change SERIAL if has
        Utils.handleSERIAL(paramLoadPackageParam);
        // change MODEL if has
        Utils.handleMODEL(paramLoadPackageParam);
        // change PhoneNumber if has
        Utils.handlePhoneNumber(paramLoadPackageParam);
    }

    public void handleLoadPackage(
            XC_LoadPackage.LoadPackageParam paramLoadPackageParam)
            throws Throwable {
        Log.d(TAG, new Exception().getStackTrace()[0].getMethodName());
        handling(paramLoadPackageParam);
    }

//    public void initCmdApp(IXposedHookCmdInit.StartupParam paramStartupParam)
//            throws Throwable {
//        Log.d(TAG, new Exception().getStackTrace()[0].getMethodName());
//    }

    public void initZygote(IXposedHookZygoteInit.StartupParam paramStartupParam)
            throws Throwable {
        Log.d(TAG, new Exception().getStackTrace()[0].getMethodName());
    }
}