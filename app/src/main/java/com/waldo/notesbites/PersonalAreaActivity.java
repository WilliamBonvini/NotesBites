package com.waldo.notesbites;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Random;


public class PersonalAreaActivity extends AppCompatActivity {
    private ArrayList<String> subjectNamesList = new ArrayList<String>();
    private Cursor cursor;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal_area);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));


        try {
            doStuff();
        } catch (SQLiteException e) {
            Toast.makeText(this, "Database unavavailable", Toast.LENGTH_SHORT).show();
        }
    }

    private void doStuff(){
        // query selected subjects to display them in the layout
        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getReadableDatabase();
        cursor = db.query("SUBJECT",
                new String[]{"_id","NAME"},
                "SELECTED = 1",
                null,
                null,null,null);



        // populate array with selected subjects ids (we'll need it to pass selected subject to next layout)
        // at the same time populate array list with selected subjects names to display them in a ListView
        final int[] subjectsIDs = new int[cursor.getCount()];
        int i = 0;
        Random random = new Random();
        int upperbound = 100;
        if(cursor.moveToFirst()){

            subjectsIDs[i] = cursor.getInt(0);
            subjectNamesList.add(cursor.getString(1) + "       " + (random.nextInt(upperbound)+1)+"%");

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
                        Intent intent = new Intent(PersonalAreaActivity.this,
                                QuizResultsActivity.class);

                        intent.putExtra(QuizResultsActivity.EXTRA_SUBJECTID, subjectsIDs[position]);
                        startActivity(intent);

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
    }
    // Close the cursor and database in the onDestroy() method
    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }

}
