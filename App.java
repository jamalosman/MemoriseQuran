package com.gre.jamal.memorisequran;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context mCon) {
        mContext = mCon;
    }

}