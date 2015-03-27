package com.gre.jamal.memorisequran.db;

/**
 * Created by jamal on 27/03/15.
 */
public class Memoriser {

    private String username;
    private int age;

    private int memoriserID;

    public Memoriser(String username, int age) {
        this.username = username;
        this.age = age;
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

    public int getMemoriserID() {
        return memoriserID;
    }

    public void setMemoriserID(int memoriserID) {
        this.memoriserID = memoriserID;
    }
}
