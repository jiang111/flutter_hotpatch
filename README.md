
```

FileUtils.copyFile(destDir, destFileName, source, new FileUtils.CopyInterface() {
                    @Override
                    public void success(File destFile) {
                        if (activity == null || activity.isDestroyed()) return;
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                HotPatchFlutterMain.startInitialization(activity.getApplicationContext(), destFile);
                                if (patchCallBack != null) {
                                    patchCallBack.success();
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
                                HotPatchFlutterMain.startInitialization(activity.getApplicationContext());
                                if (patchCallBack != null) {
                                    patchCallBack.success();
                                }
                            }
                        });
                    }
                });
```