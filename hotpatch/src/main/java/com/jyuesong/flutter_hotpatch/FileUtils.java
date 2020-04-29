package com.jyuesong.flutter_hotpatch;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.channels.FileChannel;

/**
 * created by NewTab 2020/3/12
 */
public class FileUtils {
    private static final String TAG = "FileUtils";

    public static void copyFile(File dir, String destFileName, File source, CopyInterface copyInterface) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!dir.exists()) {
                    if (dir.mkdirs()) {
                        Log.i(TAG, "mkdirs success: " + dir.getAbsolutePath());
                    } else {
                        Log.i(TAG, "mkdirs failure: " + dir.getAbsolutePath());
                    }
                } else {
                    Log.i(TAG, "dirs exists: " + dir.getAbsolutePath());
                }
                try {
                    File dest = new File(dir, destFileName);
                    if (dest.exists() && !dest.delete()) {
                        //存在就先清空
                        FileWriter writer = new FileWriter(dest, false);
                        writer.write("");
                        writer.flush();
                        writer.close();
                    }

                    FileChannel inputChannel = new FileInputStream(source).getChannel();
                    FileChannel outputChannel = new FileOutputStream(dest).getChannel();
                    outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
                    inputChannel.close();
                    outputChannel.close();

                    Log.i(TAG, "copy  file finish: " + dest.getAbsolutePath());

                    if (copyInterface != null) {
                        copyInterface.success(dest);
                    }
                } catch (Throwable error) {
                    error.printStackTrace();
                    if (copyInterface != null) {
                        copyInterface.fail(error.getMessage());
                    }
                }
            }
        }).start();

    }

    public interface CopyInterface {
        void success(File destFile);

        void fail(String reason);

    }
}
