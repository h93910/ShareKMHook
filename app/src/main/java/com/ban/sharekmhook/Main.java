package com.ban.sharekmhook;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

/**
 * Created by Ban on 2016/6/17.
 */
public class Main implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        XposedBridge.log("test ban" + loadPackageParam.packageName);
        if (!loadPackageParam.packageName.equals("com.liveov.skm"))
            return;
        XposedBridge.log("Loaded app: " + loadPackageParam.packageName);
        findAndHookMethod("java.lang.System", loadPackageParam.classLoader,
                "currentTimeMillis", new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param)
                            throws Throwable {
                        XposedBridge.log("自动改！！成2014.1.1");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param)
                            throws Throwable {
                        long go = 1388505600L * 1000;
                        param.setResult(go);
                    }
                });
    }
}
