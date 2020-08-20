package com.waldo.notesbites;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class QuizResultsActivity extends AppCompatActivity {
    public static String EXTRA_SUBJECTID = "subjectID";
    private SQLiteDatabase db;
    private Cursor cursor;
    String subjectTitleText;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        TextView textView = findViewById(R.id.text_view);

        Intent oldIntent = getIntent();
        final int subjectID = (int) oldIntent.getExtras().get(EXTRA_SUBJECTID);
        String subjectTitleText = null;
        //Uso la funzione in DatabaseFunctions
        DatabaseFunctions dbf = new DatabaseFunctions(this);
        subjectTitleText = dbf.getSubjectName(subjectID);
        textView.setText(subjectTitleText + "  Quiz Results");

//        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
//        try {
//            db = databaseHelper.getReadableDatabase();
//            Cursor cursor1 = db.query(
//                    "SUBJECT",
//                    new String[]{"NAME"},
//                    "_id=?",
//                    new String[]{Integer.toString(subjectID)},    //perchè questo?
//                    null, null, null);
//
//            if (cursor1.moveToFirst()) {
//                subjectTitleText = cursor1.getString(0);
//                textView.setText(subjectTitleText + "  Quiz Results");
//
//            }
//
//            cursor1.close();
//
//        } catch (SQLiteException e) {
//            Toast toast = Toast.makeText(this,
//                    "Database Unavailable",
//                    Toast.LENGTH_SHORT);
//            toast.show();
//        }

    }
}

