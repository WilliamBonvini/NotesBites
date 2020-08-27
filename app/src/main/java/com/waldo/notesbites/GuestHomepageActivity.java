package com.waldo.notesbites;
import android.app.Activity;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GuestHomepageActivity extends AppCompatActivity {
    private ArrayList<String> subjectNamesList = new ArrayList<String>();
    private Cursor cursor;
    private SQLiteDatabase db;
    private String subjectClickedName;
    public int subjectClickedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_homepage);

        final RecyclerView recyclerView = findViewById(R.id.guest_homepage_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Guest Homepage");
        setSupportActionBar(toolbar);

        //final SelectSubjectAdapter adapter = new SelectSubjectAdapter();
        final GuestHomepageAdapter adapter = new GuestHomepageAdapter();
        recyclerView.setAdapter(adapter);


        final GuestHomepageViewModel guestHomepageViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(GuestHomepageViewModel.class);
        guestHomepageViewModel.getAllSubjectsSelected().observe(this, new Observer<List<Subject>>() {
            @Override
            public void onChanged(List<Subject> subjects) {
                // update RecyclerView when data in the subjects data changes (the change could occur only to the column "selected")
                adapter.setSubjects(subjects);
            }
        });

        adapter.setOnItemClickListener(new GuestHomepageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Subject subject) {
                subjectClickedID = subject.getSubjectID();
                Intent intent = new Intent(GuestHomepageActivity.this, SubjectOverviewActivity.class);
                intent.putExtra("subjectID", subjectClickedID);
                startActivity(intent);
            }
        });

    }

    public void startSelectSubjectsActivity(View view) {
        Intent intent = new Intent(GuestHomepageActivity.this, SelectSubjectsActivity.class);
        startActivity(intent);
    }

    public void startSignInActivity(View view) {
        Intent intent = new Intent(GuestHomepageActivity.this, SignInActivity.class);
        startActivity(intent);
    }
    public void startSubjectOverviewActivity(View view){
        Intent intent = new Intent(GuestHomepageActivity.this, SubjectOverviewActivity.class);
        //intent.putExtra(String.valueOf(SubjectOverviewActivity.EXTRA_SUBJECTID), subjectClickedID);
        startActivity(intent);
    }




//        try {
//            doStuff();
//        } catch (SQLiteException e) {
//            Toast.makeText(this, "Database unavavailable", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//
//
//    private void doStuff(){
//        // query selected subjects to display them in the layout
//        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
//        db = databaseHelper.getReadableDatabase();
//        cursor = db.query("SUBJECT",
//                new String[]{"_id","NAME"},
//                "SELECTED = 1",
//                null,
//                null,null,null);
//
//
//
//        // populate array with selected subjects ids (we'll need it to pass selected subject to next layout)
//        // at the same time populate array list with selected subjects names to display them in a ListView
//        final int[] subjectsIDs = new int[cursor.getCount()];
//        int i = 0;
//        if(cursor.moveToFirst()){
//
//            subjectsIDs[i] = cursor.getInt(0);
//            subjectNamesList.add(cursor.getString(1));
//
//            while(cursor.moveToNext()){
//                i++;
//                subjectsIDs[i] = cursor.getInt(0);
//                subjectNamesList.add(cursor.getString(1));
//            }
//        }
//
//
//        // create an OnItemCLickListener in order to change layout once the user clicks on a subject
//        AdapterView.OnItemClickListener itemClickListener =
//                new AdapterView.OnItemClickListener() {
//                    public void onItemClick(AdapterView<?> listView,
//                                            View itemView,
//                                            int position,
//                                            long id) {
//                        Intent intent = new Intent(GuestHomepageActivity.this,
//                                SubjectOverviewActivity.class);
//
//                        intent.putExtra(SubjectOverviewActivity.EXTRA_SUBJECTID, subjectsIDs[position]);
//                        startActivity(intent);
//                        ;
//                    }
//                };
//
//        // create adapter in order to dynamically populate list with the subjects the user is interested in
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,
//                subjectNamesList);
//
//
//        // populate the list view and add the listener to it
//        ListView listView = (ListView) findViewById(R.id.list_of_subjects);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(itemClickListener);
//    }
//
//    // Close the cursor and database in the onDestroy() method
//    @Override
//    public void onDestroy(){
//        super.onDestroy();
//        cursor.close();
//        db.close();
//    }
//
//    public void startSingInActivity(View view) {
//        Intent intent = new Intent(GuestHomepageActivity.this, SignInActivity.class);
//        startActivity(intent);
//    }
//
//    public void startSelectSubjectsActivity(View view) {
//        Intent intent = new Intent(GuestHomepageActivity.this, SelectSubjectsActivity.class);
//        startActivity(intent);
//    }
}


