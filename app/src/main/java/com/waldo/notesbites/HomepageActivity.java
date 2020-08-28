package com.waldo.notesbites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomepageActivity extends AppCompatActivity {


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

        // create recycler view and adapter for the subjects
        final RecyclerView recyclerView = findViewById(R.id.homepage_subjects_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final HomepageSubjectsAdapter adapter_subjects = new HomepageSubjectsAdapter();
        recyclerView.setAdapter(adapter_subjects);


        // create recycler view and adapter for the recent modules
        final RecyclerView recyclerView_recent_modules = findViewById(R.id.homepage_recent_module_recycler_view);
        recyclerView_recent_modules.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_recent_modules.setHasFixedSize(true);
        final HomepageRecentModulesAdapter adapter_recent_module = new HomepageRecentModulesAdapter();
        recyclerView_recent_modules.setAdapter(adapter_recent_module);

        // instantiate view model
        final HomepageViewModel homepageViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(HomepageViewModel.class);

        //////////////////////////// OBSERVER 1 //////////////////////////////////////
        homepageViewModel.getAllSubjectsSelected().observe(this, new Observer<List<Subject>>() {
            @Override
            public void onChanged(List<Subject> subjects) {
                // update RecyclerView when data in the subjects data changes (the change could occur only to the column "selected")
                adapter_subjects.setSubjects(subjects);
            }

        });


        ////////////////////////// OBSERVER 2 /////////////////////////////////////////
        homepageViewModel.getRecentModulesToBeDisplayed().observe(this, new Observer<List<Module>>() {
            @Override
            public void onChanged(List<Module> modules) {
                if(modules==null){

                }
                else {
                    // update RecyclerView when data in the subjects data changes (the change could occur only to the column "selected")
                    adapter_recent_module.setModules(modules);
                }
            }
        });




        ///////////////////////// OBSERVER 3 //////////////////////////////////////
       /* homepageViewModel.getClickedSubjectID().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer subjectID) {
                List<Module> pippo = homepageViewModel.getRecentModulesToBeDisplayed();
                adapter_recent_module.setModules(pippo);
            }
        });

        */


        //TODO: when we rotate the phone it says "welcome null" --> include it in a observe(), and the problem is solved


        adapter_subjects.setOnItemClickListener(new HomepageSubjectsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Subject subject) {
                homepageViewModel.setRecentModulesBySubjectID(subject.getSubjectID());
            }
        });

    }


    public void startSelectSubjectsActivity(View view) {
        Intent intent = new Intent(HomepageActivity.this, SelectSubjectsActivity.class);
        startActivity(intent);
    }

    public void startPersonalAreaActivity(View view) {
        Intent intent = new Intent(HomepageActivity.this, PersonalAreaActivity.class);
        startActivity(intent);
    }
}
