package com.gre.jamal.memorisequran;

import com.gre.jamal.memorisequran.db.SQLiteConnectivity;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Created by jamal on 30/03/15.
 */
public class Graphing {

    public LineGraphSeries<DataPoint> getChaptersBarChart(){
        SQLiteConnectivity sqlite = SQLiteConnectivity.getSQLiteConn();
       // sqlite.execCursorQuery("SELECT ");// TODO

        DataPoint[] points =  new DataPoint[114];
        for (DataPoint point : points){
         //   point = new DataPoint(x,y);
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(points);
        return series;
    }

}
