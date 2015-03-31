package com.gre.jamal.memorisequran;

import android.database.Cursor;

import com.gre.jamal.memorisequran.db.SQLiteConnectivity;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by jamal on 30/03/15.
 */
public class Graphing {

    public static BarGraphSeries<DataPoint> getChaptersBarChart(int offset, int limit) {
        SQLiteConnectivity sqlite = SQLiteConnectivity.getSQLiteConn();
        try {
            String statement = "SELECT * FROM chapter " + "LIMIT " + offset + "," + limit;
            Cursor cursor = sqlite.execCursorQuery(statement);
            cursor.moveToFirst();
            ArrayList<DataPoint> points = new ArrayList<>();
            do {
                points.add(new DataPoint(
                        cursor.getInt(cursor.getColumnIndex("chapter_number")),
                        cursor.getDouble(cursor.getColumnIndex("memory_strength_verse_order"))
                ));
                cursor.moveToNext();
            } while (!cursor.isLast());
            DataPoint[] pointsArray = points.toArray(new DataPoint[points.size()]);
            BarGraphSeries<DataPoint> series = new BarGraphSeries<>(pointsArray);
            return series;
        } catch (Exception ex) {
            return null;
        }
    }

    public static LineGraphSeries<DataPoint> getSingleChapterLineChart(int chapterNumber, int offset, int limit) {
        SQLiteConnectivity sqlite = SQLiteConnectivity.getSQLiteConn();
        try {
            String statement = "SELECT * FROM test " +
                    "WHERE chapter_number = " + chapterNumber +" " +
                    "LIMIT " + offset + "," + limit;
            Cursor cursor = sqlite.execCursorQuery(statement);
            cursor.moveToFirst();
            ArrayList<DataPoint> points = new ArrayList<>();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            do {
                points.add(new DataPoint(
                        dateFormat.parse(cursor.getString(cursor.getColumnIndex("timestamp"))),
                        cursor.getInt(cursor.getColumnIndex("is_memorised"))
                ));
                cursor.moveToNext();
            } while (!cursor.isLast());
            DataPoint[] pointsArray = points.toArray(new DataPoint[points.size()]);
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(pointsArray);

            return series;
        } catch (Exception ex) {
            return null;
        }
    }
}
