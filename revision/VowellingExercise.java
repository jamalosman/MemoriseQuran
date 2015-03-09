package com.gre.jamal.memorisequran.revision;

import org.jqurantree.arabic.ArabicCharacter;
import org.jqurantree.orthography.Verse;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by jamal on 09/03/15.
 */
public class VowellingExercise extends MemoryExercise {

    private ArrayList<ArabicCharacter> shortVowels;

    protected VowellingExercise(Verse verse) {
        super(verse);
        shortVowels = new ArrayList<>();
        String[] verseWords = verse.toUnicode().split(" ");
        Iterator<ArabicCharacter> letterItr = verse.iterator();
        while (letterItr.hasNext()){

        }
    }

    @Override
    public String[] getAnswerChoices() {
        return new String[0];
    }
}
