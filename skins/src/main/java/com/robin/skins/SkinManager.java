package com.robin.skins;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.ArrayMap;
import android.view.LayoutInflater;

import androidx.core.view.LayoutInflaterCompat;

import java.util.Map;

/**
 * @author wjunjie 2022/7/8
 */
public class SkinManager {
    private static SkinManager sm;
    private Context context;
    public SkinLoader skinLoader;
    //及时换肤
    public boolean changeNow;

    private final Map<Activity, SkinWidget> skinUi = new ArrayMap<>();

    public static SkinManager getInstance() {
        if (sm == null) sm = new SkinManager();
        return sm;
    }

    public SkinManager init(Context context, boolean changeNow) {
        this.changeNow = changeNow;
        this.context = context;
        skinUi.clear();
        return getInstance();
    }

    public boolean loaderApkRes(String apkPath) {
        if (skinLoader == null) skinLoader = new SkinLoader();
        boolean b = skinLoader.loaderSkin(context, apkPath);
        if (!b) skinLoader = null;
        if (b) changeNow = true;
        return b;
    }

    public void addUI(Activity activity) {
        if (activity != null) {
            SkinFactory skinFactory = new SkinFactory();
            LayoutInflaterCompat.setFactory2(LayoutInflater.from(activity), skinFactory);
            if (skinFactory.skinWidget != null)
                skinUi.put(activity, skinFactory.skinWidget);

        }
    }

    public void removeUI(Activity activity) {
        if (activity != null)
            skinUi.remove(activity);
    }

    public void apply() {
        if (skinLoader != null && skinUi.size() > 0) {
            for (Activity activity : skinUi.keySet()) {
                SkinWidget skinWidget = skinUi.get(activity);
                if (activity != null && skinWidget != null)
                    skinWidget.apply();
            }
        }
    }

    public int getColor(int id) {
        if (context == null || skinLoader == null) return 0;
        String resourceTypeName = context.getResources().getResourceTypeName(id);
        String resourceName = context.getResources().getResourceEntryName(id);
        int identifier = skinLoader.skinResources.getIdentifier(resourceName, resourceTypeName, skinLoader.skinPackage);
        if (identifier == 0) return 0;
        return skinLoader.skinResources.getColor(identifier);
    }

    public Drawable getDrawable(int id) {
        if (context == null || skinLoader == null) return null;
        String resourceTypeName = context.getResources().getResourceTypeName(id);
        String resourceName = context.getResources().getResourceEntryName(id);
        int identifier = skinLoader.skinResources.getIdentifier(resourceName, resourceTypeName, skinLoader.skinPackage);
        if (identifier == 0) return null;
        return skinLoader.skinResources.getDrawable(id, null);
    }

}
