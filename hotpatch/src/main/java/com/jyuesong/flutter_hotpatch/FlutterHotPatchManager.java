package com.jyuesong.flutter_hotpatch;

import android.app.Activity;
import android.text.TextUtils;

import java.io.File;
import java.lang.reflect.Field;

import io.flutter.embedding.engine.loader.FlutterLoader;
import io.flutter.view.FlutterMain;

/**
 * created by NewTab 2020/4/29
 */
public class FlutterHotPatchManager {


    public static void patch(Activity activity, String filePath, String fileName, PatchInterface patchInterface) {

        if (activity == null) {
            if (patchInterface != null) {
                patchInterface.fail("Context 不能为空");
            }
            return;
        }
        if (TextUtils.isEmpty(filePath) || TextUtils.isEmpty(fileName)) {
            if (patchInterface != null) {
                patchInterface.fail("文件不能为空");
            }
            return;
        }

        FileUtils.copyFile(activity.getApplicationContext().getFilesDir(), fileName, new File(filePath + File.separator + fileName), new FileUtils.CopyInterface() {
            @Override
            public void success(File destFile) {
                if (activity == null || activity.isDestroyed()) return;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        FlutterHotPatchMain.startInitialization(activity.getApplicationContext(), destFile);
                        if (patchInterface != null) {
                            patchInterface.success();
                        }
                    }
                });

            }

            @Override
            public void fail(String reason) {
                if (activity == null || activity.isDestroyed()) return;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (patchInterface != null) {
                            patchInterface.fail(reason);
                        }
                    }
                });
            }
        });


    }


    public interface PatchInterface {
        void success();

        void fail(String reason);

    }
}
