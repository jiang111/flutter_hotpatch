package com.jyuesong.flutter_hotpatchdemo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Environment;

import com.jyuesong.flutter_hotpatch.FlutterHotPatchManager;

import java.io.File;
import java.lang.reflect.Method;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;

/**
 * created by NewTab 2020/4/29
 */
public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
//                if (activity instanceof FlutterActivity) {
//                    try {
//                        FlutterActivity flutterActivity = (FlutterActivity) activity;
//                        Method flutterEngineMethod = FlutterActivity.class.getDeclaredMethod("getFlutterEngine");
//                        flutterEngineMethod.setAccessible(true);
//
//                        FlutterEngine flutterEngine = (FlutterEngine) flutterEngineMethod.invoke(flutterActivity);
//                        FlutterHotPatchManager.patchResource(activity, flutterEngine, Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "hotpatch-resource.zip");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

    }
}
