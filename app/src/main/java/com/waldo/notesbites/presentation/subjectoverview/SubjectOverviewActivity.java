package com.waldo.notesbites.presentation.subjectoverview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.waldo.notesbites.R;
import com.waldo.notesbites.data.model.Module;
import com.waldo.notesbites.presentation.guesthomepage.GuestHomepageActivity;
import com.waldo.notesbites.presentation.homepage.HomepageActivity;
import com.waldo.notesbites.presentation.module.ModuleActivity;

import java.util.List;
import java.util.Objects;


public class SubjectOverviewActivity extends AppCompatActivity {
  public static final String EXTRA_SUBJECTID = "subject ID";

  ConstraintLayout expandableView;
  Button arrowBtn;
  CardView cardView;
  ImageView subjectImageView;
  RecyclerView modulesRecyclerView;





  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    int subjectID = Objects.requireNonNull(intent.getExtras()).getInt(SubjectOverviewActivity.EXTRA_SUBJECTID);

    setSupportActionBar((Toolbar)findViewById(R.id.toolbar));




    setContentView(R.layout.activity_subject_overview);

    final RecyclerView modulesRecyclerView = findViewById(R.id.subject_overview_recycler_view);
    modulesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    modulesRecyclerView.setHasFixedSize(true);

    expandableView = findViewById(R.id.expandableView);
    arrowBtn = findViewById(R.id.arrowBtn);
    cardView = findViewById(R.id.cardView);
    // make sure the expandable view is gone each time the activity is recreated  //todo: when user goes in landscape mode we'd like the visibility = visible if he had it like that in portrait mode
    expandableView.setVisibility(View.GONE);

    arrowBtn.setOnClickListener(new View.OnClickListener(){

      @Override
      public void onClick(View v) {
        if(expandableView.getVisibility()==View.GONE){
          TransitionManager.beginDelayedTransition(cardView, new AutoTransition());

          // set overview visible
          expandableView.setVisibility(View.VISIBLE);


          // set recycler view gone
          modulesRecyclerView.setVisibility(View.GONE);






          //set arrow down
          arrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
        }else{
          TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
          // set view gone
          expandableView.setVisibility(View.GONE);
          // set recycler view gone
          modulesRecyclerView.setVisibility(View.VISIBLE);
          // set arrow up
          arrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);





        }
      }
    });




    final SubjectOverviewAdapter adapter = new SubjectOverviewAdapter();
    modulesRecyclerView.setAdapter(adapter);

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


  /**
   * Override method in such a way that when the user goes in landscape mode the subject image is not displayed
   * @param newConfig
   */
  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    subjectImageView = findViewById(R.id.subject_overview_image_view);

    // Checks the orientation of the screen
    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
      subjectImageView.setVisibility(View.GONE);
      Log.w("sf","LANDSCAPE");


    } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
      subjectImageView.setVisibility(View.VISIBLE);
      Log.w("sf","PORTRAIT");

    }
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

  public void onClickGoToHomepage(View view) {
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



    /*
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
    }
     */

}




