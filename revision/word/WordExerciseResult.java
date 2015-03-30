package com.gre.jamal.memorisequran.revision.word;

import android.content.ContentValues;

import com.gre.jamal.memorisequran.revision.memory.ExerciseResult;

/**
 * Created by jamal on 12/03/15.
 */
public class WordExerciseResult extends ExerciseResult {

    private int wordNumber;
    private int userAnswer;

    public WordExerciseResult(int chapterNumber, int verseNumber, int exerciseType) {
        super(chapterNumber, verseNumber, exerciseType);
        this.exerciseType = RESULT_TYPE_WORD;
        wordNumber = -1;
    }

    public WordExerciseResult(int chapterNumber, int verseNumber, int exerciseType, boolean correct) {
        super(chapterNumber, verseNumber, exerciseType, correct);
        this.exerciseType = RESULT_TYPE_WORD;
        this.wordNumber = -1;
    }

    @Override
    public ContentValues getContentValues(long testID) {
        return null;
    }

    public WordExerciseResult(int chapterNumber, int verseNumber, int exerciseType, boolean correct, int wordNumber) {
        super(chapterNumber, verseNumber, exerciseType, correct);
        this.exerciseType = RESULT_TYPE_WORD;
        this.wordNumber = wordNumber;
    }

    public int getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }

    public int getWordNumber() {
        return wordNumber;
    }

    public void setWordNumber(int wordNumber) {
        this.wordNumber = wordNumber;
    }
}
