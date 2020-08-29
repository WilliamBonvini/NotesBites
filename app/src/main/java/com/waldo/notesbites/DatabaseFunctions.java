package com.waldo.notesbites;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



/*

- Quiz
- personal area



- recent activities
- markdown visualization
 */

public class DatabaseFunctions extends AppCompatActivity {
    private Context context;


    public DatabaseFunctions(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(this.context);
    }
    SQLiteOpenHelper databaseHelper;
    SQLiteDatabase db;



    public String getSubjectName_fromID(int subjectID){
        String subjectTitleText = null;
        try {
            db = databaseHelper.getReadableDatabase();

            Cursor cursor = db.query(
                    "SUBJECT",
                    new String[]{"NAME"},
                    "_id=?",
                    new String[]{Integer.toString(subjectID)},    //perchè questo?
                    null, null, null);

            if (cursor.moveToFirst()) {
                subjectTitleText = cursor.getString(0);
            }

            cursor.close();

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this,
                    "Database Unavailable",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
        return subjectTitleText;

    }

    public int getSubjectID_fromModuleID(int moduleID){
        int subjectID = -1;
        db = databaseHelper.getReadableDatabase();
        Cursor cursor1 = db.query("MODULE", null, "_id = ?",new String[]{Integer.toString(moduleID)}, null, null, null, null);
        if(cursor1.moveToFirst()){
            subjectID = cursor1.getInt(4);
        }
        cursor1.close();

        return subjectID;
    }
}
