package com.waldo.notesbites;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.List;
import java.util.Objects;


public class SubjectOverviewActivity extends AppCompatActivity {
  public static final String EXTRA_SUBJECTID = "subject ID";

  ConstraintLayout expandableView;
  Button arrowBtn;
  CardView cardView;





  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    int subjectID = Objects.requireNonNull(intent.getExtras()).getInt(SubjectOverviewActivity.EXTRA_SUBJECTID);

    setSupportActionBar((Toolbar)findViewById(R.id.toolbar));




    setContentView(R.layout.activity_subject_overview);

    final RecyclerView recyclerView = findViewById(R.id.subject_overview_recycler_view);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setHasFixedSize(true);

    expandableView = findViewById(R.id.expandableView);
    arrowBtn = findViewById(R.id.arrowBtn);
    cardView = findViewById(R.id.cardView);
    arrowBtn.setOnClickListener(new View.OnClickListener(){

      @Override
      public void onClick(View v) {
        if(expandableView.getVisibility()==View.GONE){
          TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
          expandableView.setVisibility(View.VISIBLE);
          arrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
        }else{
          TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
          expandableView.setVisibility(View.GONE);
          arrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
        }
      }
    });



    final SubjectOverviewAdapter adapter = new SubjectOverviewAdapter();
    recyclerView.setAdapter(adapter);

    final SubjectOverviewViewModel subjectOverviewViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(SubjectOverviewViewModel.class);


    subjectOverviewViewModel.getSubjectNameFromID(subjectID).observe(this, new Observer<String>() {
      @Override
      public void onChanged(String subjectName) {
        TextView title = findViewById(R.id.subject_title);
        title.setText(subjectName);


      }
    });


    subjectOverviewViewModel.getImageIDFromSubjectID(subjectID).observe(this, new Observer<Integer>() {
      @Override
      public void onChanged(Integer imageID) {
        ImageView imageView = findViewById(R.id.subject_overview_image_view);
        imageView.setImageResource(imageID);
      }
    });


    subjectOverviewViewModel.getOverviewFromSubjectID(subjectID).observe(this, new Observer<String>() {
      @Override
      public void onChanged(String overview) {
        TextView overviewTextView = findViewById(R.id.overview_content);
        overviewTextView.setText(overview);
      }
    });




    subjectOverviewViewModel.getModulesFromSubject(subjectID).observe(this, new Observer<List<Module>>() {
      @Override
      public void onChanged(List<Module> modules) {
        // update RecyclerView when data in the subjects data changes (the change could occur only to the column "selected")
        adapter.setModules(modules);
      }
    });








  }


  public void onClickStartModuleActivity(View view) {
    Intent intent = new Intent(SubjectOverviewActivity.this, ModuleActivity.class);
    intent.putExtra(ModuleActivity.EXTRA_MODULEID,view.getId());
    startActivity(intent);
  }






/*

  @Override
  public void onBackPressed() {
    // Check for existing Google Sign In account, if the user is already signed in
    // the GoogleSignInAccount will be non-null.
    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    Intent intent;
    if (account == null) {
      intent = new Intent(SubjectOverviewActivity.this, GuestHomepageActivity.class);
    }else {
      intent = new Intent(SubjectOverviewActivity.this, HomepageActivity.class);
    }
    super.onBackPressed();
    startActivity(intent);
  }

*/
  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event)
  {
    //replaces the default 'Back' button action
    if(keyCode==KeyEvent.KEYCODE_BACK)   {
      GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
      Intent intent;
      if (account == null) {
        intent = new Intent(SubjectOverviewActivity.this, GuestHomepageActivity.class);
      }else {
        intent = new Intent(SubjectOverviewActivity.this, HomepageActivity.class);
      }
      startActivity(intent);
      finish();
    }
    return true;
  }

    /*
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
    }
     */

}






/*
    // default stuff
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_subject_overview);
    setSupportActionBar((Toolbar)findViewById(R.id.toolbar));

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
              new String[]{Integer.toString(subjectID)},    //perchè questo?
              null, null, null);
      if (cursor1.moveToFirst()) {
        subjectTitleText = cursor1.getString(0);
        subjectDescriptionText = cursor1.getString(1);
        subjectPhotoID = cursor1.getInt(2);

        // populate toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(subjectTitleText);
        setSupportActionBar(toolbar);
        // Add up button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // get reference to first three Views
        TextView subjectDescriptionView = (TextView) findViewById(R.id.subject_description);
        ImageView subjectImageView = (ImageView) findViewById(R.id.subject_image);

        // populate first three Views
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

                newIntent.putExtra(ModuleActivity.EXTRA_MODULEID, (int)id);     //??
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

  @Override
  public void onDestroy(){
    super.onDestroy();
    cursor2.close();
    db.close();
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
*/
