package com.jyuesong.flutter_hotpatchdemo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jyuesong.flutter_hotpatch.HotPatchFlutterMain;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HotPatchFlutterMain.startInitialization(this, new File(""));

    }
}
