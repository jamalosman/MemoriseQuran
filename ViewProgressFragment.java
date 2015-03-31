package com.gre.jamal.memorisequran;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewProgressFragment extends Fragment {


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
        GraphView graph = (GraphView) getActivity().findViewById(R.id.graph);

        //load spinner view and insert values
        Spinner graphSpinner = (Spinner) getActivity().findViewById(R.id.graphSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.graph_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        graphSpinner.setAdapter(adapter);
        //onGraphTypeChosen(0);


        graphSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onGraphTypeChosen(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void onGraphTypeChosen(int position) {
        GraphView graph = (GraphView) getActivity().findViewById(R.id.graph);
        Viewport viewport = graph.getViewport();
        switch (position) {
            case 0:
                viewport.setMaxX(114);
                viewport.setMaxY(100);
                BarGraphSeries<DataPoint> lseries = Graphing.getChaptersBarChart(0, 115);
                graph.addSeries(lseries);
                break;
            case 1:
                viewport.setMaxX(20);
                viewport.setMaxY(100);
                LineGraphSeries<DataPoint> series = Graphing.getSingleChapterLineChart(0, 0, 115);
                graph.addSeries(series);
                break;
        }
    }

}
