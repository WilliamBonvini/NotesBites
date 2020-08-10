package com.waldo.notesbites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class SubjectOverviewActivity extends AppCompatActivity {
  public static final String EXTRA_SUBJECTID = "subjectID";
  private SQLiteDatabase db;
  private Cursor cursor2;
  String subjectTitleText;
  String subjectDescriptionText;
  int subjectPhotoID;


  @Override
  protected void onCreate(Bundle savedInstanceState) {

    // default stuff
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_subject_overview);
    // get reference to ListView
    ListView listOfModulesView = (ListView) findViewById(R.id.list_of_modules);

    // retrieve subject id
    Intent oldIntent = getIntent();
    final int subjectID = (int) oldIntent.getExtras().get(EXTRA_SUBJECTID);
    //retrieve subject overview info from db
    SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
    try {
      db = databaseHelper.getReadableDatabase();
      Cursor cursor1 = db.query(
              "SUBJECT",
              new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
              "_id=?",
              new String[]{Integer.toString(subjectID)},
              null, null, null);
      if (cursor1.moveToFirst()) {
        subjectTitleText = cursor1.getString(0);
        subjectDescriptionText = cursor1.getString(1);
        subjectPhotoID = cursor1.getInt(2);
        // get reference to first three Views
        TextView subjectTitleView = (TextView) findViewById(R.id.subject_title);
        TextView subjectDescriptionView = (TextView) findViewById(R.id.subject_description);
        ImageView subjectImageView = (ImageView) findViewById(R.id.subject_image);

        // populate first three Views
        subjectTitleView.setText(subjectTitleText);
        subjectDescriptionView.setText(subjectDescriptionText);
        subjectImageView.setImageResource(subjectPhotoID);
      }
      cursor1.close();


      cursor2 = db.query("MODULE",
              new String[]{"_id", "NAME"},
              "BELONGING_SUBJECT=?",
              new String[]{Integer.toString(subjectID)},
              null, null, null);

      // the following code populates the list view with data from the names of the modules
      SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
              android.R.layout.simple_list_item_1,
              cursor2,
              new String[]{"NAME"},
              new int[]{android.R.id.text1},
              0);

      listOfModulesView.setAdapter(listAdapter);
    }catch(SQLiteException e){
      Toast toast = Toast.makeText(this,
              "Database Unavailable",
              Toast.LENGTH_SHORT);
      toast.show();
    }

    // the following code sends to ModuleActivity the id of the module tha has been clicked:
    // Create a listener to listen for clicks in the list view
    AdapterView.OnItemClickListener itemClickListener =
            new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> listOfModulesView,
                                      View view,
                                      int position,
                                      long id) {
                // Pass the id of the module the user clicked to ModuleActivity
                Intent newIntent = new Intent(SubjectOverviewActivity.this, ModuleActivity.class);

                newIntent.putExtra(ModuleActivity.EXTRA_MODULEID, (int)id);
                newIntent.putExtra(ModuleActivity.EXTRA_SUBJECT_NAME,subjectTitleText);
                startActivity(newIntent);

              }
            };
    // Assign the listener to the list view
    listOfModulesView.setOnItemClickListener(itemClickListener);


  }

  /**
   * method is overridden because the user should be able to scroll the listview as long as he wants, so
   * we close cursor2 at the destruction of the activity, and not within the onCreate method.
   */
  @Override
  public void onDestroy(){
    super.onDestroy();
    cursor2.close();
    db.close();
  }

}