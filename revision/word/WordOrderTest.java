package com.gre.jamal.memorisequran.revision.word;

import android.content.ContentValues;

import com.gre.jamal.memorisequran.revision.QuranSection;
import com.gre.jamal.memorisequran.revision.memory.MemoryExercise;
import com.gre.jamal.memorisequran.revision.memory.MemoryTest;
import com.gre.jamal.memorisequran.revision.verse.VerseOrderExercise;

import org.jqurantree.orthography.Chapter;
import org.jqurantree.orthography.Document;
import org.jqurantree.orthography.Verse;

import java.util.ArrayList;

/**
 * Created by jamal on 28/02/15.
 */
public class WordOrderTest extends MemoryTest {

    //    protected QuranSection quranSection;
//    protected TestLevel testLength;
//    protected ArrayList<Chapter> chapters;
    private Verse currentVerse;
    private int offset;

    /**
     * @param quranSection a QuranSection object which sets the bounds of the test
     * @param testLength   The level of the test (how long)
     * @param chapters     The chapters to be revised
     */
    public WordOrderTest(QuranSection quranSection, int testLength, ArrayList<Chapter> chapters) {
        super(quranSection, testLength, chapters);
        this.currentExercise = new WordOrderExercise(chapters.get(0).getVerse(quranSection.getStartVerseIndex()), 0);
        currentVerse = Document.getChapter(quranSection.getStartChapterIndex()).getVerse(quranSection.getStartVerseIndex());
        this.testType = TEST_TYPE_WORD;
    }

    public WordOrderTest(QuranSection quranSection, int testLength, Chapter chapter) {
        super(quranSection, testLength, chapter);
        this.currentExercise = new WordOrderExercise(chapter.getVerse(quranSection.getStartVerseIndex()), 0);
        currentVerse = Document.getChapter(quranSection.getStartChapterIndex()).getVerse(quranSection.getStartVerseIndex());
        this.testType = TEST_TYPE_WORD;
    }

    @Override
    protected ContentValues getContentValues() {
        return null;
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
        Verse tempVerse = currentVerse;
        int tempOffset = offset;
        String[] verseWords = currentVerse.toUnicode().split(" ");
        if (offset < verseWords.length - 1) {
            offset++;
            return new WordOrderExercise(tempVerse, tempOffset);
        } else if (offset == verseWords.length - 1) {
            if (currentVerse.getVerseNumber() != currentVerse.getChapter().getVerseCount()) {
                currentVerse = currentVerse.getChapter().getVerse(currentVerse.getVerseNumber() + 1);
                offset = 0;
                return new WordOrderExercise(tempVerse, tempOffset);
            } else {
                offset++;
                return new WordOrderExercise(tempVerse, tempOffset);
            }
        } else {
            return null;
        }
    }


}

