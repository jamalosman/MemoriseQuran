package com.gre.jamal.memorisequran;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
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
    public void onStart(){
        super.onStart();
        GraphView graph = (GraphView) getActivity().findViewById(R.id.graph);
        DataPoint[] points =  new DataPoint[114];
        for (DataPoint point : points){
        //    point = new DataPoint(x,y);
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(points);
        graph.addSeries(series);
    }



}
