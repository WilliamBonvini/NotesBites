package com.waldo.notesbites.presentation.guesthomepage;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.waldo.notesbites.R;
import com.waldo.notesbites.data.model.Subject;
import com.waldo.notesbites.presentation.homepage.HomepageActivity;
import com.waldo.notesbites.presentation.selectsubjects.SelectSubjectsActivity;
import com.waldo.notesbites.presentation.subjectoverview.SubjectOverviewActivity;

public class GuestHomepageActivity extends AppCompatActivity {
    private ArrayList<String> subjectNamesList = new ArrayList<String>();
    private Cursor cursor;
    private SQLiteDatabase db;
    private String subjectClickedName;
    public int subjectClickedID;
    int RC_SIGN_IN = 0;
    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_homepage);

        signInButton = findViewById(R.id.sign_in_button);

        final RecyclerView recyclerView = findViewById(R.id.guest_homepage_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle("Guest Homepage");
//        setSupportActionBar(toolbar);

        //final SelectSubjectAdapter adapter = new SelectSubjectAdapter();
        final GuestHomepageAdapter adapter = new GuestHomepageAdapter();
        recyclerView.setAdapter(adapter);


        final GuestHomepageViewModel guestHomepageViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(GuestHomepageViewModel.class);
        guestHomepageViewModel.getAllSubjectsSelected().observe(this, new Observer<List<Subject>>() {
            @Override
            public void onChanged(List<Subject> subjects) {
                // update RecyclerView when data in the subjects data changes (the change could occur only to the column "selected")
                adapter.setSubjects(subjects);
                if(subjects.size() == 0){
                    startSelectSubjectsActivity2();
                }
            }
        });

        adapter.setOnItemClickListener(new GuestHomepageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Subject subject) {
                subjectClickedID = subject.getSubjectID();
                Intent intent = new Intent(GuestHomepageActivity.this, SubjectOverviewActivity.class);
                intent.putExtra(SubjectOverviewActivity.EXTRA_SUBJECTID, subjectClickedID);
                startActivity(intent);
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

    }

    private void startSelectSubjectsActivity2() {
        Intent intent = new Intent(GuestHomepageActivity.this, SelectSubjectsActivity.class);
        startActivity(intent);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            startActivity(new Intent(GuestHomepageActivity.this, HomepageActivity.class));
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google Sign In Error", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(GuestHomepageActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null) {
            startActivity(new Intent(GuestHomepageActivity.this, HomepageActivity.class));
        }
        super.onStart();
    }

    public void startSelectSubjectsActivity(View view) {
        Intent intent = new Intent(GuestHomepageActivity.this, SelectSubjectsActivity.class);
        startActivity(intent);
    }

    


}


