package com.gre.jamal.memorisequran;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    // Fragment managing the behaviors, interactions and presentation of the navigation drawer.
    private NavigationDrawerFragment mNavigationDrawerFragment;

    // Used to store the last screen title. For use in {@link #restoreActionBar()}.
    private CharSequence mTitle;

    // Used to store the layout of the currently selected fragment
    private static int mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        App.setContext(this.getApplicationContext());
    }


    private Fragment getFragment(Fragment fragment, String type) {
        if (fragment == null) {
            switch (type) {
                case "revision":
                    if (fragment == null) {
                        fragment = new ChapterListFragment();
                    }
                    break;
                case "view_progress":
                    if (fragment == null) {
                        fragment = new ViewProgressFragment();
                    }
                    break;
                case "schedule":
                    if (fragment == null) {
                        fragment = new ScheduleFragment();
                    }
                    break;

            }
        }
        return fragment;
    }


    // Current fragment is global so it can be referenced.
    private Fragment currentFragment;

    //individual fragments are stored and re-used
    private ChapterListFragment revisionFragment;
    private ViewProgressFragment viewProgressFragment;
    private ScheduleFragment scheduleFragment;


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        currentFragment = getFragment(viewProgressFragment, "view_progress");
        switch (position) {
            case 0:
                currentFragment = getFragment(revisionFragment, "revision");
                break;
            case 1:
                currentFragment = getFragment(viewProgressFragment, "view_progress");
                break;
            case 2:
                currentFragment = getFragment(scheduleFragment, "schedule");
                break;
        }
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, currentFragment)
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_revise);
                break;
            case 2:
                mTitle = getString(R.string.title_schedule);
                break;
            case 3:
                mTitle = getString(R.string.title_progress);
                break;
        }
    }


    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }

        return super.onCreateOptionsMenu(menu);
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
