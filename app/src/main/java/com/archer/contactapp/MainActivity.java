package com.archer.contactapp;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private TextView textView;
    private boolean isFirst = false;
    private String[] mColumnProjection = new String[] {
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Email.DATA
    };

    private final int MAIN_LOADER_ID = 1;

    public void initialize() {
        textView = (TextView) findViewById(R.id.textView);
    }

    public void showBtn(View view) {
        if(!isFirst) {
            getLoaderManager().initLoader(MAIN_LOADER_ID, null, this);
            isFirst = true;
        } else {
            getLoaderManager().restartLoader(MAIN_LOADER_ID, null, this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, ContactsContract.CommonDataKinds.Phone.CONTENT_URI, mColumnProjection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if(cursor != null && cursor.getCount() > 0) {
            StringBuilder queryResult = new StringBuilder("");
            while (cursor.moveToNext()) {
                queryResult.append(cursor.getString(0) + "\n" + cursor.getString(1) + "\n\n");
            }
            textView.setText(queryResult);
        } else {
            textView.setText("No address has been found in this provider!");
        }
        cursor.close();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        //adapter.swapCursor(null);
    }
}
