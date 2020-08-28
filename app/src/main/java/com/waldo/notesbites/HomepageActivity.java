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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomepageActivity extends AppCompatActivity {

    public int subjectClickedID = -1;


    public static String PERSON_NAME = "PERSON_NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        Intent intent = getIntent();
        PERSON_NAME = (String) intent.getExtras().get(PERSON_NAME);

        TextView welcome_text = findViewById(R.id.welcome_text);
        welcome_text.setText("welcome " + PERSON_NAME);

        final RecyclerView recyclerView = findViewById(R.id.homepage_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final HomepageAdapter adapter = new HomepageAdapter();
        recyclerView.setAdapter(adapter);

        final HomepageViewModel homepageViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(HomepageViewModel.class);
        homepageViewModel.getAllSubjectsSelected().observe(this, new Observer<List<Subject>>() {
            @Override
            public void onChanged(List<Subject> subjects) {
                // update RecyclerView when data in the subjects data changes (the change could occur only to the column "selected")
                adapter.setSubjects(subjects);
            }
        });

        final RecyclerView recyclerView_recent_modules = findViewById(R.id.homepage_recent_module_recycler_view);
        recyclerView_recent_modules.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_recent_modules.setHasFixedSize(true);

        final HomepageAdapterRecentModules adapter_recent_module = new HomepageAdapterRecentModules();
        recyclerView_recent_modules.setAdapter(adapter_recent_module);

        adapter.setOnItemClickListener(new HomepageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Subject subject) {
                subjectClickedID = subject.getSubjectID();
            }
        });



        homepageViewModel.getRecentModules(subjectClickedID).observe(this, new Observer<List<Module>>() {
            @Override
            public void onChanged(List<Module> modules) {
                // update RecyclerView when data in the subjects data changes (the change could occur only to the column "selected")
                adapter_recent_module.setModules(modules);
            }
        });
//
//
//        adapter.setOnItemClickListener(new HomepageAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(Subject subject) {
//                subjectClickedID = subject.getSubjectID();
//                recentModules = homepageViewModel.getRecentModules(subjectClickedID);
//
//            }
//        });


    }

//        try {
//            doStuff();
//        } catch (SQLiteException e) {
//            Toast.makeText(this, "Database unavavailable", Toast.LENGTH_SHORT).show();
//        }



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
//                        Intent intent = new Intent(HomepageActivity.this,
//                                SubjectOverviewActivity.class);
//
//                        //intent.putExtra(SubjectOverviewActivity.EXTRA_SUBJECTID, subjectsIDs[position]);
//                        startActivity(intent);
//
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
//    // Close the cursor and database in the onDestroy() method
//    @Override
//    public void onDestroy(){
//        super.onDestroy();
//        cursor.close();
//        db.close();
//    }

    public void startSelectSubjectsActivity(View view) {
        Intent intent = new Intent(HomepageActivity.this, SelectSubjectsActivity.class);
        startActivity(intent);
    }

    public void startPersonalAreaActivity(View view) {
        Intent intent = new Intent(HomepageActivity.this, PersonalAreaActivity.class);
        startActivity(intent);
    }
}
