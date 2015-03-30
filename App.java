package com.gre.jamal.memorisequran;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;

import com.gre.jamal.memorisequran.db.Memoriser;
import com.gre.jamal.memorisequran.db.SQLiteConnectivity;

public class App extends Application {

    private static Context mContext;
    private static Memoriser user;

    /**
     * Find the verse_id at the database given a chapter and verse number
     * @param chapterNumber the chapter number (1 <= chapterNumber <= 114)
     * @param verseNumber the verse number
     * @return the verse_id at the database
     */
    public static int getVerseID(int chapterNumber, int verseNumber) {
        SQLiteConnectivity sqlite = SQLiteConnectivity.getSQLiteConn();
        Cursor cursor = sqlite.execCursorQuery("SELECT * FROM verse " +
                "WHERE chapter_number = " + chapterNumber);
        cursor.moveToFirst();
        do {
            int cursorVNum = cursor.getInt(cursor.getColumnIndex("verse_number"));
            if (cursorVNum == verseNumber){
                return cursorVNum;
            }
            cursor.moveToNext();
        } while (!cursor.isLast());
        return -1;
    }


    public static Memoriser getUser() {
        if (user == null) {
            return new Memoriser("theUser", 8);
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