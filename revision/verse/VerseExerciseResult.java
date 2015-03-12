package com.gre.jamal.memorisequran.revision.verse;

import com.gre.jamal.memorisequran.revision.memory.ExerciseResult;

/**
 * Created by jamal on 12/03/15.
 */
public class VerseExerciseResult extends ExerciseResult {

    private int correctVerse;
    private int chosenVerse;

    public int getCorrectVerse() {
        return correctVerse;
    }

    public void setCorrectVerse(int correctVerse) {
        this.correctVerse = correctVerse;
    }

    public int getChosenVerse() {
        return chosenVerse;
    }

    public void setChosenVerse(int chosenVerse) {
        this.chosenVerse = chosenVerse;
    }

    public VerseExerciseResult(int chapterNumber, int verseNumber, int exerciseType) {
        super(chapterNumber, verseNumber, exerciseType);
        this.exerciseType = RESULT_TYPE_VERSE;
    }

    public VerseExerciseResult(int chapterNumber, int verseNumber, int exerciseType, boolean correct) {
        super(chapterNumber, verseNumber, exerciseType, correct);
        this.exerciseType = RESULT_TYPE_VERSE;
    }


}
