package com.lok.appcontroller;

import java.io.File;
import java.util.Random;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.lok.appcontroller.model.FileLoaderBaseVersion;

import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class Utils {
    public static String TAG = "Utils";

    /**
     * @param paramString
     * @return
     */
    public static boolean fileIsExists(String paramString) {
        return new File(paramString).exists();
    }

    /**
     * 生成指定长度的数字串
     * 
     * @param paramInt
     * @return
     */
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

    /**
     * 生成十六进制串
     * 
     * @param paramInt
     *            串长度
     * @return
     */
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

    /**
     * 生成随机IMEI
     * 
     * @return
     */
    public static String getRandomIMEI() {
        return getRandomDigit(15);
    }

    /**
     * 生成随机IMSI
     * 
     * @return
     */
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

    /**
     * 生成电话号码
     * 
     * @return
     */
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
        localStringBuilder1.append(str1);
        String str2 = getRandomDigit(8);
        localStringBuilder1.append(str2);
        return localStringBuilder1.toString();
    }

    /**
     * 生成随机串号
     * 
     * @return
     */
    public static String getRandomSERIAL() {
        StringBuilder localStringBuilder1 = new StringBuilder();
        String str = getRandomString(new Random().nextInt(15) % 11 + 5);
        localStringBuilder1.append(str);
        return localStringBuilder1.toString();
    }

    /**
     * 生成随机sim卡串行号
     * 
     * @return
     */
    public static String getRandomSimSerialNumber() {
        StringBuilder localStringBuilder1 = new StringBuilder();
        Random localRandom = new Random();
        String[] arrayOfString = new String[2];
        arrayOfString[0] = "898600";
        arrayOfString[1] = "898601";
        int i = arrayOfString.length;
        int j = localRandom.nextInt(i);
        String str1 = arrayOfString[j];
        localStringBuilder1.append(str1);
        String str2 = getRandomDigit(14);
        localStringBuilder1.append(str2);
        return localStringBuilder1.toString();
    }

    /**
     * 生成随机字符串
     * 
     * @param paramInt
     * @return
     */
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
            localStringBuffer1.append(c);
            i += 1;
        }
    }

    /**
     * modify IMEI
     * 
     * @param lpparam
     */
    public static void handleIMEI(final LoadPackageParam lpparam) {
        try {
            findAndHookMethod(TelephonyManager.class, "getDeviceId",
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param)
                                throws Throwable {
                            Log.d(TAG + "-" + "handleIMEI", new Exception()
                                    .getStackTrace()[0].getMethodName());
                            String IMEI_VAL = "";
                            try {
                                FileLoaderBaseVersion imeiLoader = new FileLoaderBaseVersion(
                                        AppsParameters.PARAM_FILE_PATH);
                                IMEI_VAL = imeiLoader.loadKeyValue("IMEI");
                            } catch (Exception e) {
                                IMEI_VAL = getRandomIMEI();
                            } finally {
                                Log.d(TAG, "Replace value:" + IMEI_VAL);
                                param.setResult(IMEI_VAL);
                            }
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, lpparam.packageName + " has error:" + e.toString());
        }
    }

    /**
     * @param lpparam
     * @param ctrlFilePath
     */
    public static void handleIMSI(final LoadPackageParam lpparam,
            String ctrlFilePath) {
        try {
            findAndHookMethod(TelephonyManager.class, "getSubscriberId",
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param)
                                throws Throwable {
                            String IMEI = Utils.getRandomIMEI();
                            Log.d(TAG, IMEI);
                            param.setResult(IMEI);
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, lpparam.packageName + " has error:" + e.toString());
        }
    }

    /**
     * modify MODEL
     * @param lpparam
     */
    public static void handleMODEL(XC_LoadPackage.LoadPackageParam lpparam) {
        try {
            Log.d(TAG + "-" + "handleMODEL",
                    new Exception().getStackTrace()[0].getMethodName());
            String MODEL_VAL = "";
            try {
                FileLoaderBaseVersion imeiLoader = new FileLoaderBaseVersion(
                        AppsParameters.PARAM_FILE_PATH);
                MODEL_VAL = imeiLoader.loadKeyValue("MODEL");
            } catch (Exception e) {
                MODEL_VAL = getRandomSERIAL();
            } finally {
                XposedHelpers.setStaticObjectField(Build.class, "MODEL",
                        MODEL_VAL);
            }
        } catch (Exception e) {
            Log.e(TAG, lpparam.packageName + " has error:" + e.toString());
        }
    }
    
    /**
     * modify SERIAL
     * @param lpparam
     */
    public static void handleSERIAL(XC_LoadPackage.LoadPackageParam lpparam) {
        try {
            Log.d(TAG + "-" + "handleSERIAL",
                    new Exception().getStackTrace()[0].getMethodName());
            String SERIAL_VAL = "";
            try {
                FileLoaderBaseVersion imeiLoader = new FileLoaderBaseVersion(
                        AppsParameters.PARAM_FILE_PATH);
                SERIAL_VAL = imeiLoader.loadKeyValue("SERIAL");
            } catch (Exception e) {
                SERIAL_VAL = getRandomSERIAL();
            } finally {
                XposedHelpers.setStaticObjectField(Build.class, "SERIAL",
                        SERIAL_VAL);
            }

            // XposedHelpers.setStaticObjectField(Build.class, "MODEL",
            // AppsParameters.MODEL_VAL);
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
        AppsParameters.MACADDR_VAL = "12:12:12:12:12:12";
        AppsParameters.SERIAL_VAL = getRandomSERIAL();
        AppsParameters.PHONE_NUM = getRandomPHONENUM();
        AppsParameters.MODEL = getRandomSERIAL();
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