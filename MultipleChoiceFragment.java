package com.gre.jamal.memorisequran;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gre.jamal.memorisequran.revision.memory.MemoryExercise;
import com.gre.jamal.memorisequran.revision.memory.MemoryTest;
import com.gre.jamal.memorisequran.revision.QuranSection;
import com.gre.jamal.memorisequran.revision.verse.VerseOrderTest;
import com.gre.jamal.memorisequran.revision.word.WordOrderTest;

import org.jqurantree.orthography.Chapter;
import org.jqurantree.orthography.Document;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class MultipleChoiceFragment extends Fragment {

    // store the chapter
    private int chapterNumber;
    private Chapter chapter;

    //the test being run and current exercise
    private MemoryTest test;
    private MemoryExercise currentExercise;

    //used for answer feedback
    private FrameLayout messageFrame;
    private TextView messageTextView;

    //setup colors for feedback
    final int correctColor = 0xFF22BB22;
    final int incorrectColor = 0xFFBB2222;


    //answer buttons
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;


    public MultipleChoiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multiple_choice_test, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();


        Intent intent = getActivity().getIntent();
        int chapterNumber = intent.getIntExtra(ChapterListFragment.EXTRA_CHAPTER_NUMBER, -1);


        //initialise views
        messageFrame = (FrameLayout) getActivity().findViewById(R.id.messageFrame);
        messageTextView = (TextView) getActivity().findViewById(R.id.messageTextView);
        btn1 = (Button) getActivity().findViewById(R.id.verse1Btn);
        btn2 = (Button) getActivity().findViewById(R.id.verse2Btn);
        btn3 = (Button) getActivity().findViewById(R.id.verse3Btn);
        btn4 = (Button) getActivity().findViewById(R.id.verse4Btn);


        if (chapterNumber != -1) {
            switch (intent.getIntExtra(RevisionTestActivity.EXTRA_CURRENT_TEST_TYPE, -1)) {
                case MemoryTest.TEST_TYPE_WORD:
                    test = new WordOrderTest(new QuranSection(chapterNumber),
                            WordOrderTest.TEST_LENGTH_MEDUIUM, Document.getChapter(chapterNumber));
                    break;
                case MemoryTest.TEST_TYPE_VERSE:
                    test = new VerseOrderTest(new QuranSection(chapterNumber),
                            VerseOrderTest.TEST_LENGTH_MEDUIUM, Document.getChapter(chapterNumber));
                    break;
                case MemoryTest.TEST_TYPE_VOWEL:
                    ;
                    break;
                case -1:
                    test = null;
                    break;
            }

            if (test == null){
                getActivity().onBackPressed();
            }


            currentExercise = test.getNextExercise();

            EditText verseEditText = (EditText) getActivity().findViewById(R.id.verseEditText);
            verseEditText.setKeyListener(null);
            verseEditText.setText(currentExercise.getExerciseDisplay());
            btn1.setText(currentExercise.getAnswerChoices()[0]);
            btn2.setText(currentExercise.getAnswerChoices()[1]);
            btn3.setText(currentExercise.getAnswerChoices()[2]);
            btn4.setText(currentExercise.getAnswerChoices()[3]);
        }


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtn1Click(v);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtn2Click(v);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtn3Click(v);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtn4Click(v);
            }
        });

    }


    private void onBtn1Click(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        incrementVerse(buttonText);
    }

    private void onBtn2Click(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        incrementVerse(buttonText);
    }

    private void onBtn3Click(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        incrementVerse(buttonText);
    }

    private void onBtn4Click(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        incrementVerse(buttonText);
    }

    private void incrementVerse(String buttonText) {
        if (currentExercise.isCorrect(buttonText)) {
            messageFrame.setBackgroundColor(correctColor);
            messageTextView.setText("Correct");
        } else {
            messageFrame.setBackgroundColor(incorrectColor);
            messageTextView.setText("Incorrect");
        }
        currentExercise = test.getNextExercise();
        if (currentExercise == null) {
            getActivity().onBackPressed();
        } else {
            EditText verseEditText = (EditText) getActivity().findViewById(R.id.verseEditText);
            verseEditText.setText(currentExercise.getExerciseDisplay());
            String answerChoices[] = currentExercise.getAnswerChoices();
            btn1.setText(answerChoices[0]);
            btn2.setText(answerChoices[1]);
            btn3.setText(answerChoices[2]);
            btn4.setText(answerChoices[3]);

        }
    }
}
