package com.gre.jamal.memorisequran.revision.memory;

import com.gre.jamal.memorisequran.revision.QuranSection;

import org.jqurantree.orthography.Chapter;
import org.jqurantree.orthography.Verse;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jamal on 14/01/15.
 */
public abstract class MemoryTest {

    protected QuranSection quranSection;
    protected int testLength;
    protected ArrayList<Chapter> chapters;
    protected MemoryExercise currentExercise;
    protected ArrayList<ExerciseResult> exerciseResults;
    protected Date testDate;

    protected int testType;

    public final static int TEST_LENGTH_SHORT = 1;
    public final static int TEST_LENGTH_MEDUIUM = 2;
    public final static int TEST_LENGTH_LONG = 3;

    public final static int TEST_TYPE_VERSE = 1;
    public final static int TEST_TYPE_WORD = 2;
    public final static int TEST_TYPE_VOWEL = 3;

    /**
     * @param quranSection a QuranSection object which sets the bounds of the test
     * @param testLevel    The level of the test (how long)
     * @param chapters     The chapters to be revised
     */
    protected MemoryTest(QuranSection quranSection, int testLevel, ArrayList<Chapter> chapters) {
        this.quranSection = quranSection;
        this.testLength = testLevel;
        this.chapters = chapters;
  //      this.currentExercise = new MemoryExercise(chapters.get(0).getVerse(quranSection.getStartVerseIndex()));
    }

    public MemoryTest(QuranSection quranSection, int testLevel, Chapter chapter)
    {
        this.quranSection = quranSection;
        this.testLength = testLevel;
        this.chapters = new ArrayList<Chapter>();
        this.chapters.add(chapter);
        this.testDate = new Date();
     //   this.currentExercise = new MemoryExercise(chapters.get(0).getVerse(quranSection.getStartVerseIndex()));
    }





    public int getTestType() {
        return testType;
    }

    public void setTestType(int testType) {
        this.testType = testType;
    }

    public abstract void generateExercise(Verse verse);

    public abstract MemoryExercise getNextExercise();
}
