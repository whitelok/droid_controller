package com.lok.appcontroller;

import de.robv.android.xposed.IXposedHookCmdInit;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import android.util.Log;

public class CtrlMain implements IXposedHookZygoteInit, IXposedHookLoadPackage,
        IXposedHookCmdInit {

    public static final String TAG = "CtrlMain";

    public static void handling(
            XC_LoadPackage.LoadPackageParam paramLoadPackageParam) {
        Log.d(TAG, new Exception().getStackTrace()[0].getMethodName());

        // change IMEI if has
        Utils.handleIMEI(paramLoadPackageParam);
        // change SERIAL if has
        Utils.handleSERIAL(paramLoadPackageParam);
        // change MODEL if has
        Utils.handleMODEL(paramLoadPackageParam);
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
    }

    public void initZygote(IXposedHookZygoteInit.StartupParam paramStartupParam)
            throws Throwable {
        Log.d(TAG, new Exception().getStackTrace()[0].getMethodName());
    }
}