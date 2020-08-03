package com.waldo.notesbites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

        //retrieve the ID of the subject, in order to pass it to the Module Activity
        final int subjectID = subject.getSubjectID();

        // get reference to Views
        TextView subjectTitleView = (TextView) findViewById(R.id.subject_title);
        TextView subjectDescriptionView = (TextView) findViewById(R.id.subject_description);
        ImageView subjectImageView = (ImageView) findViewById(R.id.subject_image);
        ListView listOfModulesView = (ListView) findViewById(R.id.list_of_modules);

        // populate first three Views
        subjectTitleView.setText(subject.getName());
        subjectDescriptionView.setText(subject.getDescription());
        subjectImageView.setImageResource(subject.getImageResourceID());

        // the following populates the list view with data from the module array
        ArrayAdapter<Subject> listAdapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                subject.getModules());
        listOfModulesView.setAdapter(listAdapter);


        // Create a listener for clicks on modules
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> listDrinks,
                                            View itemView,
                                            int position,
                                            long moduleID){
                        // Pass the module the user clicks on to ModuleActivity
                        Intent intent = new Intent(SubjectOverviewActivity.this,
                                                    ModuleActivity.class);
                        intent.putExtra(ModuleActivity.EXTRA_MODULEID,(int) moduleID);
                        intent.putExtra(ModuleActivity.EXTRA_SUBJECTID, subjectID);
                        startActivity(intent);
                    }
                };
        // Assign the listener to the list view
        listOfModulesView.setOnItemClickListener(itemClickListener);


    }
}
