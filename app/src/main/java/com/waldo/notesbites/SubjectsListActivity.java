package com.waldo.notesbites;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.View;

import java.util.ArrayList;

public class SubjectsListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectslist);

        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("SUBJECT",new String[]{"_id"},null,null,null,null,null);
        final int[] subjectsIDs = new int[cursor.getCount()];
        int i = 0;
        if(cursor.moveToFirst()){
            subjectsIDs[i] = cursor.getInt(0);
            while(cursor.moveToNext()){
                i++;
                subjectsIDs[i] = cursor.getInt(0);
            }
        }
        cursor.close();
        db.close();

        //Create an OnItemCLickListener
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?>listView,
                                                View itemView,
                                                int position,
                                                long id){
                Intent intent = new Intent(SubjectsListActivity.this,
                        SubjectOverviewActivity.class);

                intent.putExtra(SubjectOverviewActivity.EXTRA_SUBJECTID,subjectsIDs[position]);
                startActivity(intent);
;               /*
                if(position == 0){

                    intent.putExtra("SubjectOverview",subjectsIDs[position]);
                    startActivity(intent);
                }
                if(position == 1){
                    intent.putExtra("SubjectOverview",subjectsIDs[1]);
                    startActivity(intent);
                }
                if(position == 2){
                    intent.putExtra("SubjectOverview",subjectsIDs[2]);
                    startActivity(intent);
                }
                */
            }
        };
        // add the listener to the list view
        ListView listView = (ListView) findViewById(R.id.list_of_subjects);
        listView.setOnItemClickListener(itemClickListener);
    }
}
