package com.gre.jamal.memorisequran;

import android.database.Cursor;

import com.gre.jamal.memorisequran.db.SQLiteConnectivity;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

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

        String statement = "SELECT * FROM test " +
                "WHERE chapter_number = " + chapterNumber + " " +
                "ORDER BY timestamp DESC " +
                "LIMIT " + offset + "," + limit;

        ArrayList<DataPoint> points = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        SortedMap<Date, Integer> testStrengths = new TreeMap<>();

        Cursor cursor = sqlite.execCursorQuery(statement);
        cursor.moveToFirst();
        do {

            Date date = null;
            try {
                String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
                date = dateFormat.parse(timestamp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar now = new GregorianCalendar();
            now.roll(Calendar.MONTH, 9);
            Date cutOff = now.getTime();
            if (date.after(cutOff)) {
                int strength = cursor.getInt(cursor.getColumnIndex("memory_strength"));
                testStrengths.put(date, strength);
                //points.add(new DataPoint(date, strength));
            }
            cursor.moveToNext();
        } while (!cursor.isLast());


        int i = 0;
        for (Map.Entry<Date, Integer> pair : testStrengths.entrySet()) {
            points.add(new DataPoint(i, pair.getValue().intValue()));
            i++;
        }
        DataPoint[] pointsArray = points.toArray(new DataPoint[points.size()]);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(pointsArray);

        return series;
    }
}
