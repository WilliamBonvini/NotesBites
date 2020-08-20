package com.waldo.notesbites;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DatabaseFunctions extends AppCompatActivity {
    private Context context;


    public DatabaseFunctions(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(this.context);
    }
    SQLiteOpenHelper databaseHelper;
    SQLiteDatabase db;


    //NON FUNZIONA
    public String getSubjectName(int subjectID){
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
}
