package com.jyuesong.flutter_hotpatch.loader;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;

import io.flutter.BuildConfig;

/**
 * created by NewTab 2020/3/10
 */
public class ResourceCleaner {

    private static final String TAG = "ResourceCleaner";
    private static final long DELAY_MS = 5000;

    private static class CleanTask extends AsyncTask<Void, Void, Void> {
        private final File[] mFilesToDelete;

        CleanTask(File[] filesToDelete) {
            mFilesToDelete = filesToDelete;
        }

        boolean hasFilesToDelete() {
            return mFilesToDelete != null && mFilesToDelete.length > 0;
        }

        @Override
        protected Void doInBackground(Void... unused) {
            if (BuildConfig.DEBUG) {
                Log.i(TAG, "Cleaning " + mFilesToDelete.length + " resources.");
            }
            for (File file : mFilesToDelete) {
                if (file.exists()) {
                    deleteRecursively(file);
                }
            }
            return null;
        }

        private void deleteRecursively(File parent) {
            if (parent.isDirectory()) {
                for (File child : parent.listFiles()) {
                    deleteRecursively(child);
                }
            }
            parent.delete();
        }
    }

    private final Context mContext;

    ResourceCleaner(Context context) {
        mContext = context;
    }

    void start() {
        File cacheDir = mContext.getCacheDir();
        if (cacheDir == null) {
            return;
        }

        final ResourceCleaner.CleanTask task = new ResourceCleaner.CleanTask(cacheDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                boolean result = name.startsWith(ResourcePaths.TEMPORARY_RESOURCE_PREFIX);
                return result;
            }
        }));

        if (!task.hasFilesToDelete()) {
            return;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        }, DELAY_MS);
    }
}
