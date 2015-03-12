package com.gre.jamal.memorisequran.revision.memory;

import com.gre.jamal.memorisequran.revision.QuranSection;

import org.jqurantree.orthography.Chapter;
import org.jqurantree.orthography.Verse;

import java.util.ArrayList;

/**
 * Created by jamal on 14/01/15.
 */
public abstract class MemoryTest {

    protected QuranSection quranSection;
    protected int testLength;
    protected ArrayList<Chapter> chapters;
    protected MemoryExercise currentExercise;
    protected ArrayList<ExerciseResult> exerciseResults;

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
     //   this.currentExercise = new MemoryExercise(chapters.get(0).getVerse(quranSection.getStartVerseIndex()));
    }



    /*
         * runs the test
         */
    /*
    public void runRevisionTest(Chapter chapter) {
        if (chapter.getChapterNumber() == quranSection.getStartChapterIndex()) {
            int loopStartPosition, loopEndPosition;
            if (quranSection.getStartChapterIndex() == quranSection.getEndChapterIndex()) {
                loopStartPosition = quranSection.getStartVerseIndex();
                loopEndPosition = quranSection.getEndVerseIndex();
            } else {
                loopStartPosition = quranSection.getStartVerseIndex();
                loopEndPosition = chapter.getVerseCount();
            }
            for (int i = loopStartPosition; i < loopEndPosition; i++) {
                generateExercise(chapter.getVerse(i));
            }
        } else if (chapter.getChapterNumber() != quranSection.getStartChapterIndex()
                && chapter.getChapterNumber() != quranSection.getEndChapterIndex()) {
            for (int i = 0; i < chapter.getVerseCount(); i++) {
                generateExercise(chapter.getVerse(i));
            }
        } else if (chapter.getChapterNumber() == quranSection.getEndChapterIndex()) {
            for (int i = 0; i < quranSection.getEndVerseIndex(); i++) {
                generateExercise(chapter.getVerse(i));
            }
        }
    }
    */

    //public abstract MemoryExercise getNextExercise(MemoryExercise exercise);
    /*
     {

        Verse verse = exercise.getVerse();
        Chapter chapter = verse.getChapter();
        if (verse.getVerseNumber() < chapter.getVerseCount()) {
            return new MemoryExercise(chapter.getVerse(verse.getVerseNumber() +1));
        } else if (verse.getVerseNumber() == chapter.getVerseCount()
                && verse.getChapterNumber() == quranSection.getEndChapterIndex()) {
            return null;
        } else if (verse.getVerseNumber() == chapter.getVerseCount()
                && verse.getChapterNumber() != quranSection.getEndChapterIndex()){
            return new MemoryExercise(Document.getVerse(verse.getChapterNumber() +1, 1));
        } else {
            throw new JQuranTreeException(Errors.INVALID_VERSE_NUMBER);
        }

    }
     */

    public int getTestType() {
        return testType;
    }

    public void setTestType(int testType) {
        this.testType = testType;
    }

    public abstract void generateExercise(Verse verse);

    public abstract MemoryExercise getNextExercise();
}
