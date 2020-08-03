package com.waldo.notesbites;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.View;
public class SubjectsListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectslist);
        //Create an OnItemCLickListener
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?>listView,
                                                View itemView,
                                                int position,
                                                long id){
                Intent intent = new Intent(SubjectsListActivity.this,
                        SubjectOverviewActivity.class);
                if(position == 0){
                    intent.putExtra("SubjectOverview",SubjectsList.subjects[0].toString());
                    startActivity(intent);
                }
                if(position == 1){
                    intent.putExtra("SubjectOverview",SubjectsList.subjects[1].toString());
                    startActivity(intent);
                }
                if(position == 2){
                    intent.putExtra("SubjectOverview",SubjectsList.subjects[2].toString());
                    startActivity(intent);
                }
            }
        };
        // add the listener to the list view
        ListView listView = (ListView) findViewById(R.id.list_of_subjects);
        listView.setOnItemClickListener(itemClickListener);
    }
}
