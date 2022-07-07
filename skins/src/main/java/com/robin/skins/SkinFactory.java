package com.robin.skins;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author wjunjie 2022/7/7
 */
public class SkinFactory implements LayoutInflater.Factory2 {
    private SkinViewInflater skinViewInflater;
    private Context mContext;

    public SkinFactory(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        if (skinViewInflater == null) skinViewInflater = new SkinViewInflater();
        //构建view
        View view = skinViewInflater.createView(parent, name, context, attrs,
                false, false, true);
        return view;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return onCreateView(null, name, context, attrs);
    }
}
