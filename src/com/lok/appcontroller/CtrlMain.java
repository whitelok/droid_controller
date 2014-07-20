package com.lok.appcontroller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.util.List;
import java.util.Vector;

import de.robv.android.xposed.IXposedHookCmdInit;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.*;
import de.robv.android.xposed.XposedHelpers;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;
import de.robv.android.xposed.XC_MethodHook;
import android.os.Build;

public class CtrlMain implements IXposedHookZygoteInit, IXposedHookLoadPackage,
	IXposedHookCmdInit {

    public static final String TAG = "whitelok_CtrlMain";
    public static String ctrlFilePath = "/data/change.set";

    public static void handling(
	    XC_LoadPackage.LoadPackageParam paramLoadPackageParam,
	    String ctrlFilePath) {
	// 先修改参数
	try {
	    Utils.changeValue(ctrlFilePath);
	} catch (Exception e) {
	    Log.e(TAG,
		    paramLoadPackageParam.packageName + " has error:"
			    + e.toString());
	}
	// 修改IMEI
	Utils.handleIMEI(paramLoadPackageParam, ctrlFilePath);
	// 修改Build.SERIAL
	Utils.handleSERIAL(paramLoadPackageParam, ctrlFilePath);
	// 修改IMSI
	Utils.handleIMSI(paramLoadPackageParam, ctrlFilePath);
	// 修改Sim卡上的SerialNumber
	Utils.handleSimSerialNumber(paramLoadPackageParam, ctrlFilePath);
    }

    // 驱动入口
    public void handleLoadPackage(
	    XC_LoadPackage.LoadPackageParam paramLoadPackageParam)
	    throws Throwable {
	// 判断是否存在配置文件 若不存在 则使用系统原来参数
	if (!Utils.fileIsExists(ctrlFilePath)) {
	    // 输出警告
	    Log.w(TAG, "The configuration file:" + ctrlFilePath
		    + " does not exists.");
	    // 配置文件不存在直接返回，不作修改
	    return;
	} else
	    // 修改参数
	    handling(paramLoadPackageParam, ctrlFilePath);
    }

    public void initCmdApp(IXposedHookCmdInit.StartupParam paramStartupParam)
	    throws Throwable {
	// 初始化APP
	Log.i(TAG, "initCmdApp");
	// 初始化默认参数
	Utils.initValues();
    }

    public void initZygote(IXposedHookZygoteInit.StartupParam paramStartupParam)
	    throws Throwable {
	// 初始化系统输出
	Log.i(TAG, "initZygote");
    }
}