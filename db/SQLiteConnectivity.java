package com.gre.jamal.memorisequran.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gre.jamal.memorisequran.App;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Code adpapted from stackoverflow
 * http://stackoverflow.com/questions/20797387/where-should-i-put-a-sqlite-file-in-source-android-app
 */

public class SQLiteConnectivity extends SQLiteOpenHelper {

    public int GetCursor;
    // ****************** Declare all the global variable
// ****************************//
    private Context myContext;
    public String DB_PATH = "data/data/com.gre.jamal.memorisequran/databases/"; // database path
    public static String DB_NAME = "mq.db";// database name
    static String ASSETS_DB_FOLDER = "db";
    private SQLiteDatabase db;
    private static SQLiteConnectivity sqlc;

    //singleton
    private SQLiteConnectivity(Context context, String db_name) {
        super(context, db_name, null, 2);
        if (db != null && db.isOpen())
            close();

        this.myContext = context;
        DB_NAME = db_name;

        try {
            createDataBase();
            openDataBase();
        } catch (IOException e) {
            // System.out.println("Exception in creation of database : "+
            // e.getMessage());
            e.printStackTrace();
        }

    }

    public static SQLiteConnectivity getSQLiteConn (){
        if (sqlc == null){
            return new SQLiteConnectivity(App.getContext(),"mq.db");
        }
        return sqlc;
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();

        if (dbExist) {
            // System.out.println("Database Exist");
        } else {
            this.getReadableDatabase();

            try {
                copyDatabase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private void copyDatabase() throws IOException {
        InputStream input = myContext.getAssets().open(DB_NAME);
        String outputFileName = DB_PATH + DB_NAME;
        OutputStream output = new FileOutputStream(outputFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }

        // Close the streams
        output.flush();
        output.close();
        input.close();
        // System.out.println(DB_NAME + "Database Copied !");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDataBase() throws SQLException {
        // Open the database
        String myPath = DB_PATH + DB_NAME;
        db = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READWRITE);
    }

    public boolean isOpen() {
        if (db != null)
            return db.isOpen();
        return false;
    }

    @Override
    public synchronized void close() {
        if (db != null)
            db.close();
        super.close();
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            // System.out.println("My Pathe is:- " + myPath);
            // System.out.println("Open");
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READWRITE);
            // System.out.println("checkDB value:" + checkDB);
            // System.out.println("My Pathe is:- " + myPath);
        } catch (Exception e) {
            // database does't exist yet.
        }

        if (checkDB != null) {
            // System.out.println("Closed");
            checkDB.close();
            // System.out.println("My db is:- " + checkDB.isOpen());
        }

        return checkDB != null ? true : false;
    }

    public Cursor execCursorQuery(String sql) {
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql, null);
            GetCursor = cursor.getCount();
            Log.i("Inside execCursorQuery try", sql);
        } catch (Exception e) {
            Log.i("Inside execCursorQuery exception", e.getMessage());
        }
        return cursor;
    }

    public void execNonQuery(String sql) {
        try {
            db.execSQL(sql);
            // Log.d("SQL", sql);
        } catch (Exception e) {
            // Log.e("Err", e.getMessage());
        } finally {
            // closeDb();
        }
    }

    /**
     * Inserts a memoriser into the database
     * @param user the memoriser to be entered
     * @return the rowID of the inserted memoriser
     */
    public long insertMemoriser(Memoriser user){
        String statement = user.getsSelectStatement();
        Cursor cursor = execCursorQuery(statement);
        long id;
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
           id = cursor.getInt(cursor.getColumnIndex("id"));
        } else {
            id = db.insert("Memoriser", null, user.getContentValues());
        }
        user.setMemoriserID(id);
        return id;
    }

    public long insert(String table, ContentValues values){
        return db.insert(table,null,values);
    }


}