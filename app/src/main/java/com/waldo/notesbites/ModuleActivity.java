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
  private int belongingSubjectID;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_module);

    // Get the moduleID and the subject name from the intent
    Intent intent = getIntent();

    moduleID = (Integer) intent.getExtras().get(EXTRA_MODULEID);

    moduleViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ModuleViewModel.class);
    moduleViewModel.updateLastOpenedDate(moduleID);  //TODO: è proprio il posto migliore in cui metterlo? ...
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
        toolbar.setTitle("All Modules");
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


        //set belonging subject, it will be needed to override the up buttton (back button)
        belongingSubjectID = module.getBelongingSubjectID();





        // todo:the following UI components are still present in the layout but probably we want to get rid of them
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

    //

    return (super.onOptionsItemSelected(item));
  }

  @Override
  public void onBackPressed()
  {
    super.onBackPressed();
    Log.w("moduleActivity","entered unBackPressed");
    Intent goToSubjectOverview = new Intent(ModuleActivity.this, SubjectOverviewActivity.class);
    goToSubjectOverview.putExtra(SubjectOverviewActivity.EXTRA_SUBJECTID,belongingSubjectID);
    Log.w("moduleactivity","gonna start activity?");
    startActivity(goToSubjectOverview);
    Log.w("moduleActivity","it started the activity");


    finish();

  }
}

