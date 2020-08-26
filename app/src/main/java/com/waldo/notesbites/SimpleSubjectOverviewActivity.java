package com.waldo.notesbites;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class SimpleSubjectOverviewActivity extends AppCompatActivity {
    public static String EXTRA_SUBJECT_ID = "SubjectID";
    private String overview;
    private String subjectName;
    private SelectSubjectsViewModel selectSubjectsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_subject_overview);

        // retrieve subject name and overview template.
        Intent intent = getIntent();
        int subjectID = intent.getExtras().getInt(EXTRA_SUBJECT_ID);

        // get the viewModel
        selectSubjectsViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(SelectSubjectsViewModel.class);
        //modify UI accordingly
        selectSubjectsViewModel.getSubjectByID(subjectID).observe(this, new Observer<Subject>() {
            @Override
            public void onChanged(Subject subject) {
                // populate toolbar
                Toolbar toolbar = findViewById(R.id.toolbar);
                toolbar.setTitle(subject.getName() + " Overview");
                setSupportActionBar(toolbar);

                // Add up button
                ActionBar actionBar = getSupportActionBar();
                actionBar.setDisplayHomeAsUpEnabled(true);

                // populate description/overview
                StringBuilder fullOverview = new StringBuilder();
                fullOverview.append(subject.getOverview());
                //.......


                TextView overviewView = findViewById(R.id.simple_overview_text);
                overviewView.setText(fullOverview);
            }

        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return(super.onOptionsItemSelected(item));
    }

}





/*
package com.waldo.notesbites;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SimpleSubjectOverviewActivity extends AppCompatActivity {
    public static String EXTRA_SUBJECT_ID = "SubjectID";
    private String overview;
    private String subjectName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_subject_overview);


        // retrieve subject name and overview template.
        Intent intent = getIntent();
        int subjectID = intent.getExtras().getInt(EXTRA_SUBJECT_ID);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor1 = db.query("SUBJECT",new String[]{"NAME","OVERVIEW"},"_id = ?",new String[]{Integer.toString(subjectID)},null,null,null);
        if(cursor1.moveToFirst()){
            subjectName = cursor1.getString(0);
            overview    = cursor1.getString(1);
        }
        cursor1.close();

        // populate toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(subjectName + " Overview");
        setSupportActionBar(toolbar);

        // Add up button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        // populate description/overview
        StringBuilder fullOverview = new StringBuilder();
        fullOverview.append(overview);
        Cursor cursor2 = db.query("MODULE",new String[]{"NAME"},"BELONGING_SUBJECT = ?", new String[]{Integer.toString(subjectID)},null,null,null);
        if(cursor2.moveToFirst()){
            fullOverview.append(cursor2.getString(0));
            while (cursor2.moveToNext()){
                fullOverview.append(", ");
                fullOverview.append(cursor2.getString(0));
            }
        }
        cursor2.close();
        TextView overviewView = findViewById(R.id.simple_overview_text);
        overviewView.setText(fullOverview);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return(super.onOptionsItemSelected(item));
    }

}



*/