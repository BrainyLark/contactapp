package com.archer.contactapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {
    TextView eventView;
    CalendarContentResolver calendarResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        eventView = (TextView) findViewById(R.id.eventView);

        calendarResolver = new CalendarContentResolver(this);

        Cursor eventData = calendarResolver.getEvents();

        Calendar calendarBeginDate = Calendar.getInstance();

        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        if(eventData != null && eventData.getCount() > 0) {
            String text = "";
            while(eventData.moveToNext()) {
                String title = null;
                long eventID = 0;
                long beginVal = 0;

                eventID = eventData.getLong(CalendarContentResolver.PROJECTION_ID_INDEX);
                beginVal = eventData.getLong(CalendarContentResolver.PROJECTION_BEGIN_INDEX);
                title = eventData.getString(CalendarContentResolver.PROJECTION_TITLE_INDEX);

                calendarBeginDate.setTimeInMillis(beginVal);

                text += "Event title: " + title + "\n" + "Event Date: " + formatter.format(calendarBeginDate.getTime()) + "\nEvent ID: " + eventID + "\n\n";
            }
            eventView.setText(text);
        } else {
            eventView.setText("there is no event");
        }

    }
}
