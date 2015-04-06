package com.gre.jamal.memorisequran;

import android.database.Cursor;
import android.graphics.Color;

import com.gre.jamal.memorisequran.db.SQLiteConnectivity;
import com.jjoe64.graphview.ValueDependentColor;
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

    public enum ChapterState {
        GOOD, OKAY, BAD, NULL
    }

    public static BarGraphSeries<DataPoint> getChaptersBarChart(int offset, int limit) {
        SQLiteConnectivity sqlite = SQLiteConnectivity.getSQLiteConn();
        try {
            String statement = "SELECT * FROM chapter " + "LIMIT " + offset + "," + limit;
            Cursor cursor = sqlite.execCursorQuery(statement);
            cursor.moveToFirst();
            ArrayList<DataPoint> points = new ArrayList<>();
            for (int i = 0; i < cursor.getCount(); i++) {
                points.add(new DataPoint(
                        cursor.getInt(cursor.getColumnIndex("chapter_number")),
                        cursor.getDouble(cursor.getColumnIndex("memory_strength_verse_order"))
                ));
                cursor.moveToNext();
            }
            DataPoint[] pointsArray = points.toArray(new DataPoint[points.size()]);
            BarGraphSeries<DataPoint> series = new BarGraphSeries<>(pointsArray);
            series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                @Override
                public int get(DataPoint dataPoint) {
                    ChapterState state = getChapterState((int)(Math.round(dataPoint.getX())));
                    int color;
                    switch (state){
                        case GOOD:
                            color = Color.GREEN;
                            break;
                        case OKAY:
                            color = Color.YELLOW;
                            break;
                        case BAD:
                            color = Color.RED;
                            break;
                        default:
                            color = Color.YELLOW;
                            break;
                    }
                    return color;
                }

                private ChapterState getChapterState(int chapterNumber){
                    String statement = "SELECT * FROM test WHERE chapter_number = "+
                            chapterNumber
                            +" ORDER BY timestamp DESC  LIMIT 0,2";
                    SQLiteConnectivity sqlite = SQLiteConnectivity.getSQLiteConn();
                    Cursor cursor = sqlite.execCursorQuery(statement);
                    cursor.moveToFirst();
                    try {
                        int latestScore = cursor.getInt(cursor.getColumnIndex("memory_strength"));

                        //get current date, last test date, date 2 weeks ago and one month ago.
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        GregorianCalendar calendar = new GregorianCalendar();
                        calendar.roll(Calendar.MONTH, -1);
                        Date oneMonthAgo = calendar.getTime();
                        calendar = new GregorianCalendar();
                        calendar.roll(Calendar.WEEK_OF_YEAR, -2);
                        Date twoWeeksAgo = calendar.getTime();
                        Date latestTestDate = null;
                        try {
                            latestTestDate = dateFormat.parse(cursor.getString(cursor.getColumnIndex("timestamp")));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        cursor.moveToNext();
                        int prevScore = cursor.getInt(cursor.getColumnIndex("memory_strength"));

                        ChapterState state = ChapterState.GOOD;
                        if (latestScore < prevScore) {
                            state = ChapterState.BAD;
                        }
                        if (latestScore < 100 && latestScore == prevScore) {
                            state = ChapterState.OKAY;
                        }
                        if (latestTestDate.before(oneMonthAgo)) {
                            state = ChapterState.BAD;
                        }
                        if (latestTestDate.before(twoWeeksAgo)) {
                            state = ChapterState.OKAY;
                        }
                        return state;
                    } catch (Exception ex) {
                        return ChapterState.NULL;
                    }
                }

            });
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
        for (int i = 0; i < cursor.getCount(); i++) {

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
            }
            cursor.moveToNext();
        }


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
