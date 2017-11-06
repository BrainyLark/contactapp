package com.archer.contactapp;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import java.util.Calendar;
import java.util.TimeZone;

import android.net.Uri;
import android.provider.CalendarContract;
import android.widget.Toast;

/**
 * Created by archer on 2017-11-06.
 */

public class CalendarContentResolver {
    public static final String[] INSTANCE_PROJECTION = {
            CalendarContract.Instances.EVENT_ID,
            CalendarContract.Instances.BEGIN,
            CalendarContract.Instances.TITLE
    };

    public static final int PROJECTION_ID_INDEX = 0;
    public static final int PROJECTION_BEGIN_INDEX = 1;
    public static final int PROJECTION_TITLE_INDEX = 2;

    private long startMillis;
    private long endMillis;
    private static final int TOTAL_DAYS_OF_WEEK = 7;

    ContentResolver contentResolver;

    public CalendarContentResolver(Context context) {
        contentResolver = context.getContentResolver();

        long daysUntilWeekend = 1000 * 60 * 60 * 24 * (TOTAL_DAYS_OF_WEEK - Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
        startMillis = Calendar.getInstance(TimeZone.getTimeZone("GMT-8")).getTimeInMillis() + daysUntilWeekend;
        endMillis = startMillis + 1000 * 60 * 60 * 24 * 7;
    }

    public Cursor getEvents() {
        Uri.Builder builder = CalendarContract.Instances.CONTENT_URI.buildUpon();
        ContentUris.appendId(builder, startMillis);
        ContentUris.appendId(builder, endMillis);

        return this.contentResolver.query(
                builder.build(),
                INSTANCE_PROJECTION,
                null,
                null,
                null
        );
    }
}
