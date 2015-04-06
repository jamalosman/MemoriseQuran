package com.gre.jamal.memorisequran;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.NumberFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewProgressFragment extends Fragment {

    Spinner chapterSpinner;
    GraphView graph;
    boolean viewingSingleChapter = false;

    public ViewProgressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_progress, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        // load graph view
        graph = (GraphView) getActivity().findViewById(R.id.graph);

        //load spinners view and insert values
        Spinner graphSpinner = (Spinner) getActivity().findViewById(R.id.graphSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.graph_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        graphSpinner.setAdapter(adapter);

        chapterSpinner = (Spinner) getActivity().findViewById(R.id.chapterSpinner);
        ArrayAdapter<CharSequence> chapterAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.chapter_names, android.R.layout.simple_spinner_item);
        chapterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chapterSpinner.setAdapter(chapterAdapter);

        Viewport viewport = graph.getViewport();
        viewport.setScrollable(true);
        viewport.setYAxisBoundsManual(true);
        viewport.setXAxisBoundsManual(true);
        viewport.setMaxY(100);
        viewport.setMaxX(10);

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(0);
        nf.setMinimumIntegerDigits(1);
        nf.setMaximumFractionDigits(0);
        nf.setMaximumIntegerDigits(3);
        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(nf, nf));

        graphSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onGraphTypeChosen(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        chapterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onChapterChosen(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void onGraphTypeChosen(int position) {
        graph.removeAllSeries();
        Viewport viewport = graph.getViewport();
        switch (position) {
            case 0:
                viewingSingleChapter = false;
                chapterSpinner.setVisibility(View.INVISIBLE);
                viewport.setMaxX(40);
                //graph.getGridLabelRenderer().setNumHorizontalLabels(10);
                BarGraphSeries<DataPoint> lseries = Graphing.getChaptersBarChart(0, 115);
                graph.addSeries(lseries);
                break;
            case 1:
                viewingSingleChapter = true;
                chapterSpinner.setVisibility(View.VISIBLE);
                viewport.setMaxX(10);
                viewport.setMaxY(100);
                graph.getGridLabelRenderer().setNumVerticalLabels(5);
                LineGraphSeries<DataPoint> series = Graphing.getSingleChapterLineChart(
                        chapterSpinner.getSelectedItemPosition() + 1, 0, 50);
                graph.addSeries(series);
                break;
        }
    }

    private void onChapterChosen(int position) {
        if (viewingSingleChapter) {
            graph.removeAllSeries();
            LineGraphSeries<DataPoint> series = Graphing.getSingleChapterLineChart(
                    chapterSpinner.getSelectedItemPosition() + 1, 0, 50);
            graph.addSeries(series);
        }
    }
}
