package com.waldo.notesbites;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class GuestHomepageActivity extends Activity {
    ArrayList<String> subjectNamesList = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_homepage);
        try {
            doStuff();
        } catch (SQLiteException e) {
            Toast.makeText(this, "Database unavavailable", Toast.LENGTH_SHORT);
        }
    }



    private void doStuff(){
        // query selected subjects to display them in the layout
        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("SUBJECT",
                new String[]{"_id","NAME"},
                "SELECTED = 1",
                null,//new String[]{Integer.toString(1)},
                null,null,null);

        if(cursor.getCount()==0){
            Log.println(Log.DEBUG,"baaad","no tuple returned!!!!");
        }

        // populate array with selected subjects ids (we'll need it to pass selected subject to next layout)
        // at the same time populate array list with selected subjects names to display them in a ListView
        final int[] subjectsIDs = new int[cursor.getCount()];
        int i = 0;
        if(cursor.moveToFirst()){
            Log.println(Log.ERROR,"good","something has been returned");

            subjectsIDs[i] = cursor.getInt(0);
            subjectNamesList.add(cursor.getString(1));

            while(cursor.moveToNext()){
                i++;
                subjectsIDs[i] = cursor.getInt(0);
                subjectNamesList.add(cursor.getString(1));
            }
        }


        // create an OnItemCLickListener in order to change layout once the user clicks on a subject
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> listView,
                                            View itemView,
                                            int position,
                                            long id) {
                        Intent intent = new Intent(GuestHomepageActivity.this,
                                SubjectOverviewActivity.class);

                        intent.putExtra(SubjectOverviewActivity.EXTRA_SUBJECTID, subjectsIDs[position]);
                        startActivity(intent);
                        ;
                    }
                };

        // create adapter in order to dynamically populate list with the subjects the user is interested in
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                subjectNamesList);


        // populate the list view and add the listener to it
        ListView listView = (ListView) findViewById(R.id.list_of_subjects);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(itemClickListener);

        cursor.close();
        db.close();
    }
}


