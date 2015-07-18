package com.lok.appcontroller.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class FileLoader extends Thread {
    private static String TAG = "FileLoader";

    public final static int LOAD_FILE_BEGIN = 0x0000001;
    public final static int LOAD_FILE_SUCCESS = 0x0000002;
    public final static int LOAD_FILE_FAILED = 0x0000003;

    private String filePath = null;
    private Handler handler = null;

    public FileLoader(final String filePath, Handler handler) {
        this.filePath = filePath;
        this.handler = handler;
    }

    public void run() {
        Message msgBegin = new Message();
        msgBegin.what = LOAD_FILE_BEGIN;
        handler.sendMessage(msgBegin);

        File file = new File(filePath);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        String result = "";

        Message msgFailed = new Message();
        msgFailed.what = LOAD_FILE_FAILED;
        Bundle failData = new Bundle();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
                result = result + "\n" + s;
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(pw);
            failData.putString("ERROR_INFO", sw.toString());
            msgFailed.setData(failData);
            handler.sendMessage(msgFailed);
            return;
        } catch (IOException e) {
            e.printStackTrace(pw);
            failData.putString("ERROR_INFO", sw.toString());
            msgFailed.setData(failData);
            handler.sendMessage(msgFailed);
            return;
        }

        Message msgSuccess = new Message();
        msgSuccess.what = LOAD_FILE_SUCCESS;
        Bundle successData = new Bundle();
        successData.putString("SUCCESS_DATA", result);
        msgSuccess.setData(successData);
        handler.sendMessage(msgSuccess);
    }
}
