package com.robin.sskinmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.robin.skins.SkinFactory;
import com.robin.skins.SkinManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SkinManager.getInstance().addUI(true,this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fg, SkinFragment.newInstance("", ""))
                .commitAllowingStateLoss();
//        InputStream is = getClass().getResourceAsStream("/assets/" + "app-debug.apk");
//        File externalCacheDir = getExternalCacheDir();
//        Log.e("file", String.valueOf(externalCacheDir.exists()));
//        try {
//            FileOutputStream fos = new FileOutputStream(new File(externalCacheDir,"app-debug.apk"));
//            byte[] buffer = new byte[1024];
//            int byteCount = 0;
//            while ((byteCount = is.read(buffer)) != -1) {//循环从输入流读取 buffer字节
//                fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
//            }
//            fos.flush();
//            fos.close();
//            is.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().removeUI(this);
    }

    public void newUi(View view) {
//        startActivity(new Intent(this, SecondActivity.class));
//        File externalCacheDir = getExternalCacheDir();
//        SkinManager.getInstance().loaderApkRes(new File(externalCacheDir,"app-debug.apk").toString());
        SkinManager.getInstance().recovery();
    }

}