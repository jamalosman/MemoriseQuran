package com.gre.jamal.memorisequran;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jqurantree.orthography.Chapter;
import org.jqurantree.orthography.Document;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChapterListFragment extends Fragment {
    public final static String EXTRA_CHAPTER_NUMBER = "com.gre.jamal.memorisequran.CHAPTER_NUMBER";
    public final static String EXTRA_TEST_TYPES = "com.gre.jamal.memorisequran.TEST_TYPES";
    public static final int VERSE_ORDER_TEST = 0;
    public static final int WORD_ORDER_TEST = 1;
    public static final int VOWELLING_ORDER_TEST = 2;
    private AlertDialog dialog;

    public ChapterListFragment() {
        // Required empty public constructor
    }

    String[] chapterNames;
    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_revision, container, false);
    }

    @Override
    public void onStart() {
        // Populate list view with chapter names
        super.onStart();
        ListView chapterListView = (ListView) getView().findViewById(R.id.chapterListView);
        chapterNames = getResources().getStringArray(R.array.chapter_names);
        String temp;
        Chapter chapter;
        for (int i = 0; i < 114; i++) {
            chapterNames[i] = i + 1 + ": " + chapterNames[i];
            temp = Document.getChapter(i + 1).getName().toUnicode();
            chapterNames[i] += "\n" + temp;
        }
        ArrayAdapter<String> chapterListAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, chapterNames);
        chapterListView.setAdapter(chapterListAdapter);

        // add onItemClickListener
        chapterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onChapterSelected(view, position);
            }
        });
    }


    public void onChapterSelected(View view, int position) {
        // get chapterNumber (chapters start from 1, list positions start from 0)
        int chapterNumber = position + 1;
        final Intent revisionTestIntent = new Intent(this.getActivity(), RevisionTestActivity.class);
        revisionTestIntent.putExtra(EXTRA_CHAPTER_NUMBER, chapterNumber);

        final CharSequence[] items = {" Verse Order Test ", " Word Order Test ", " Vowelling "};
        // arraylist to keep the selected items
        final boolean[] selectedItems = {true, false, false};

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle("Select Test Types");

        builder.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected) {

                        for (int i = 0; i < selectedItems.length; i++) {
                            if (i == indexSelected) {
                                selectedItems[i] = true;
                            } else {
                                selectedItems[i] = false;
                            }
                        }
                    }
                });
        // Set the action buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        revisionTestIntent.putExtra(EXTRA_TEST_TYPES, selectedItems);
                        startActivity(revisionTestIntent);
                    }
                }

        );
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                }

        );

        dialog = builder.create();//AlertDialog dialog; create like this outside onClick
        dialog.show();


    }


}


