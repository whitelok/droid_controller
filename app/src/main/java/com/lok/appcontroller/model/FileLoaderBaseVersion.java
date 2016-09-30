package com.lok.appcontroller.model;

import android.util.Log;

import com.lok.appcontroller.AppsParameters;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class FileLoaderBaseVersion extends Thread {
    private static String TAG = "FileLoader";

    private String filePath = null;
    private String result = "";
    private boolean isSuccess = false;

    public FileLoaderBaseVersion(final String filePath) {
        this.filePath = filePath;
    }

    public void run() {
        File file = new File(filePath);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        result = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
                result = result + "\n" + s;
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(pw);
            Log.e(TAG, sw.toString());
            return;
        } catch (IOException e) {
            e.printStackTrace(pw);
            Log.e(TAG, sw.toString());
            return;
        }
        isSuccess = true;
        Log.d(TAG, result);
        try {
            JSONObject resultObj = new JSONObject(result);
            if (AppsParameters.params.get("IMEI") != null) {
                AppsParameters.params.remove("IMEI");
            }
            AppsParameters.params.put("IMEI", resultObj.getString("IMEI"));
            AppsParameters.IMEI_VAL = resultObj.getString("IMEI");
            Log.d(TAG, "Loaded IMEI:" + resultObj.getString("IMEI"));
        } catch (JSONException e) {
            e.printStackTrace(pw);
            Log.e(TAG, sw.toString());
        }
    }

    public String loadKeyValue(String key) {
        File file = new File(filePath);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        result = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
                result = result + "\n" + s;
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(pw);
            return sw.toString();
        } catch (IOException e) {
            e.printStackTrace(pw);
            return sw.toString();
        }
        isSuccess = true;
        Log.d(TAG, result);
        try {
            JSONObject resultObj = new JSONObject(result);
            return resultObj.getString(key);
        } catch (JSONException e) {
            e.printStackTrace(pw);
            return sw.toString();
        }
    }

    synchronized public String getResult() {
        if (isSuccess) {
            return result;
        } else {
            return "";
        }
    }

    synchronized public void setResult(String result) {
        this.result = result;
    }

    synchronized public boolean isSuccess() {
        return isSuccess;
    }

    synchronized public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
