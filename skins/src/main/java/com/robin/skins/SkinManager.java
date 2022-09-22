package com.robin.skins;

import android.app.Activity;
import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.LayoutInflaterCompat;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author wjunjie 2022/7/8
 */
public class SkinManager {
    private static SkinManager sm;
    public Context context;
    public SkinLoader skinLoader;
    //及时换肤
    public boolean changeNow;
    //追加字体大小
    private float appendSize = 0;

    private final Map<Activity, SkinWidget> skinUi = new ArrayMap<>();

    public static SkinManager getInstance() {
        if (sm == null) sm = new SkinManager();
        return sm;
    }

    //初始化
    public SkinManager init(Context context, boolean changeNow) {
        this.changeNow = changeNow;
        this.context = context;
        skinUi.clear();
        return getInstance();
    }

    //追加字体大小
    public SkinManager appendFontSize(float size) {
        appendSize = size;
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
            SkinFactory skinFactory = new SkinFactory(false);
            LayoutInflaterCompat.setFactory2(LayoutInflater.from(activity), skinFactory);
            if (skinFactory.skinWidget != null)
                skinUi.put(activity, skinFactory.skinWidget);
        }
    }

    public void addUI(boolean onlyTextSize, Activity activity) {
        if (activity != null) {
            SkinFactory skinFactory = new SkinFactory(onlyTextSize);
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

    public void recovery() {
        if (skinUi.size() > 0) {
            for (Activity activity : skinUi.keySet()) {
                SkinWidget skinWidget = skinUi.get(activity);
                if (activity != null && skinWidget != null)
                    skinWidget.recovery();
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
        return ResourcesCompat.getDrawable(skinLoader.skinResources, id, null);
    }

    public int getDefaultColor(int id) {
        if (context == null) return 0;
        return context.getResources().getColor(id);
    }

    public Drawable getDefaultDrawable(int id) {
        if (context == null) return null;
        return ResourcesCompat.getDrawable(context.getResources(), id, context.getTheme());
    }

    public float getTextSize(float xmlTextSize) {
        return new BigDecimal(appendSize).add(new BigDecimal(xmlTextSize)).floatValue();
    }


    //去色 灰度处理
    public void onGreyLayout(Activity activity, boolean blackColor) {
        if (blackColor) {
            Paint paint = new Paint();
            ColorMatrix cm = new ColorMatrix();
            cm.setSaturation(0);
            paint.setColorFilter(new ColorMatrixColorFilter(cm));
            activity.getWindow().getDecorView().setLayerType(View.LAYER_TYPE_HARDWARE, paint);
        }
    }

    public void onGreyLayout(View... views) {
        if (views == null || views.length == 0) return;
        for (View view : views) {
            if (view == null) continue;
            Paint paint = new Paint();
            ColorMatrix cm = new ColorMatrix();
            cm.setSaturation(0);
            paint.setColorFilter(new ColorMatrixColorFilter(cm));
            view.setLayerType(View.LAYER_TYPE_HARDWARE, paint);
        }
    }


}
