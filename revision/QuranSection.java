package com.gre.jamal.memorisequran.revision;

import org.jqurantree.orthography.Chapter;
import org.jqurantree.orthography.Document;

import java.util.ArrayList;

/**
 * Created by jamal on 14/01/15.
 */
public class QuranSection {
    private int startChapter;
    private int startVerse;
    private int endChapter;
    private int endVerse;

    /**
     * Creates a Qur'an section for a whole chapter
     *
     * @param chapterNumber The chapter to be tested on
     */
    public QuranSection(int chapterNumber) {
        Chapter chapter = Document.getChapter(chapterNumber);
        this.startChapter = chapterNumber;
        this.startVerse = 1;
        this.endChapter = chapterNumber;
        this.endVerse = chapter.getVerseCount();
    }

    /**
     *  Creates a QuranSection using the specified starting chapter and verse, and ending chapter and verse
     *
     *
     * @param startChapter
     * @param startVerse
     * @param endChapter
     * @param endVerse
     */
    public QuranSection(int startChapter, int startVerse, int endChapter, int endVerse) {
        this.startChapter = startChapter;
        this.startVerse = startVerse;
        this.endChapter = endChapter;
        this.endVerse = endVerse;
    }

    public int getStartChapterIndex() {
        return startChapter;
    }

    public void setStartChapter(int startChapter) {
        this.startChapter = startChapter;
    }

    public int getStartVerseIndex() {
        return startVerse;
    }

    public void setStartVerse(int startVerse) {
        this.startVerse = startVerse;
    }

    public int getEndChapterIndex() {
        return endChapter;
    }

    public void setEndChapter(int endChapter) {
        this.endChapter = endChapter;
    }

    public int getEndVerseIndex() { return endVerse; }

    public void setEndVerse(int endVerse) {
        this.endVerse = endVerse;
    }

    /*
      * returns all the chapters in the section
      * including the 1st and the last
     */
    public ArrayList<Chapter> getSectionChapters(){
        ArrayList<Chapter> chapters = new ArrayList<>();

        for (int i=this.getStartChapterIndex(); i<=this.getEndChapterIndex(); i++){
            chapters.add(Document.getChapter(i));
        }

        return chapters;
    }

}
