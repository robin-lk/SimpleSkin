### SkinManager 换肤统一管理
 * 如果有设置需要在Application中调用loaderApkRes()方法
```java
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        File file = new File(getExternalCacheDir(), "app-debug.apk");
        if (file.exists()) {
            SkinManager.getInstance().init(this, file.exists()).loaderApkRes(file.toString());
        }
    }
}
```
 * 在用到的Activity中注册方法
 ```java
public class Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //在创建view之前调用
        SkinManager.getInstance().addUI(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().removeUI(this);
    }

    //主动更新 一般用修改设置后
    public void apply(){
        SkinManager.getInstance().apply();
    }

}
``` 