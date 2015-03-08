package com.gre.jamal.memorisequran.revision;

import org.jqurantree.orthography.Verse;

import java.util.Random;

/**
 * Created by jamal on 15/01/15.
 */
public abstract class MemoryExercise {

    protected Verse verse;

    protected MemoryExercise(Verse verse) {
        this.verse = verse;
    }

    public Verse getVerse() {
        return verse;
    }

    public String getExerciseDisplay() {
        return verse.toUnicode();
    }

    public void setVerse(Verse verse) {
        this.verse = verse;
    }

    public boolean isCorrect(String userAnswer){
        return false;
    }

    public abstract String[] getAnswerChoices();

    protected void shuffleArray(String[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

}
