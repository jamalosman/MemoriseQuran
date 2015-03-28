package com.gre.jamal.memorisequran.db;

import android.content.ContentValues;

/**
 * Created by jamal on 27/03/15.
 */
public class Memoriser {

    private String username;
    private int age;

    private long memoriserID;

    /**
     * Creates a new memoriser and inserts it into the database, If the memoriser already exists,
     * use the constructor that takes an id
     * @param username the memoriser's username
     * @param age the memorisers age
     */
    public Memoriser(String username, int age) {
        this.username = username;
        this.age = age;
        SQLiteConnectivity.getSQLiteConn().insertMemoriser(this);
    }

    public Memoriser(String username, int age, int memoriserID) {
        this.username = username;
        this.age = age;
        this.memoriserID = memoriserID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getMemoriserID() {
        return memoriserID;
    }

    public void setMemoriserID(long memoriserID) {
        this.memoriserID = memoriserID;
    }

    public ContentValues getContentValues(){
        ContentValues userValues = new ContentValues();
        userValues.put("username", this.username);
        userValues.put("username", this.age);
        return userValues;
    }

    public String getInsertStatement() {
        return "INSERT INTO Memoriser (username, age)" +
                "Values ("+ this.username +","+ this.age +")";
    }
}
