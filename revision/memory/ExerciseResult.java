package com.gre.jamal.memorisequran.revision.memory;

/**
 * Created by jamal on 12/03/15.
 */
public abstract class ExerciseResult {
    protected int chapterNumber;
    protected int verseNumber; //the verse where the mistake was made on (not the correct answer)
    protected int exerciseType;
    protected boolean correct = false;

    public final static int RESULT_TYPE_VERSE = 1;
    public final static int RESULT_TYPE_WORD = 2;
    public final static int RESULT_TYPE_VOWEL = 3;

    protected ExerciseResult(int chapterNumber, int verseNumber, int exerciseType) {
        this.chapterNumber = chapterNumber;
        this.verseNumber = verseNumber;
        this.exerciseType = exerciseType;
    }

    protected ExerciseResult(int chapterNumber, int verseNumber, int exerciseType, boolean correct) {
        this.chapterNumber = chapterNumber;
        this.verseNumber = verseNumber;
        this.exerciseType = exerciseType;
        this.correct = correct;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public int getVerseNumber() {
        return verseNumber;
    }

    public void setVerseNumber(int verseNumber) {
        this.verseNumber = verseNumber;
    }

    public int getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(int exerciseType) {
        this.exerciseType = exerciseType;
    }
}
