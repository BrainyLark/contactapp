package com.archer.contactapp;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MiddlewareActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_middleware);
        TabHost tabHost = getTabHost();
        tabHost.addTab(tabHost.newTabSpec("contact").setIndicator("Contact").setContent(new Intent(this, MainActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("calendar").setIndicator("Calendar").setContent(new Intent(this, CalendarActivity.class)));

        tabHost.setCurrentTab(0);
    }
}
