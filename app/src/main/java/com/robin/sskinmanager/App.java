package com.robin.sskinmanager;

import android.app.Application;

import com.robin.skins.SkinManager;

import java.io.File;

/**
 * @author wjunjie 2022/7/8
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        File file = new File(getExternalCacheDir(), "app-debug.apk");
        SkinManager
                .getInstance()
                .init(this, true)
                .appendFontSize(12)
                .loaderApkRes("ww");
    }
}
