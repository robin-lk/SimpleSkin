package com.robin.skins;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * @author wjunjie 2022/7/7
 * 皮肤加载器
 */
public class SkinLoader {
    public String skinPackage;
    public Resources skinResources;
    public String shinPath;

    /**
     * @param skinPath 皮肤apk包
     */
    public boolean loaderSkin(Context context, String skinPath) {
        release();
        if (context == null || TextUtils.isEmpty(skinPath)) return false;
        this.shinPath = skinPath;
        //包管理器
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageArchiveInfo = packageManager.getPackageArchiveInfo(skinPath, PackageManager.GET_ACTIVITIES);
        //皮肤所在的包名
        if (packageArchiveInfo == null) return false;
        skinPackage = packageArchiveInfo.packageName;
        try {
            //通过反射获取assetManager对象
            AssetManager assetManager = AssetManager.class.newInstance();
            //通过反射获取addAssetPath方向
            Method addAssetPath = assetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, skinPath);
            //得到了资源包里面的资源对象
            skinResources = new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
            Log.i("skin", "皮肤加载成功");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 释放资源
     */
    private void release() {
        shinPath = null;
        skinResources = null;
        skinPackage = null;
    }
}
