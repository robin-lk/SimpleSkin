package com.robin.skins;

import android.graphics.drawable.Drawable;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;
import java.util.Map;

/**
 * @author wjunjie 2022/7/8
 */
public class SkinWidget {
    Map<View, List<SkinRes>> map = new ArrayMap<>();
    private boolean onlyTextSize = false;

    public void setOnlyTextSize(boolean onlyTextSize) {
        this.onlyTextSize = onlyTextSize;
    }

    public void addWidget(View view, List<SkinRes> skinRes) {
        if (view != null && skinRes != null && skinRes.size() > 0) {
            map.put(view, skinRes);
            //需要及时换肤
            if (SkinManager.getInstance().changeNow && onlyTextSize) {
                applyTextSize(view, skinRes);
            } else if (SkinManager.getInstance().changeNow && SkinManager.getInstance().skinLoader != null) {
                apply(view, skinRes);
            }
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
                } else if (res.attrName.equalsIgnoreCase("textSize")) {
                    setTextSize(view, res);
                }
            }
    }

    private void applyTextSize(View view, List<SkinRes> skinRes) {
        for (SkinRes res : skinRes) {
            if (res.attrName.equalsIgnoreCase("textSize")) {
                setTextSize(view, res);
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

    //设置字体
    private void setTextSize(View view, SkinRes skinRes) {
        if (view == null || skinRes == null) return;
        switch (skinRes.viewName) {
            case "TextView":
                ((TextView) view).setTextSize(skinRes.getTextSizeType(), SkinManager.getInstance().getTextSize(skinRes.textSize));
                break;
            case "Button":
                ((Button) view).setTextSize(skinRes.getTextSizeType(), SkinManager.getInstance().getTextSize(skinRes.textSize));
                break;
            case "MaterialButton":
            case "com.google.android.material.button.MaterialButton":
                ((MaterialButton) view).setTextSize(skinRes.getTextSizeType(), SkinManager.getInstance().getTextSize(skinRes.textSize));
                break;
            case "RadioButton":
                ((RadioButton) view).setTextSize(skinRes.getTextSizeType(), SkinManager.getInstance().getTextSize(skinRes.textSize));
                break;
            case "CheckedTextView":
                ((CheckedTextView) view).setTextSize(skinRes.getTextSizeType(), SkinManager.getInstance().getTextSize(skinRes.textSize));
                break;
            case "AutoCompleteTextView":
                ((AutoCompleteTextView) view).setTextSize(skinRes.getTextSizeType(), SkinManager.getInstance().getTextSize(skinRes.textSize));
                break;
            case "CheckBox":
                ((CheckBox) view).setTextSize(skinRes.getTextSizeType(), SkinManager.getInstance().getTextSize(skinRes.textSize));
                break;
            case "MaterialTextView":
            case "com.google.android.material.textview.MaterialTextView":
                ((MaterialTextView) view).setTextSize(skinRes.getTextSizeType(), SkinManager.getInstance().getTextSize(skinRes.textSize));
                break;
            case "MaterialCheckBox":
            case "com.google.android.material.checkbox.MaterialCheckBox":
                ((MaterialCheckBox) view).setTextSize(skinRes.getTextSizeType(), SkinManager.getInstance().getTextSize(skinRes.textSize));
                break;
            default:
                try {
                    ((TextView) view).setTextSize(skinRes.getTextSizeType(), SkinManager.getInstance().getTextSize(skinRes.textSize));
                } catch (Exception e) {
                    Log.e("shins", e.toString());
                }
                break;
        }
    }
}
