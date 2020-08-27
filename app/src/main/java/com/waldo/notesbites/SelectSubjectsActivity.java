
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
        Log.w("duuuuuuuuuuung","duuuuuuuuuuuuuung");
        startActivity(intent);
    }


}
/*
package com.waldo.notesbites;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import static android.widget.Toast.*;

public class SelectSubjectsActivity extends AppCompatActivity {

    // TODO: for all activities: deal with the database in a secondary thread (page 723 on)

    private SQLiteOpenHelper databaseHelper;
    private SQLiteDatabase db;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subjects);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));





        LinearLayout linearMain = (LinearLayout) findViewById(R.id.linear_select_subjects);
        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("SUBJECT",
                                new String[]{"_id","NAME","SELECTED"},
                                null,
                                null,
                                null,
                                null,
                                null);

        int i = 0;
        while(cursor.moveToNext()){
            // create row
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setId(cursor.getInt(0));

            // create and populate checkbox
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(cursor.getString(1));
            boolean isSelected = cursor.getInt(2) == 1;
            checkBox.setChecked(isSelected);
            checkBox.setOnClickListener(onCheckBoxClicked(checkBox));
            row.addView(checkBox);

            // create and populate overview button
            Button btn = new Button(this);
            btn.setText(cursor.getString(1) + " overview");
            btn.setOnClickListener(onOverviewButtonClicked(btn));
            row.addView(btn);

            // add row to main layout
            linearMain.addView(row);
            i++;
        }
        cursor.close();


        //add bottom message below checkboxes
        TextView bottom_message = new TextView(this);
        bottom_message.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        bottom_message.setText(R.string.select_subjects_message_2);
        bottom_message.setTextSize(20);
        linearMain.addView(bottom_message);

        //add button to confirm selected subjects
        Button button = new Button(this);
        button.setText(R.string.select_subjects_confirm_button_text);
        button.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        button.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        button.setOnClickListener(onConfirmButtonClicked(button));
        linearMain.addView(button);

    }

    private View.OnClickListener onCheckBoxClicked(final Button button){
        // flag to True the field "selected" in SUBJECT
        return new View.OnClickListener() {

            public void onClick(View v){
                try{
                    db = databaseHelper.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("SELECTED",((CheckBox)v).isChecked());
                    db.update("SUBJECT",contentValues,"NAME=?",new String[]{((CheckBox)v).getText().toString()});
                    db.close();
                }catch (SQLiteException e){
                    Toast toast = makeText(v.getContext(),"Database unavailable", LENGTH_SHORT);
                    toast.show();
                }
            }
        };
    }

    private View.OnClickListener onConfirmButtonClicked(Button button){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectSubjectsIntent = new Intent(SelectSubjectsActivity.this,
                GuestHomepageActivity.class);
                startActivity(selectSubjectsIntent);
            }
        };
    }


    @Override
    public void onRestart() {
        super.onRestart();
        // TODO: page 718. I don't think I need to implement it, but maybe I'll need to for some reason
    }

     View.OnClickListener onOverviewButtonClicked(Button button) {
         return new View.OnClickListener() {

             public void onClick(View v) {
                 Intent selectSubjectsOverview = new Intent(SelectSubjectsActivity.this, SimpleSubjectOverviewActivity.class);
                 selectSubjectsOverview.putExtra(SimpleSubjectOverviewActivity.EXTRA_SUBJECT_ID,((ViewGroup)v.getParent()).getId());
                 startActivity(selectSubjectsOverview);
             }

         };
     }

}



 */