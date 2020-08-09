package com.waldo.notesbites;

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

public class SelectSubjectsActivity extends Activity {

    LinearLayout linearMain;
    CheckBox checkBox;
    SQLiteOpenHelper databaseHelper;
    Intent selectSubjectsIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subjects);
        selectSubjectsIntent = new Intent(SelectSubjectsActivity.this,
                GuestHomepageActivity.class);



        linearMain = (LinearLayout) findViewById(R.id.linear_select_subjects);
        ArrayList<String> subjectsNames = new ArrayList<String>();
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("SUBJECT",
                                new String[]{"NAME"},
                                null,
                                null,
                                null,
                                null,
                                null);
        if(cursor.moveToFirst()){
            subjectsNames.add(cursor.getString(0));
            while(cursor.moveToNext()){
                subjectsNames.add(cursor.getString(0));
            }
        }
        cursor.close();
        for(int i=0;i<subjectsNames.size();i++){
            checkBox = new CheckBox(this);
            checkBox.setId(i);
            checkBox.setText(subjectsNames.get(i));
            checkBox.setOnClickListener(onCheckBoxClicked(checkBox));
            linearMain.addView(checkBox);
        }

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

        // TODO: put all the views in the layout and populate them by using their ids



    }

    private View.OnClickListener onCheckBoxClicked(final Button button){
        // flag to True the field "selected" in SUBJECT
        return new View.OnClickListener() {

            public void onClick(View v){
                try{
                    SQLiteDatabase db = databaseHelper.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("SELECTED",((CheckBox)v).isChecked());        //((CheckBox)v).isChecked() ? 1 : 0);
                    db.update("SUBJECT",contentValues,"NAME=?",new String[]{((CheckBox)v).getText().toString()});
                    db.close();
                    // TODO: 07/08/2020 implement: set to false field "selected" in SUBJECTS if user clicks again on checkbox, or. easier, update tables only when user clicks on confirm button
                }catch (SQLiteException e){
                    Toast toast = Toast.makeText(v.getContext(),"Database unavailable",Toast.LENGTH_SHORT);
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

}
