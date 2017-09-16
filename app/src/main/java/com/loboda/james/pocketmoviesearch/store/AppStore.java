package com.loboda.james.pocketmoviesearch.store;

import android.content.pm.PackageManager;

import com.loboda.james.pocketmoviesearch.activity.MainActivity;

/**
 * Created by Twaltex on 7/29/2017.
 */

public abstract class AppStore {

    public static int appStoreType = 1; // 1 = Google Play, 2 = Amazon, 3 = Other

    public static void setAppStore(){

        PackageManager pm = MainActivity.mainActivity.getPackageManager();
        String installerPackageName = pm.getInstallerPackageName(MainActivity.mainActivity.getPackageName());

        if ("com.android.vending".equals(installerPackageName)) {
            appStoreType = 1;
        } else if ("com.amazon.venezia".equals(installerPackageName)) {
            appStoreType = 2;
        } else {
            appStoreType = 3;
        }

    }

    public static int getAppStoreType() {
        return appStoreType;
    }

    public static void setAppStoreType(int appStoreType) {
        AppStore.appStoreType = appStoreType;
    }
}
