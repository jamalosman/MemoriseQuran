package com.gre.jamal.memorisequran.revision.verse;

import com.gre.jamal.memorisequran.revision.memory.ExerciseResult;
import com.gre.jamal.memorisequran.revision.memory.MemoryExercise;

import org.jqurantree.core.error.JQuranTreeException;
import org.jqurantree.orthography.Chapter;
import org.jqurantree.orthography.Verse;

/**
 * Created by jamal on 18/01/15.
 */
public class VerseOrderExercise extends MemoryExercise {
    private String correctAnswer;
    private Verse correctVerse;
    private String[] incorrectAnswers;
    private int[] incorrectAnswerLocations;
    private String[] answerChoices;
    private int[] answerChoiceLocations;

    /**
     * Accessor for the correct answer for this exercise
     * @return the correct answer
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }


    /**
     * Accessor for the list of possible answers
     * @return List of possible answers
     */
    public String[] getAnswerChoices() {
        return answerChoices;
    }



    /**
     *
     * @param verse The verse which the exercise is based on.
     */
    public VerseOrderExercise(Verse verse) {
        super(verse);
        Chapter chapter = verse.getChapter();
        this.correctVerse = chapter.getVerse(verse.getVerseNumber() + 1);
        this.correctAnswer = correctVerse.toUnicode();
        this.incorrectAnswers = new String[3];
        this.incorrectAnswerLocations = new int[3];

        try {
            this.incorrectAnswerLocations[0] = correctVerse.getVerseNumber() + 1;
            this.incorrectAnswers[0] = chapter.getVerse(incorrectAnswerLocations[0]).toUnicode();
            this.incorrectAnswerLocations[1] = correctVerse.getVerseNumber() + 2;
            this.incorrectAnswers[1] = chapter.getVerse(incorrectAnswerLocations[1]).toUnicode();
            this.incorrectAnswerLocations[2] = correctVerse.getVerseNumber() + 3;
            this.incorrectAnswers[2] = chapter.getVerse(incorrectAnswerLocations[2]).toUnicode();
        } catch (JQuranTreeException jqre) {
            this.incorrectAnswers[2] = "*** end *** ";
            incorrectAnswers[1] = (incorrectAnswers[1] == null) ?
                    chapter.getVerse(correctVerse.getVerseNumber() - 1).toUnicode()
                    : incorrectAnswers[1];
            incorrectAnswers[0] = (incorrectAnswers[0] == null) ?
                    chapter.getVerse(correctVerse.getVerseNumber() - 2).toUnicode()
                    : incorrectAnswers[0];
        }
        answerChoices = new String[4];
        answerChoices[0] = correctAnswer;

        answerChoiceLocations = new int[4];
        answerChoiceLocations[0] = correctVerse.getVerseNumber();
        for (int i = 1; i < 4; i++) {
            answerChoiceLocations[i] = incorrectAnswerLocations[i-1];
        }
        shuffleArray(answerChoiceLocations);
        for (int i = 0; i < 4; i++) {
            answerChoices[i] = verse.getChapter().getVerse(answerChoiceLocations[i]).toUnicode();
        }

    }

    public VerseExerciseResult getResult(){
        VerseExerciseResult result = new VerseExerciseResult(verse.getChapterNumber(),verse.getVerseNumber(),
                ExerciseResult.RESULT_TYPE_VERSE,isCorrect(userAnswer), userAnswer);
        result.setCorrectVerse(this.correctVerse.getVerseNumber());
        this.result = result;
        return result;
    }

    /**
     *
     * @param userAnswer The User's answer.
     * @return True if the answer is correct, false if the answer is wrong.
     */
    @Override
    public boolean isCorrect(String userAnswer) {
        this.userAnswer = userAnswer;
        if (userAnswer.equals(this.correctAnswer)) {
            return true;
        } else {
            return false;
        }
    }
}
