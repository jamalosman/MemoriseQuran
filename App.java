package com.gre.jamal.memorisequran;

import android.app.Application;
import android.content.Context;

import com.gre.jamal.memorisequran.db.Memoriser;

public class App extends Application {

    private static Context mContext;
    private static Memoriser user;

    public static Memoriser getUser() {
        if (user == null){
            return new Memoriser("theUser",8);
        }
        return user;
    }

    public static void setUser(Memoriser user) {
        App.user = user;
    }

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context mCon) {
        mContext = mCon;
    }

}