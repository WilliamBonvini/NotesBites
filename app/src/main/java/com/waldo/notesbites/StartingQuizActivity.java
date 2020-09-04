package com.waldo.notesbites;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class StartingQuizActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_MODULE_ID = "moduleID";
    private TextView recordScoreTextView;
    private int moduleID;
    private int highestScore;
    StartingQuizViewModel startingQuizViewModel;
    Intent goToQuizIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w("StartingQuizActivity","onCreaaaaaaaaaaaaaaate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_quiz);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        Intent intent = getIntent();
        moduleID = intent.getIntExtra(EXTRA_MODULE_ID,0);


        startingQuizViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(StartingQuizViewModel.class);
        startingQuizViewModel.getModuleNameByModuleID(moduleID).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String moduleName) {
                TextView textView = findViewById(R.id.text_module_name);
                textView.setText(moduleName);
            }
        });

        recordScoreTextView = findViewById(R.id.text_view_highscore);
        loadHighscore();


        StartingQuizViewModel startingQuizViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(StartingQuizViewModel.class);

        goToQuizIntent = new Intent(StartingQuizActivity.this, QuizActivity.class);
        /**
         * the following observer gets called even when the user does a better score in the quiz and clicks on "finish"
         * this is because the observer was instantiated back when the user clicked on "start quiz"
         * and it seems it stayed alive all this time.
         */
        startingQuizViewModel.getQuizIDAndNumberOfQuestionsByModuleID(moduleID).observe(this, new Observer<QuizIDAndQuizQuestionCountTuple>() {

            @Override
            public void onChanged(QuizIDAndQuizQuestionCountTuple data) {
                goToQuizIntent.putExtra(QuizActivity.EXTRA_MOD_ID,moduleID);
                goToQuizIntent.putExtra(QuizActivity.EXTRA_QUIZ_ID, data.getQuizID());
                goToQuizIntent.putExtra(QuizActivity.EXTRA_NUM_QUESTIONS, data.getNumberOfQuestions());

            }
        });

    }


    public void startQuizActivity(View view) {
        startActivityForResult(goToQuizIntent,REQUEST_CODE_QUIZ);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_QUIZ && resultCode == RESULT_OK) {
            int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 1000);
            if (score > highestScore) {
                updateHighscore(score);
            }
        }
    }
    private void loadHighscore() {
        startingQuizViewModel.getCorrectQuestionsByModuleID(moduleID).observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer correctQuestions) {
                highestScore = correctQuestions;
                Log.w("StartingQuizActivity","entered loadHighScore");
                recordScoreTextView.setText("Highest Score: " + correctQuestions);

            }
        });
    }
    private void updateHighscore(int highscoreNew) {
        startingQuizViewModel.updateCorrectQuestionsByModuleID(moduleID,highscoreNew);
    }

    public void gotToModuleActivity(View view) {
        Intent intent = new Intent(StartingQuizActivity.this,ModuleActivity.class);
        intent.putExtra(ModuleActivity.EXTRA_MODULEID,moduleID);
        startActivity(intent);
    }
}
