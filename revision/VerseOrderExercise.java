package com.gre.jamal.memorisequran.revision;

import org.jqurantree.core.error.JQuranTreeException;
import org.jqurantree.orthography.Chapter;
import org.jqurantree.orthography.Verse;

/**
 * Created by jamal on 18/01/15.
 */
public class VerseOrderExercise extends MemoryExercise {
    private String correctAnswer;
    private String[] incorrectAnswers;
    private String[] answerChoices;

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
        Verse correctVerse = chapter.getVerse(verse.getVerseNumber() + 1);
        this.correctAnswer = correctVerse.toUnicode();
        this.incorrectAnswers = new String[3];

        try {
            this.incorrectAnswers[0] = chapter.getVerse(correctVerse.getVerseNumber() + 1).toUnicode();
            this.incorrectAnswers[1] = chapter.getVerse(correctVerse.getVerseNumber() + 2).toUnicode();
            this.incorrectAnswers[2] = chapter.getVerse(correctVerse.getVerseNumber() + 3).toUnicode();
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
        for (int i = 1; i < 4; i++) {
            answerChoices[i] = incorrectAnswers[i-1];
        }
        shuffleArray(answerChoices);


    }

    /**
     *
     * @param userAnswer The User's answer.
     * @return True if the answer is correct, false if the answer is wrong.
     */
    @Override
    public boolean isCorrect(String userAnswer) {
        if (userAnswer.equals(this.correctAnswer)) {
            return true;
        } else {
            return false;
        }
    }
}
