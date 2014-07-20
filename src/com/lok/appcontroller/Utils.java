package com.lok.appcontroller;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Random;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.util.List;

import de.robv.android.xposed.IXposedHookCmdInit;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
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

public class Utils {
    /*
     * 处理类 处理各种参数的修改
     */
    public static String TAG = "whitelok_Utils";

    // 修改手机上所有参数
    public static void changeValue(String paramPath) throws Exception {
	// 初始化读取文件的配置
	FileInputStream fistream = new FileInputStream(paramPath);
	InputStreamReader istreamReader = new InputStreamReader(fistream);
	BufferedReader bufferReader = new BufferedReader(istreamReader);

	// 初始时读取第一行
	String line = bufferReader.readLine();
	while (line != null) {
	    // 循环读取一行
	    line = bufferReader.readLine();
	    // 整理行的字符串符号
	    line = line.replace("\r", "").replace("\n", "");

	    // 读取各种参数

	    // 读取IMEI
	    if (line.startsWith("IMEI")) {
		AppsParameters.IMEI_VAL = line.split("=")[1];
	    }
	    // 读取IMSI
	    if (line.startsWith("IMSI")) {
		AppsParameters.IMSI_VAL = line.split("=")[1];
	    }
	    // 读取SERIAL
	    if (line.startsWith("SERIAL")) {
		AppsParameters.SERIAL_VAL = line.split("=")[1];
	    }
	    // 读取MAC地址
	    if (line.startsWith("MACADDR")) {
		AppsParameters.MACADDR_VAL = line.split("=")[1];
	    }
	    // 读取电话号码
	    if (line.startsWith("PHONE_NUM")) {
		AppsParameters.PHONE_NUM = line.split("=")[1];
	    }
	    // 读取MODEL
	    if (line.startsWith("MODEL")) {
		AppsParameters.MODEL_VAL = line.split("=")[1];
	    }
	    // 读取ID
	    if (line.startsWith("ID")) {
		AppsParameters.ID_VAL = line.split("=")[1];
	    }
	    // 读取DISPLAY
	    if (line.startsWith("DISPLAY")) {
		AppsParameters.DISPLAY_VAL = line.split("=")[1];
	    }
	    // 读取PRODUCT
	    if (line.startsWith("PRODUCT")) {
		AppsParameters.PRODUCT_VAL = line.split("=")[1];
	    }
	    // 读取DEVICE
	    if (line.startsWith("DEVICE")) {
		AppsParameters.DEVICE_VAL = line.split("=")[1];
	    }
	    // 读取BOARD
	    if (line.startsWith("BOARD")) {
		AppsParameters.BOARD_VAL = line.split("=")[1];
	    }
	    // 读取CPU_AMI
	    if (line.startsWith("CPU_AMI")) {
		AppsParameters.CPU_ABI_VAL = line.split("=")[1];
	    }
	    // 读取CPU_ABI2
	    if (line.startsWith("CPU_ABI2")) {
		AppsParameters.CPU_ABI2_VAL = line.split("=")[1];
	    }
	    // 读取MANUFACTURER
	    if (line.startsWith("MANUFACTURER")) {
		AppsParameters.MANUFACTURER_VAL = line.split("=")[1];
	    }
	    // 读取NetworkOperatorName
	    if (line.startsWith("NetworkOperatorName")) {
		AppsParameters.NetworkOperatorName_VAL = line.split("=")[1];
	    }
	    // 读取NetworkOperator
	    if (line.startsWith("NetworkOperator")) {
		AppsParameters.NetworkOperator_VAL = line.split("=")[1];
	    }
	    // 读取NetworkType
	    if (line.startsWith("NetworkType")) {
		AppsParameters.NetworkType_VAL = line.split("=")[1];
	    }
	    // 读取Sim卡SerialNumber
	    if (line.startsWith("SimSerialNumber")) {
		AppsParameters.SimSerialNumber_VAL = line.split("=")[1];
	    }
	    // 读取Country
	    if (line.startsWith("Country")) {
		AppsParameters.Country_VAL = line.split("=")[1];
	    }
	    // 读取Language
	    if (line.startsWith("Language")) {
		AppsParameters.Language_VAL = line.split("=")[1];
	    }
	    // 读取SecureString
	    if (line.startsWith("SecureString")) {
		AppsParameters.SecureString_VAL = line.split("=")[1];
	    }
	    // 读取VERSION_RELEASE
	    if (line.startsWith("VERSION_RELEASE")) {
		AppsParameters.RELEASE_VAL = line.split("=")[1];
	    }
	    // 读取BRAND
	    if (line.startsWith("BRAND")) {
		AppsParameters.BRAND_VAL = line.split("=")[1];
	    }
	}
    }

