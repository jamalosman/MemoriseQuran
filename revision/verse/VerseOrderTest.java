package com.gre.jamal.memorisequran.revision.verse;

import android.content.ContentValues;
import android.util.Log;

import com.gre.jamal.memorisequran.App;
import com.gre.jamal.memorisequran.db.Memoriser;
import com.gre.jamal.memorisequran.db.SQLiteConnectivity;
import com.gre.jamal.memorisequran.revision.QuranSection;
import com.gre.jamal.memorisequran.revision.memory.ExerciseResult;
import com.gre.jamal.memorisequran.revision.memory.MemoryExercise;
import com.gre.jamal.memorisequran.revision.memory.MemoryTest;

import org.jqurantree.orthography.Chapter;
import org.jqurantree.orthography.Document;
import org.jqurantree.orthography.Verse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        this.currentExercise = new VerseOrderExercise(chapters.get(0).getVerse(quranSection.getStartVerseIndex()));
        currentVerse = Document.getChapter(quranSection.getStartChapterIndex()).getVerse(quranSection.getStartVerseIndex());
        this.testType = TEST_TYPE_VERSE;

    }

    public VerseOrderTest(QuranSection quranSection, int testLength, Chapter chapter) {
        super(quranSection, testLength, chapter);
        this.currentExercise = new VerseOrderExercise(chapters.get(0).getVerse(quranSection.getStartVerseIndex()));
        currentVerse = Document.getChapter(quranSection.getStartChapterIndex()).getVerse(quranSection.getStartVerseIndex());
        this.testType = TEST_TYPE_VERSE;
    }

    @Override
    protected ContentValues getContentValues() {
        ContentValues testValues = new ContentValues();
        testValues.put("memoriser_id", App.getUser().getMemoriserID());
        testValues.put("chapter_number", this.quranSection.getStartChapterIndex());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        testValues.put("timestamp", dateFormat.format(new Date()));
        return testValues;
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
        try {
            VerseExerciseResult result = ((VerseOrderExercise) currentExercise).getResult();
            exerciseResults.add(result);
        } catch (NullPointerException npe) {
            Log.d("First run", "There is no result for this run");
        }

        Verse temp = currentVerse;
        if (currentVerse.getVerseNumber() != currentVerse.getChapter().getVerseCount()) {
            currentVerse = currentVerse.getChapter().getVerse(currentVerse.getVerseNumber() + 1);
            currentExercise = new VerseOrderExercise(temp);
            return currentExercise;
        } else {
            // when the test is finished
            saveResults();
            return null;
        }
    }

    private void saveResults() {
        Memoriser user = App.getUser();
        ContentValues testValues = this.getContentValues();
        SQLiteConnectivity sqliteConn = SQLiteConnectivity.getSQLiteConn();
        long testID = sqliteConn.insert("test", this.getContentValues());
        ArrayList<Integer> correctList = new ArrayList<>();
        for (ExerciseResult result : exerciseResults) {
            sqliteConn.insert("verse_exercise_result", result.getContentValues(testID));
            correctList.add(result.getContentValues(testID).getAsInteger("is_correct"));
        }
        if (!correctList.contains(0)) {
            updateTestScore(100, testID, 1);
        } else {
            double verseWeight = 100 / Document.getChapter(quranSection.getStartChapterIndex())
                    .getVerseCount();
            double memoryStrength = 0;
            for (Integer i : correctList) {
                if (i.equals(1)) {
                    memoryStrength += verseWeight;
                }
            }
            updateTestScore(memoryStrength, testID);
        }
    }

    private boolean updateTestScore(double memoryStrength, long testID) {
        boolean success;
        try {
            SQLiteConnectivity sqliteConn = SQLiteConnectivity.getSQLiteConn();
            String chapterStatement = "UPDATE chapter " +
                    "SET memory_strength_verse_order = " + memoryStrength + " " +
                    "WHERE chapter_number = " + quranSection.getStartChapterIndex() + ";";
            String testStatement = "UPDATE test " +
                    "SET memory_strength = " + memoryStrength + " " +
                    "WHERE id = " + testID + ";";
            sqliteConn.execNonQuery(chapterStatement);
            sqliteConn.execNonQuery(testStatement);
            success = true;
        } catch (Exception ex) {
            success = false;
        }
        return success;
    }

    private boolean updateTestScore(double memoryStrength, long testID, int isMemorised) {
        boolean success;
        try {
            SQLiteConnectivity sqliteConn = SQLiteConnectivity.getSQLiteConn();
            String chapterStatement = "UPDATE chapter " +
                    "SET is_memorised = " + isMemorised + ", " +
                    "memory_strength_verse_order = " + memoryStrength + " " +
                    "WHERE chapter_number = " + quranSection.getStartChapterIndex() + ";";
            String testStatement = "UPDATE test " +
                    "SET memory_strength = " + memoryStrength + " " +
                    "WHERE id = " + testID + ";";
            sqliteConn.execNonQuery(chapterStatement);
            sqliteConn.execNonQuery(testStatement);
            success = true;
        } catch (Exception ex) {
            success = false;
        }
        return success;
    }

}

