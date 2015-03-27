package com.gre.jamal.memorisequran.revision.verse;

import com.gre.jamal.memorisequran.revision.memory.ExerciseResult;

/**
 * Created by jamal on 12/03/15.
 */
public class VerseExerciseResult extends ExerciseResult {
//    protected int chapterNumber;
//    protected int verseNumber; //the verse where the mistake was made on (not the correct answer)
//    protected int exerciseType;
//    protected boolean correct = false;

    private int correctVerse;
    private String chosenVerse;

    public int getCorrectVerse() {
        return correctVerse;
    }

    public void setCorrectVerse(int correctVerse) {
        this.correctVerse = correctVerse;
    }

    public String getChosenVerse() {
        return chosenVerse;
    }

    public void setChosenVerse(String chosenVerse) {
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
    public VerseExerciseResult(int chapterNumber, int verseNumber, int exerciseType, boolean correct, String cVerse) {
        super(chapterNumber, verseNumber, exerciseType, correct);
        chosenVerse = cVerse;
        this.exerciseType = RESULT_TYPE_VERSE;
    }


}
