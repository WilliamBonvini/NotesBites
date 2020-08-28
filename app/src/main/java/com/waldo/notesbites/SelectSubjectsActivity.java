
package com.waldo.notesbites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SelectSubjectsActivity extends AppCompatActivity{
    private SelectSubjectsViewModel selectSubjectsViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate((savedInstanceState));
        setContentView(R.layout.activity_select_subjects);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final SelectSubjectAdapter adapter = new SelectSubjectAdapter();
        recyclerView.setAdapter(adapter);

        selectSubjectsViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(SelectSubjectsViewModel.class);
        selectSubjectsViewModel.getAllSubjects().observe(this, new Observer<List<Subject>>() {
            @Override
            public void onChanged(List<Subject> subjects) {
                // update RecyclerView when data in the subjects data changes (the change could occur only to the column "selected")
                adapter.setSubjects(subjects);
            }
        });


        adapter.setOnItemClickListener(new SelectSubjectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Subject subject) {
                if (!subject.isSelected()){
                    selectSubjectsViewModel.setSelectedTrue(subject.getSubjectID());
                    Log.w("com.waldo.notesbites","true!");
                }else{
                    selectSubjectsViewModel.setSelectedFalse(subject.getSubjectID());
                    Log.w("com.waldo.notesbites","false!");

                }


            }
        });

    }

    public void onClickStartSimpleSubjectOverview(View view) {
        Intent intent = new Intent(SelectSubjectsActivity.this, SimpleSubjectOverviewActivity.class);
        intent.putExtra(SimpleSubjectOverviewActivity.EXTRA_SUBJECT_ID,view.getId());
        startActivity(intent);
    }

    public void onClickConfirmSelectedSubjects(View view) {
        Intent intent = new Intent(SelectSubjectsActivity.this,SignInActivity.class);
        startActivity(intent);
    }


}
