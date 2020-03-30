package com.jyuesong.flutter_hotpatch;

import android.content.Context;
import android.os.Looper;

import com.jyuesong.flutter_hotpatch.loader.HotPatchFlutterLoader;

import java.io.File;

import io.flutter.embedding.engine.loader.FlutterLoader;
import io.flutter.view.FlutterMain;

/**
 * created by NewTab 2020/3/12
 */
public class HotPatchFlutterMain {

    public static void startInitialization(Context context) {
        startInitialization(context, null);
    }

    public static void startInitialization(Context context, File aotFile) {
        startInitialization(context, aotFile, new FlutterLoader.Settings());
    }

    public static void startInitialization(Context context, File aotFile, FlutterLoader.Settings settings) {
        if (Looper.myLooper() != Looper.getMainLooper()) return;
        if (aotFile == null || !aotFile.exists()) {
            HotPatchFlutterLoader.getInstance().startInitialization(context, settings);
        } else {
            HotPatchFlutterLoader.getInstance().startInitialization(context, aotFile, new FlutterLoader.Settings());

        }


    }

}