    public static boolean fileIsExists(String paramString) {
	return new File(paramString).exists();
    }

    // 生成指定长度的数字串
    public static String getRandomDigit(int paramInt) {
	Random random = new Random();
	StringBuilder result = new StringBuilder();
	String digitArray = "0123456789";
	int digitLen = digitArray.length();
	for (int i = 0; i < paramInt; i++) {
	    result.append("0123456789".charAt(random.nextInt(digitLen)));
	}
	return result.toString();
    }

    // 生成十六进制串
    public static String getRandomHEXString(int paramInt) {
	Random random = new Random();
	StringBuilder result = new StringBuilder();
	String digitArray = "ABCDEF0123456789";
	int digitLen = digitArray.length();
	for (int i = 0; i < paramInt; i++) {
	    result.append("ABCDEF0123456789".charAt(random.nextInt(digitLen)));
	}
	return result.toString();
    }

    // 生成IMEI
    public static String getRandomIMEI() {
	return getRandomDigit(15);
    }

    // 生成IMSI
    public static String getRandomIMSI() {
	StringBuilder result = new StringBuilder();
	result.append("4600");
	Random localRandom = new Random();
	int i = "01237".length();
	int j = localRandom.nextInt(i);
	char c = "01237".charAt(j);
	result.append(c);
	String str = getRandomDigit(11);
	result.append(str);
	return result.toString();
    }

    // 生成MAC地址
    public static String getRandomMACAddr() {
	StringBuffer localStringBuffer1 = new StringBuffer();
	int i = 0;
	while (true) {
	    if (i >= 6)
		return localStringBuffer1.toString();
	    String str = getRandomHEXString(2);
	    StringBuffer localStringBuffer2 = localStringBuffer1.append(str);
	    i += 1;
	}
    }

    // 生成电话号码
    public static String getRandomPHONENUM() {
	StringBuilder localStringBuilder1 = new StringBuilder();
	Random localRandom = new Random();
	String[] arrayOfString = new String[31];
	arrayOfString[0] = "133";
	arrayOfString[1] = "153";
	arrayOfString[2] = "180";
	arrayOfString[3] = "182";
	arrayOfString[4] = "189";
	arrayOfString[5] = "130";
	arrayOfString[6] = "131";
	arrayOfString[7] = "132";
	arrayOfString[8] = "145";
	arrayOfString[9] = "155";
	arrayOfString[10] = "156";
	arrayOfString[11] = "185";
	arrayOfString[12] = "186";
	arrayOfString[13] = "134";
	arrayOfString[14] = "135";
	arrayOfString[15] = "136";
	arrayOfString[16] = "137";
	arrayOfString[17] = "138";
	arrayOfString[18] = "139";
	arrayOfString[19] = "147";
	arrayOfString[20] = "150";
	arrayOfString[21] = "151";
	arrayOfString[22] = "152";
	arrayOfString[23] = "157";
	arrayOfString[24] = "158";
	arrayOfString[25] = "159";
	arrayOfString[26] = "182";
	arrayOfString[27] = "183";
	arrayOfString[28] = "184";
	arrayOfString[29] = "187";
	arrayOfString[30] = "188";
	int i = arrayOfString.length;
	int j = localRandom.nextInt(i);
	String str1 = arrayOfString[j];
	StringBuilder localStringBuilder2 = localStringBuilder1.append(str1);
	String str2 = getRandomDigit(8);
	StringBuilder localStringBuilder3 = localStringBuilder1.append(str2);
	return localStringBuilder1.toString();
    }

    // 生成串号
    public static String getRandomSERIAL() {
	StringBuilder localStringBuilder1 = new StringBuilder();
	String str = getRandomString(new Random().nextInt(15) % 11 + 5);
	StringBuilder localStringBuilder2 = localStringBuilder1.append(str);
	return localStringBuilder1.toString();
    }

