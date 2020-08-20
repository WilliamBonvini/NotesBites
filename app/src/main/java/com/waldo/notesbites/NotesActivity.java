package com.waldo.notesbites;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.mukesh.MarkdownView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NotesActivity extends AppCompatActivity {
    public static final String MODULE_ID  = "MODULE_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));

        int subjectID = -1;

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

        DatabaseFunctions dbf = new DatabaseFunctions(this);
        subjectID = dbf.getSubjectID_fromModuleID(moduleID);
//        Cursor cursor1 = db.query("MODULE", null, "_id = ?",new String[]{Integer.toString(moduleID)}, null, null, null, null);
//        if(cursor1.moveToFirst()){
//            subjectID = cursor1.getInt(4);
//        }
//        cursor1.close();
        db.close();
        String subjectName = dbf.getSubjectName_fromID(subjectID);

        MarkdownView markdownView = (MarkdownView) findViewById(R.id.markdown_view);
        //markdownView.setMarkDownText("# Hello World\nThis is a simple markdown"); //Displays markdown text
        //TODO: ora cambia il markdown in base alla materia, deve cambiare in base al modulo della materia; cambiare prima il db
        if(subjectName.equals("AI")) {
            markdownView.loadMarkdownFromAssets("1_Introduction.md"); //Loads the markdown file from the assets folder
        }
        if(subjectName.equals("IOT")) {
            //ci sono due moduli in AI quindi è il terzo
            if(moduleID == 3){
            markdownView.loadMarkdownFromAssets("IOT/IOT_1.md"); //Loads the markdown file from the assets folder
            }
            if(moduleID == 4){
                markdownView.loadMarkdownFromAssets("IOT/IOT_2.md");
            }
            //markdownView.loadMarkdownFromAssets("IOT/IOT_1.md");
        }

        //File markdownFile=new File("filePath");
        //markdownView.loadMarkdownFromFile(markdownFile); //Loads the markdown file.



    }

}
