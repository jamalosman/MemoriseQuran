package com.gre.jamal.memorisequran.revision.word;

import com.gre.jamal.memorisequran.revision.memory.MemoryExercise;

import org.jqurantree.orthography.Chapter;
import org.jqurantree.orthography.Verse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by jamal on 28/02/15.
 */
public class WordOrderExercise extends MemoryExercise {
    private String correctAnswer;
    private String[] incorrectAnswers;
    private String[] answerChoices;
    private int offset;
    private String[] verseWords;

    @Override
    public String getExerciseDisplay() {
        if (offset != 0) {
            return verseWords[offset - 1];
        } else {
            return "First word?";
        }
    }

    /**
     * Accessor for the correct answer for this exercise
     *
     * @return the correct answer
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Accessor for the list of possible answers
     *
     * @return List of possible answers
     */
    public String[] getAnswerChoices() {
        return answerChoices;
    }

    /**
     * @param verse The verse which the exercise is based on.
     */

    public WordOrderExercise(Verse verse, int offset) {
        super(verse);
        this.offset = offset;
        Chapter chapter = verse.getChapter();
        verseWords = verse.toUnicode().split(" ");
        this.incorrectAnswers = new String[3];
        Random rnd = new Random();

        correctAnswer = verseWords[offset];

        ArrayList<String> incorrectWords = new ArrayList<>();
        for (String word : verseWords) {
            if (!word.equals(verseWords[offset]))
                incorrectWords.add(word);
        }

        try {
            incorrectAnswers[0] = incorrectWords.get(0);
            incorrectAnswers[1] = incorrectWords.get(1);
            incorrectAnswers[2] = incorrectWords.get(2);
        } catch (Exception ex) {
            for (int i = 0; i < incorrectAnswers.length; i++) {
                incorrectAnswers[i] = incorrectAnswers[i] == null ? "" : incorrectAnswers[i];
            }
        }


        answerChoices = new String[4];
        answerChoices[0] = correctAnswer;
        for ( int i = 1;  i < 4; i++)
        {
            answerChoices[i] = incorrectAnswers[i - 1];
        }

        ArrayList<String> answersList = new ArrayList<>();

        for (String answer : answerChoices)
        {
            if (!answersList.contains(answer)) {
                answersList.add(answer);
            }
        }

        Collections.shuffle(answersList);
        answerChoices = answersList.toArray(new String[4]);
    }

    /**
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
