package com.gre.jamal.memorisequran.revision;

import org.jqurantree.orthography.Chapter;
import org.jqurantree.orthography.Verse;

import java.util.ArrayList;

/**
 * Created by jamal on 09/03/15.
 */
public class VowellingTest extends MemoryTest {

    protected VowellingTest(QuranSection quranSection, int testLevel, ArrayList<Chapter> chapters) {
        super(quranSection, testLevel, chapters);
    }

    public VowellingTest(QuranSection quranSection, int testLevel, Chapter chapter) {
        super(quranSection, testLevel, chapter);
    }

    @Override
    public void generateExercise(Verse verse) {

    }

    @Override
    public MemoryExercise getNextExercise() {
        return null;
    }
}
