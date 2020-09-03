package com.waldo.notesbites;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ModuleActivity extends AppCompatActivity {

  public static final String EXTRA_MODULEID = "moduleID";
  public static final String EXTRA_SUBJECT_NAME = "subjectName";
  private ModuleViewModel moduleViewModel;
  private int moduleID;
  private String moduleTitle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_module);

    // Get the moduleID and the subject name from the intent
    Intent intent = getIntent();

    moduleID = (Integer) intent.getExtras().get(EXTRA_MODULEID);
    Log.w("ripeto, moduleID vale: ", String.valueOf(moduleID));

    moduleViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ModuleViewModel.class);
    moduleViewModel.updateLastOpenedDate(moduleID);  //TODO: è proprio il posto migliore in cui metterlo? ...
    Log.w("moduleActivity","called OnCreate");
    moduleViewModel.getModuleByModuleID(moduleID).observe(this, new Observer<Module>() {
      @Override
      public void onChanged(final Module module) {
        // find UI components!
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView moduleNameTextView = findViewById(R.id.module_title);
        //TextView moduleDescriptionTextView = findViewById(R.id.module_description);
        ImageButton videoContentImageButton = findViewById(R.id.videoContentImageButton);

        // populate description!
        //moduleDescriptionTextView.setText(module.getDescription());

        // set toolbar and add up button!
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // set module name!
        moduleNameTextView.setText(module.getName());

        // set Youtube url!
        videoContentImageButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(module.getVideoURL())));
          }
        });

        // todo:the following UI component are still present in the layout but probably we want to get rid of them
        // in case we don't, here is the code to populate them:
        // TextView belongingSubjectTextView = findViewById(R.id.belongingSubject);
        // TextView moduleTitleTextView = findViewById(R.id.module_title);
        // belongingSubjectTextView.setText(module.getBelongingSubject());
        // moduleTitleTextView.setText(module.getName());

      }
    });





  }

  public void startNotesActivity (View view){
    Intent intent = new Intent(ModuleActivity.this, NotesActivity.class);
    intent.putExtra(NotesActivity.MODULE_ID, moduleID);
    startActivity(intent);
  }


  public void startStartingQuizActivity (View view){
    Intent intent = new Intent(ModuleActivity.this, StartingQuizActivity.class);
    intent.putExtra(StartingQuizActivity.EXTRA_MODULE_ID, moduleID);
    startActivity(intent);
  }


  @Override
  public boolean onOptionsItemSelected (MenuItem item){
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
    }

    return (super.onOptionsItemSelected(item));
  }

}


    /*
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
*/