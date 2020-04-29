package com.jyuesong.flutter_hotpatch;

import android.content.Context;
import android.os.Looper;

import com.jyuesong.flutter_hotpatch.loader.FlutterHotPatchLoader;

import java.io.File;

import io.flutter.embedding.engine.loader.FlutterLoader;

/**
 * created by NewTab 2020/3/12
 */
public class FlutterHotPatchMain {

    public static void startInitialization(Context context) {
        startInitialization(context, null);
    }

    public static void startInitialization(Context context, File aotFile) {
        startInitialization(context, aotFile, new FlutterLoader.Settings());
    }

    public static void startInitialization(Context context, File aotFile, FlutterLoader.Settings settings) {
        if (Looper.myLooper() != Looper.getMainLooper()) return;
        if (aotFile == null || !aotFile.exists()) {
            FlutterHotPatchLoader.getInstance().startInitialization(context, settings);
        } else {
            FlutterHotPatchLoader.getInstance().startInitialization(context, aotFile, new FlutterLoader.Settings());

        }


    }

}
