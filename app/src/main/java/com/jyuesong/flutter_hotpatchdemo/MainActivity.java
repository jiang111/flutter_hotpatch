package com.jyuesong.flutter_hotpatchdemo;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jyuesong.flutter_hotpatch.FlutterHotPatchManager;

import io.flutter.embedding.android.FlutterActivity;

public class MainActivity extends AppCompatActivity {
    private static String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                // 联系人权限尚未被授予，直接请求
                ActivityCompat.requestPermissions(this, PERMISSIONS, 300);
            }
        }
        TextView fix = findViewById(R.id.fix);
        TextView start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(FlutterActivity.withNewEngine().build(MainActivity.this));

            }
        });

        fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FlutterHotPatchManager.patch(MainActivity.this,
                        Environment.getExternalStorageDirectory().getAbsolutePath(), "libapp_fix.so", new FlutterHotPatchManager.PatchInterface() {
                            @Override
                            public void success() {
                                Toast.makeText(MainActivity.this, "修复成功", Toast.LENGTH_SHORT).show();
                                startActivity(FlutterActivity.withNewEngine().build(MainActivity.this));

                            }

                            @Override
                            public void fail(String reason) {
                                Toast.makeText(MainActivity.this, reason, Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });

    }
}
