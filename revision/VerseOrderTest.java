package com.gre.jamal.memorisequran.revision;

import org.jqurantree.orthography.Chapter;
import org.jqurantree.orthography.Document;
import org.jqurantree.orthography.Verse;

import java.util.ArrayList;

/**
 * Created by jamal on 15/01/15.
 */
public class VerseOrderTest extends MemoryTest {

    //    protected QuranSection quranSection;
//    protected TestLevel testLength;
//    protected ArrayList<Chapter> chapters;
    private Verse currentVerse;

    /**
     * @param quranSection a QuranSection object which sets the bounds of the test
     * @param testLength   The level of the test (how long)
     * @param chapters     The chapters to be revised
     */
    public VerseOrderTest(QuranSection quranSection, int testLength, ArrayList<Chapter> chapters) {
        super(quranSection, testLength, chapters);
        this.startExercise = new VerseOrderExercise(chapters.get(0).getVerse(quranSection.getStartVerseIndex()));
        currentVerse = Document.getChapter(quranSection.getStartChapterIndex()).getVerse(quranSection.getStartVerseIndex());
        this.testType = TEST_TYPE_VERSE;

    }

    public VerseOrderTest(QuranSection quranSection, int testLength, Chapter chapter) {
        super(quranSection, testLength, chapter);
        this.startExercise = new VerseOrderExercise(chapters.get(0).getVerse(quranSection.getStartVerseIndex()));
        currentVerse = Document.getChapter(quranSection.getStartChapterIndex()).getVerse(quranSection.getStartVerseIndex());
        this.testType = TEST_TYPE_VERSE;
    }

    /**
     * Generates a exercise based on a verse
     *
     * @param verse The verse which the exercise is based on
     */
    @Override
    public void generateExercise(Verse verse) {
        VerseOrderExercise voe = new VerseOrderExercise(verse);
    }

    @Override
    public MemoryExercise getNextExercise() {
        Verse temp = currentVerse;
        if (currentVerse.getVerseNumber() != currentVerse.getChapter().getVerseCount()) {
            currentVerse = currentVerse.getChapter().getVerse(currentVerse.getVerseNumber() + 1);
            return new VerseOrderExercise(temp);
        } else {
            return null;
        }
    }


}

