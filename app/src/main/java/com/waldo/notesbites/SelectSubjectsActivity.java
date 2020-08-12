package com.waldo.notesbites;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.widget.Toast.*;

public class SelectSubjectsActivity extends Activity {

    // TODO: for all activities: deal with the database in a secondary thread (page 723 on)

    private SQLiteOpenHelper databaseHelper;
    private SQLiteDatabase db;
    private Intent selectSubjectsIntent;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subjects);
        selectSubjectsIntent = new Intent(SelectSubjectsActivity.this,
                GuestHomepageActivity.class);



        LinearLayout linearMain = (LinearLayout) findViewById(R.id.linear_select_subjects);
        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("SUBJECT",
                                new String[]{"NAME","SELECTED"},
                                null,
                                null,
                                null,
                                null,
                                null);

        int i = 0;
        while(cursor.moveToNext()){
            CheckBox checkBox = new CheckBox(this);
            Button btn = new Button(this);
            checkBox.setId(i);
            btn.setId(10+i); //da modificare
            checkBox.setText(cursor.getString(0));
            btn.setText(cursor.getString(0) + " overview");
            btn.setOnClickListener(onBtnClicked(btn));
            boolean isSelected = cursor.getInt(1) == 1;
            checkBox.setChecked(isSelected);
            checkBox.setOnClickListener(onCheckBoxClicked(checkBox));
            linearMain.addView(checkBox);
            linearMain.addView(btn);
            i++;
        }
        cursor.close();


        //add bottom message below checkboxes
        TextView bottom_message = new TextView(this);
        bottom_message.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        bottom_message.setText(R.string.select_subjects_message_2);
        linearMain.addView(bottom_message);

        //add button to confirm selected subjects
        Button button = new Button(this);
        button.setText(R.string.select_subjects_confirm_button_text);
        button.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        button.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        button.setOnClickListener(onButtonClicked(button));
        linearMain.addView(button);

    }

    private View.OnClickListener onCheckBoxClicked(final Button button){
        // flag to True the field "selected" in SUBJECT
        return new View.OnClickListener() {

            public void onClick(View v){
                try{
                    db = databaseHelper.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("SELECTED",((CheckBox)v).isChecked());        //((CheckBox)v).isChecked() ? 1 : 0);
                    db.update("SUBJECT",contentValues,"NAME=?",new String[]{((CheckBox)v).getText().toString()});
                    db.close();
                }catch (SQLiteException e){
                    Toast toast = makeText(v.getContext(),"Database unavailable", LENGTH_SHORT);
                    toast.show();
                }
            }
        };
    }

    private View.OnClickListener onButtonClicked(Button button){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(selectSubjectsIntent);
            }
        };
    }


    @Override
    public void onRestart() {
        super.onRestart();
        // TODO: page 718. I don't think I need to implement it, but maybe I'll need to for some reason
    }

     View.OnClickListener onBtnClicked(Button button) {
         return new View.OnClickListener() {

             public void onClick(View v) {
                 Intent visualizeOverview = new Intent(SelectSubjectsActivity.this,
                         SimpleSubjectOverviewActivity.class);
                 startActivity(visualizeOverview);
             }

         };
     }

}
