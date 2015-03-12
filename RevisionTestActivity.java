package com.gre.jamal.memorisequran;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.gre.jamal.memorisequran.revision.memory.MemoryTest;


public class RevisionTestActivity extends ActionBarActivity {

    /*    private int chapterNumber;

        public int getChapterNumber() {
            return chapterNumber;
        }

        public void setChapterNumber(int chapterNumber) {
            this.chapterNumber = chapterNumber;
        }*/
    public final static String EXTRA_CURRENT_TEST_TYPE = "com.gre.jamal.memorisequran.CURRENT_TEST_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revision_test);
        boolean[] selectedTests = getIntent().getBooleanArrayExtra(ChapterListFragment.EXTRA_TEST_TYPES);
        if (savedInstanceState == null) {
            int chapterNumber = getIntent().getIntExtra((ChapterListFragment.EXTRA_CHAPTER_NUMBER), 0);
            if (selectedTests[ChapterListFragment.VERSE_ORDER_TEST] == true) {
                selectedTests[ChapterListFragment.VERSE_ORDER_TEST] = false;
                getIntent().putExtra(EXTRA_CURRENT_TEST_TYPE, MemoryTest.TEST_TYPE_VERSE);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new MultipleChoiceFragment())
                        .commit();
            } else if (selectedTests[ChapterListFragment.WORD_ORDER_TEST] == true) {
                selectedTests[ChapterListFragment.WORD_ORDER_TEST] = false;
                getIntent().putExtra(EXTRA_CURRENT_TEST_TYPE, MemoryTest.TEST_TYPE_WORD);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new MultipleChoiceFragment())
                        .commit();
            } else {
                onBackPressed();
            }
        }
    }

    private MemoryTest currentTest;

    public MemoryTest getCurrentTest() {
        return currentTest;
    }

    public void setCurrentTest(MemoryTest currentTest) {
        this.currentTest = currentTest;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_revision_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
