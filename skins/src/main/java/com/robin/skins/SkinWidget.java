package com.robin.skins;

import android.graphics.drawable.Drawable;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * @author wjunjie 2022/7/8
 */
public class SkinWidget {
    Map<View, List<SkinRes>> map = new ArrayMap<>();

    public void addWidget(View view, List<SkinRes> skinRes) {
        if (view != null && skinRes != null && skinRes.size() > 0) {
            map.put(view, skinRes);
            //需要及时换肤
            if (SkinManager.getInstance().changeNow && SkinManager.getInstance().skinLoader != null)
                apply(view, skinRes);
        }
    }

    //换肤操作
    public void apply() {
        for (View view : map.keySet()) {
            List<SkinRes> skinRes = map.get(view);
            apply(view, skinRes);
        }
    }

    private void apply(View view, List<SkinRes> skinRes) {
        if (skinRes != null && SkinManager.getInstance().skinLoader != null)
            for (SkinRes res : skinRes) {
                if (res.attrName.equalsIgnoreCase("background")) {
                    if (res.resType.equalsIgnoreCase("color")) {
                        int color = SkinManager.getInstance().getColor(res.resId);
                        if (color != 0)
                            view.setBackgroundColor(color);
                    } else if (res.resType.equalsIgnoreCase("drawable") || res.resType.equalsIgnoreCase("mipmap")) {
                        Drawable drawable = SkinManager.getInstance().getDrawable(res.resId);
                        if (drawable != null)
                            view.setBackground(drawable);
                    }
                } else if (res.attrName.equalsIgnoreCase("src")) {
                    Drawable drawable = SkinManager.getInstance().getDrawable(res.resId);
                    if (drawable != null)
                        ((ImageView) view).setImageDrawable(drawable);
                } else if (res.attrName.equalsIgnoreCase("textColor")) {
                    int color = SkinManager.getInstance().getColor(res.resId);
                    if (color != 0)
                        ((TextView) view).setTextColor(color);
                }
            }
    }

    /**
     * 恢复
     */
    public void recovery() {
        if (SkinManager.getInstance().context != null)
            for (View view : map.keySet()) {
                List<SkinRes> skinRes = map.get(view);
                if (skinRes != null)
                    for (SkinRes res : skinRes) {
                        if (res.attrName.equalsIgnoreCase("background")) {
                            if (res.resType.equalsIgnoreCase("color")) {
                                int color = SkinManager.getInstance().getDefaultColor(res.resId);
                                if (color != 0)
                                    view.setBackgroundColor(color);
                            } else if (res.resType.equalsIgnoreCase("drawable") || res.resType.equalsIgnoreCase("mipmap")) {
                                Drawable drawable = SkinManager.getInstance().getDefaultDrawable(res.resId);
                                if (drawable != null)
                                    view.setBackground(drawable);
                            }
                        } else if (res.attrName.equalsIgnoreCase("src")) {
                            Drawable drawable = SkinManager.getInstance().getDefaultDrawable(res.resId);
                            if (drawable != null)
                                ((ImageView) view).setImageDrawable(drawable);
                        } else if (res.attrName.equalsIgnoreCase("textColor")) {
                            int color = SkinManager.getInstance().getDefaultColor(res.resId);
                            if (color != 0)
                                ((TextView) view).setTextColor(color);
                        }
                    }
            }
    }


}
