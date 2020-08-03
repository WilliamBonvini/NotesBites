package com.waldo.notesbites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class SubjectOverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // default stuff
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_overview);

        // retrieve subject object
        Intent intent = getIntent();
        String jsonSubjectOverview = intent.getStringExtra("SubjectOverview");
        Subject subject = new com.google.gson.Gson().fromJson(jsonSubjectOverview,Subject.class);

        // get reference to Views
        TextView subjectTitleView = (TextView) findViewById(R.id.subject_title);
        TextView subjectDescriptionView = (TextView) findViewById(R.id.subject_description);
        ListView listView = (ListView) findViewById(R.id.list_of_modules);

        // populate first two Views
        subjectTitleView.setText(subject.getName());
        subjectDescriptionView.setText(subject.getDescription());

        // the following populates the list view with data from the module array
        ArrayAdapter<Subject> listAdapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                subject.getModules());
        ListView listOfModules = (ListView) findViewById(R.id.list_of_modules);
        listOfModules.setAdapter(listAdapter);
    }
}
