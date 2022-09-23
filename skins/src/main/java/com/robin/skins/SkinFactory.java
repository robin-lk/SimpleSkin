package com.robin.skins;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wjunjie 2022/7/7
 */
public class SkinFactory implements LayoutInflater.Factory2 {
    private SkinViewInflater skinViewInflater;
    public SkinWidget skinWidget;
    //是否只用于字体大小
    private boolean onlyTextSize = false;

    public SkinFactory(boolean onlyTextSize) {
        skinWidget = new SkinWidget();
        this.onlyTextSize = onlyTextSize;
        skinWidget.setOnlyTextSize(onlyTextSize);
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        if (skinViewInflater == null) skinViewInflater = new SkinViewInflater();
        //构建view
        View view = skinViewInflater.createView(parent, name, context, attrs,
                false, false, true);
        List<SkinRes> list = new ArrayList<>();
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            String attributeName = attrs.getAttributeName(i);
            if (!onlyTextSize && (attributeName.contains("background") || attributeName.contains("textColor") || attributeName.contains("src"))) {
                //认为是要收集的换肤的控件
                String attributeValue = attrs.getAttributeValue(i);
                //获取资源文件id
                int resId = Integer.parseInt(attributeValue.substring(1));
                //获取资源id的类型
                String resourceTypeName = view.getResources().getResourceTypeName(resId);
                //获取资源id的名字
                String resourceEntryName = view.getResources().getResourceEntryName(resId);
                SkinRes skinItem = new SkinRes(attributeName, resourceTypeName, resourceEntryName, resId);
                list.add(skinItem);
            } else if (attributeName.contains("textSize")) {
                String attributeValue = attrs.getAttributeValue(i);
                if (attributeValue.contains("sp")) {
                    String textSize = attributeValue.replace("sp", "");
                    SkinRes skinItem = new SkinRes(attributeName, name, Float.parseFloat(textSize), "sp");
                    list.add(skinItem);
                } else if (attributeValue.contains("dp")) {
                    String textSize = attributeValue.replace("dp", "");
                    SkinRes skinItem = new SkinRes(attributeName, name, Float.parseFloat(textSize), "dp");
                    list.add(skinItem);
                } else if (attributeValue.contains("dip")) {
                    String textSize = attributeValue.replace("dip", "");
                    SkinRes skinItem = new SkinRes(attributeName, name, Float.parseFloat(textSize), "dip");
                    list.add(skinItem);
                } else if (attributeValue.contains("px")) {
                    String textSize = attributeValue.replace("px", "");
                    SkinRes skinItem = new SkinRes(attributeName, name, Float.parseFloat(textSize), "px");
                    list.add(skinItem);
                }
            }
        }
        skinWidget.addWidget(view, list);
        return view;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return onCreateView(null, name, context, attrs);
    }
}