    // 生成sim卡串行号
    public static String getRandomSimSerialNumber() {
	StringBuilder localStringBuilder1 = new StringBuilder();
	Random localRandom = new Random();
	String[] arrayOfString = new String[2];
	arrayOfString[0] = "898600";
	arrayOfString[1] = "898601";
	int i = arrayOfString.length;
	int j = localRandom.nextInt(i);
	String str1 = arrayOfString[j];
	StringBuilder localStringBuilder2 = localStringBuilder1.append(str1);
	String str2 = getRandomDigit(14);
	StringBuilder localStringBuilder3 = localStringBuilder1.append(str2);
	return localStringBuilder1.toString();
    }

    // 生成随机字符串
    public static String getRandomString(int paramInt) {
	Random localRandom = new Random();
	StringBuffer localStringBuffer1 = new StringBuffer();
	int i = 0;
	while (true) {
	    if (i >= paramInt)
		return localStringBuffer1.toString();
	    int j = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".length();
	    int k = localRandom.nextInt(j);
	    char c = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(k);
	    StringBuffer localStringBuffer2 = localStringBuffer1.append(c);
	    i += 1;
	}
    }

    // 修改IMEI
    public static void handleIMEI(final LoadPackageParam lpparam,
	    String ctrlFilePath) {
	try {
	    findAndHookMethod(TelephonyManager.class, "getDeviceId",
		    new XC_MethodHook() {
			@Override
			protected void afterHookedMethod(MethodHookParam param)
				throws Throwable {
			    param.setResult(AppsParameters.IMEI_VAL);
			}
		    });
	} catch (Exception e) {
	    Log.e(TAG, lpparam.packageName + " has error:" + e.toString());
	}
    }

    public static void handleIMSI(final LoadPackageParam lpparam,
	    String ctrlFilePath) {
	try {
	    findAndHookMethod(TelephonyManager.class, "getSubscriberId",
		    new XC_MethodHook() {
			@Override
			protected void afterHookedMethod(MethodHookParam param)
				throws Throwable {
			    param.setResult(AppsParameters.IMSI_VAL);
			}
		    });
	} catch (Exception e) {
	    Log.e(TAG, lpparam.packageName + " has error:" + e.toString());
	}

    }

    public static void handleSERIAL(XC_LoadPackage.LoadPackageParam lpparam,
	    String ctrlFilePath) {
	try {
	    XposedHelpers.setStaticObjectField(Build.class, "SERIAL",
		    AppsParameters.SERIAL_VAL);
	    XposedHelpers.setStaticObjectField(Build.class, "MODEL",
		    AppsParameters.MODEL_VAL);
	} catch (Exception e) {
	    Log.e(TAG, lpparam.packageName + " has error:" + e.toString());
	}
    }

    public static void handleSimSerialNumber(final LoadPackageParam lpparam,
	    String ctrlFilePath) {

    }

    public static void initValues() {
	AppsParameters.IMEI_VAL = getRandomIMEI();
	AppsParameters.IMSI_VAL = getRandomIMSI();
	AppsParameters.MACADDR_VAL = getRandomMACAddr();
	AppsParameters.SERIAL_VAL = getRandomSERIAL();
	AppsParameters.PHONE_NUM = getRandomPHONENUM();
	AppsParameters.MODEL_VAL = getRandomSERIAL();
	AppsParameters.ID_VAL = getRandomSERIAL();
	AppsParameters.DISPLAY_VAL = getRandomSERIAL();
	AppsParameters.PRODUCT_VAL = getRandomSERIAL();
	AppsParameters.DEVICE_VAL = getRandomSERIAL();
	AppsParameters.BOARD_VAL = getRandomSERIAL();
	AppsParameters.CPU_ABI_VAL = getRandomSERIAL();
	AppsParameters.CPU_ABI2_VAL = getRandomSERIAL();
	AppsParameters.MANUFACTURER_VAL = getRandomSERIAL();
	AppsParameters.RELEASE_VAL = getRandomSERIAL();
	AppsParameters.NetworkOperatorName_VAL = "g12";
	AppsParameters.NetworkOperator_VAL = "46000";
	AppsParameters.NetworkType_VAL = getRandomSimSerialNumber();
	AppsParameters.Country_VAL = "CN";
	AppsParameters.Language_VAL = "zh";
	AppsParameters.SecureString_VAL = "g12:fc70efb73ffd0a35";
	AppsParameters.SimSerialNumber_VAL = getRandomSimSerialNumber();
    }
}