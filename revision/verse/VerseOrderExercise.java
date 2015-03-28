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
        correctVerse = chapter.getVerse(verse.getVerseNumber() + 1);
        correctAnswer = correctVerse.toUnicode();
        incorrectAnswers = new String[3];
        incorrectAnswerLocations = new int[3];

        try {
            incorrectAnswerLocations[0] = correctVerse.getVerseNumber() + 1;
            incorrectAnswers[0] = chapter.getVerse(incorrectAnswerLocations[0]).toUnicode();
            incorrectAnswerLocations[1] = correctVerse.getVerseNumber() + 2;
            incorrectAnswers[1] = chapter.getVerse(incorrectAnswerLocations[1]).toUnicode();
            incorrectAnswerLocations[2] = correctVerse.getVerseNumber() + 3;
            incorrectAnswers[2] = chapter.getVerse(incorrectAnswerLocations[2]).toUnicode();
        } catch (JQuranTreeException jqre) {
            incorrectAnswerLocations[2] = correctVerse.getVerseNumber() - 1;
            incorrectAnswers[2] = chapter.getVerse(incorrectAnswerLocations[2]).toUnicode();
            if (incorrectAnswers[1] == null) {
                incorrectAnswers[1] = chapter.getVerse(correctVerse.getVerseNumber() - 2).toUnicode();
                incorrectAnswerLocations[1] = correctVerse.getVerseNumber() - 2;
            }
            if (incorrectAnswers[0] == null){
                incorrectAnswers[0] = chapter.getVerse(correctVerse.getVerseNumber() - 3).toUnicode();
                incorrectAnswerLocations[0] = correctVerse.getVerseNumber() - 3;
            }

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
