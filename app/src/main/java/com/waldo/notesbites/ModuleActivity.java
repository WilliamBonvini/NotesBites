package com.waldo.notesbites;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ModuleActivity extends AppCompatActivity {

  public static final String EXTRA_MODULEID = "moduleID";
  public static final String EXTRA_SUBJECT_NAME = "subjectName";
  private int moduleID;
  private String moduleTitle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_module);






    // Get the moduleID and the subject name from the intent
    Intent intent = getIntent();

    moduleID = (Integer) intent.getExtras().get(EXTRA_MODULEID);
    String belongingSubject = (String) intent.getExtras().get(EXTRA_SUBJECT_NAME);
    SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
    try {
      SQLiteDatabase db = databaseHelper.getReadableDatabase();
      // code to read the data from the database
      Cursor cursor = db.query("MODULE",new String[]{"NAME","DESCRIPTION"},"_id=?",new String[]{Integer.toString(moduleID)},null,null,null);
      if(cursor.moveToFirst()){
        // get the data from the only tuple you have retrieved
        moduleTitle = cursor.getString(0);
        String descriptionText = cursor.getString(1);

        // populate the belonging subject text view
        //TextView belongingSubjectTextView = (TextView) findViewById(R.id.belongingSubject);
        //belongingSubjectTextView.setText(belongingSubject);

        // set toolbar and add up button
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(belongingSubject + " - " + moduleTitle);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // populate the module title
        //TextView moduleTitleTextView = (TextView)findViewById(R.id.module_title);
        //moduleTitleTextView.setText(moduleTitle);

        // populate the module description
        TextView moduleDescriptionTextView = (TextView) findViewById(R.id.module_description);
        moduleDescriptionTextView.setText(descriptionText);
      }
      cursor.close();
      db.close();
    }catch(SQLiteException e){
      Toast.makeText(this, "Database Unavailable", Toast.LENGTH_SHORT).show();
    }
  }

  public void startNotesActivity(View view){
    Intent intent = new Intent(ModuleActivity.this, NotesActivity.class);
    intent.putExtra(NotesActivity.MODULE_ID,moduleID);
    startActivity(intent);
  }

  public void startVideoContentActivity(View view) {
    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=jNQXAC9IVRw")));
  }

  public void startQuizActivity(View view){
    Intent intent = new Intent(ModuleActivity.this, StartingScreenActivity.class);
    startActivity(intent);
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
    }

    return(super.onOptionsItemSelected(item));
  }


}