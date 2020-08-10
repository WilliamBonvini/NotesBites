package com.waldo.notesbites;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class NotesActivity extends Activity {
    public static final String MODULE_ID  = "MODULE_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        Intent intent = getIntent();
        int moduleID = (int)intent.getExtras().get(MODULE_ID);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("MODULE",new String[]{"READABLE_CONTENT"},"_id = ?",new String[]{Integer.toString(moduleID)},null,null,null);
        if(cursor.moveToFirst()){
            String readableContent = cursor.getString(0);
            TextView notesTextTextView = findViewById(R.id.notes_text);
            notesTextTextView.setText(readableContent);
        }
        cursor.close();
        db.close();
    }

}
