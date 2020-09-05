package com.waldo.notesbites.presentation.homepage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.waldo.notesbites.presentation.utils.OnSwipeTouchListener;
import com.waldo.notesbites.R;
import com.waldo.notesbites.data.model.Module;
import com.waldo.notesbites.data.model.Subject;
import com.waldo.notesbites.presentation.module.ModuleActivity;
import com.waldo.notesbites.presentation.selectsubjects.SelectSubjectsActivity;
import com.waldo.notesbites.presentation.subjectoverview.SubjectOverviewActivity;
import com.waldo.notesbites.presentation.guesthomepage.GuestHomepageActivity;

import java.util.List;

public class HomepageActivity extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;


    public static String PERSON_NAME = "PERSON_NAME";
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        //setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

//        Intent intent = getIntent();
//        PERSON_NAME = (String) intent.getExtras().get(PERSON_NAME);
        final LinearLayout linearLayout = findViewById(R.id.linear_layout_general);
        ImageView logo = findViewById(R.id.image_logo);
        logo.setOnTouchListener(new OnSwipeTouchListener(this) {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onSwipeLeft() {
                linearLayout.setBackground(getDrawable(R.drawable.background_gradient2));
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onSwipeRight() {
                linearLayout.setBackground(getDrawable(R.drawable.background_gradient));
            }
        });

        final TextView mySubjecy = findViewById(R.id.my_subjects);
        final LinearLayout linearLayout_middle = findViewById(R.id.linear_layout_middle);
        LinearLayout linearLayout_bottom = findViewById(R.id.linear_layout_bottom);

//        mySubjecy.setOnTouchListener(new OnSwipeTouchListener(this) {
//            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//            @Override
//            public void onSwipeLeft() {
//                LinearLayout.LayoutParams lay = (LinearLayout.LayoutParams) mySubjecy.getLayoutParams();
//                lay.weight = 1f;
//                mySubjecy.setLayoutParams(lay);
//            }
//
//            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//            @Override
//            public void onSwipeRight() {
//                LinearLayout.LayoutParams lay = (LinearLayout.LayoutParams) mySubjecy.getLayoutParams();
//                lay.weight = 2f;
//                mySubjecy.setLayoutParams(lay);
//            }
//        });



        ImageButton sign_out = findViewById(R.id.btn_logOut);
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        // create recycler view and adapter for the subjects
        final RecyclerView recyclerView = findViewById(R.id.homepage_subjects_recycler_view);
        //make recycler view subject take all screen
        final LinearLayout.LayoutParams lay = (LinearLayout.LayoutParams) recyclerView.getLayoutParams();
        lay.weight = 4f;
        recyclerView.setLayoutParams(lay);
        //recyclerView.setBackgroundColor(getResources().getColor(R.color.nb_white));

        mySubjecy.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeRight() {
                lay.weight = 4f;
                recyclerView.setLayoutParams(lay);
                TextView recentActivitiesTextView;
                recentActivitiesTextView = findViewById(R.id.recent_activities);
                recentActivitiesTextView.setVisibility(View.INVISIBLE);
            }

        });



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final HomepageSubjectsAdapter adapter_subjects = new HomepageSubjectsAdapter();
        recyclerView.setAdapter(adapter_subjects);


        // create recycler view and adapter for the recent modules
        final RecyclerView recyclerView_recent_modules = findViewById(R.id.homepage_recent_module_recycler_view);
        recyclerView_recent_modules.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_recent_modules.setHasFixedSize(true);
        final HomepageRecentModulesAdapter adapter_recent_module = new HomepageRecentModulesAdapter();
        recyclerView_recent_modules.setAdapter(adapter_recent_module);

        // reference goToSubjectButton
        final Button goToSubjectsModulesButton = findViewById(R.id.homepage_go_to_subjects_modules_button);

        // instantiate view model
        final HomepageViewModel homepageViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(HomepageViewModel.class);

        //////////////////////////// OBSERVER 1 //////////////////////////////////////
        homepageViewModel.getAllSubjectsSelected().observe(this, new Observer<List<Subject>>() {
            @Override
            public void onChanged(List<Subject> subjects) {
                // update RecyclerView when data in the subjects data changes (the change could occur only to the column "selected")
                adapter_subjects.setSubjects(subjects);
            }

        });


        ////////////////////////// OBSERVER 2 /////////////////////////////////////////
        homepageViewModel.getRecentModulesToBeDisplayed().observe(this, new Observer<List<Module>>() {
            @Override
            public void onChanged(List<Module> modules) {
                if(modules.size()==0 && homepageViewModel.isSubjectJustPressed()){
                    Toast.makeText(HomepageActivity.this, "No Recent Activity", Toast.LENGTH_SHORT).show();
                    homepageViewModel.setSubjectJustPressed(false);
                }

                // update recent modules to be displayed
                adapter_recent_module.setModules(modules);

                // set go to subject button visible as soon as someone triggers a change in the recent modules to be displayed
                goToSubjectsModulesButton.setVisibility(View.VISIBLE);

            }
        });



        adapter_subjects.setOnItemClickListener(new HomepageSubjectsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final Subject subject) {
                //homepageViewModel.setRecentModulesBySubjectID(subject.getSubjectID());

                //half for subjects and half for modules
                if(lay.weight != 2f){
                    Animation anim = new ScaleAnimation(
                            2f, 1f, // Start and end values for the X axis scaling
                            1f, 1f); // Start and end values for the Y axis scaling
                    anim.setFillAfter(true); // Needed to keep the result of the animation
                    anim.setDuration(300);
                    recyclerView.startAnimation(anim);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            goToSubjectsModulesButton.setId(subject.getSubjectID());                    //  masterpiece
                            homepageViewModel.updateRecentModulesMediator(subject.getSubjectID());
                            homepageViewModel.setSubjectJustPressed(true);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }

                    });

                    TextView recentActivitiesTextView;
                    recentActivitiesTextView = findViewById(R.id.recent_activities);
                    recentActivitiesTextView.setVisibility(View.VISIBLE);
                }
                else{
                    goToSubjectsModulesButton.setId(subject.getSubjectID());                    //  masterpiece
                    homepageViewModel.updateRecentModulesMediator(subject.getSubjectID());
                    homepageViewModel.setSubjectJustPressed(true);
                }

                lay.weight = 2f;
                recyclerView.setLayoutParams(lay);
//                LinearLayout subjectLayout = findViewById(R.id.guest_homepage_linear_layout);
//                subjectLayout.setBackgroundResource(R.color.nb_color);

            }
        });


        adapter_recent_module.setOnItemClickListener(new HomepageRecentModulesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Module module) {
                Intent intent = new Intent(HomepageActivity.this, ModuleActivity.class);
                intent.putExtra(ModuleActivity.EXTRA_MODULEID,module.getModuleID());
                startActivity(intent);
            }
        });

    }


    public void startSelectSubjectsActivity(View view) {
        Intent intent = new Intent(HomepageActivity.this, SelectSubjectsActivity.class);
        startActivity(intent);
    }




    public void startSubjectOverviewActivity(View view) {
        Intent intent = new Intent(HomepageActivity.this, SubjectOverviewActivity.class);
        Log.w("subject id vale:",String.valueOf(view.getId()));
        intent.putExtra(SubjectOverviewActivity.EXTRA_SUBJECTID,view.getId());
        startActivity(intent);
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(HomepageActivity.this,"Successfully signed out",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(HomepageActivity.this, GuestHomepageActivity.class));
                        finish();
                    }
                });
    }
}