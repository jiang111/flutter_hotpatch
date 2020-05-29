package com.jyuesong.flutter_hotpatchdemo;

import android.os.Environment;
import android.support.annotation.NonNull;

import com.jyuesong.flutter_hotpatch.FlutterHotPatchManager;

import java.io.File;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.android.FlutterEngineConfigurator;
import io.flutter.embedding.engine.FlutterEngine;

/**
 * created by NewTab 2020/5/29
 */
class PatchActivity extends FlutterActivity {


    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        try {
            FlutterHotPatchManager.patchResource(this, flutterEngine, Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "hotpatch-resource.zip");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
