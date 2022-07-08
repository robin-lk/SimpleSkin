package com.robin.skins;

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

    public SkinRes(String attrName, String resType, String resName, int resId) {
        this.attrName = attrName;
        this.resType = resType;
        this.resName = resName;
        this.resId = resId;
    }

    @Override
    public String toString() {
        return "SkinRes{" +
                "attrName='" + attrName + '\'' +
                ", resType='" + resType + '\'' +
                ", resName='" + resName + '\'' +
                ", resId=" + resId +
                '}';
    }
}
