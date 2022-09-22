package com.robin.skins;

import android.util.TypedValue;

/**
 * @author wjunjie 2022/7/8
 */
public class SkinRes {
    //属性名 （textColor、backgroundColor）
    public String attrName = "";
    //资源类型
    public String resType = "";
    //资源name
    public String resName = "";
    //资源id
    public int resId;
    //控件类名 如 TextView
    public String viewName = "";
    //字体大小
    public float textSize = 12;
    //规格 sp单位
    public String textSizeType = "sp";

    public SkinRes(String attrName, String resType, String resName, int resId) {
        this.attrName = attrName;
        this.resType = resType;
        this.resName = resName;
        this.resId = resId;
    }

    //用于字体大小
    public SkinRes(String attrName, String viewName, float textSize, String textSizeType) {
        this.attrName = attrName;
        this.viewName = viewName;
        this.textSize = textSize;
        this.textSizeType = textSizeType;
    }

    @Override
    public String toString() {
        return "SkinRes{" +
                "attrName='" + attrName + '\'' +
                ", resType='" + resType + '\'' +
                ", resName='" + resName + '\'' +
                ", resId=" + resId +
                ", viewName='" + viewName + '\'' +
                ", textSizeType=" + textSizeType +
                '}';
    }

    public int getTextSizeType() {
        if ("sp".equals(textSizeType)) {
            return TypedValue.COMPLEX_UNIT_SP;
        } else if ("px".equals(textSizeType)) {
            return TypedValue.COMPLEX_UNIT_PX;
        } else if ("dp".equals(textSizeType) || "dip".equals(textSizeType)) {
            return TypedValue.COMPLEX_UNIT_DIP;
        } else {
            return TypedValue.COMPLEX_UNIT_SP;
        }
    }
}
