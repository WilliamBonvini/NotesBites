package com.waldo.notesbites.presentation.simplesubjectoverview;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import com.waldo.notesbites.R;
import com.waldo.notesbites.data.model.Subject;
import com.waldo.notesbites.presentation.selectsubjects.SelectSubjectsViewModel;

import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class SimpleSubjectOverviewActivity extends AppCompatActivity {
    public static String EXTRA_SUBJECT_ID = "SubjectID";
    private SelectSubjectsViewModel selectSubjectsViewModel;
    private SimpleSubjectOverviewViewModel simpleSubjectOverviewViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_subject_overview);

        // retrieve subject name and overview template.
        Intent intent = getIntent();
        int subjectID = intent.getExtras().getInt(EXTRA_SUBJECT_ID);

        // get the selectSubjectsViewModel
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
                assert actionBar != null;
                actionBar.setDisplayHomeAsUpEnabled(true);

                // populate description/overview
                TextView overviewView = findViewById(R.id.simple_overview_text);
                overviewView.setText(subject.getOverview());
                overviewView.setMovementMethod(new ScrollingMovementMethod());
            }

        });

        // get the SimpleSubjectOverviewViewModel
        simpleSubjectOverviewViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(SimpleSubjectOverviewViewModel.class);
        simpleSubjectOverviewViewModel.getModuleNamesBySubjectID(subjectID).observe(this,new Observer<List<String>>(){
            @Override
            public void onChanged(List<String> moduleNames){
                // populate textview with module names
                TextView moduleNamesTextView = findViewById(R.id.simple_overview_module_names);
                StringBuilder sb = new StringBuilder();

                sb.append(("the list of modules are:\n"));
                sb.append(moduleNames.get(0));
                for (String moduleName:moduleNames.subList(1,moduleNames.size())) {
                    sb.append(", ");
                    sb.append(moduleName);
                }
                sb.append(".");

                moduleNamesTextView.setText(sb);
                moduleNamesTextView.setMovementMethod(new ScrollingMovementMethod());

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



