package com.waldo.notesbites;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class QuizActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    public static final String EXTRA_NUM_QUESTIONS = "numQuestions";
    public static final String EXTRA_QUIZ_ID = "quizID";
    public static final String EXTRA_MOD_ID = "moduleID";
    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    List<RadioButton> radioButtonList;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton rbSelected;
    private RadioButton rbCorrect;
    private Button buttonConfirmNext;
    private ColorStateList textColorDefaultRb;
    private int score;
    private boolean answered;
    private long backPressedTime;
    private String correctAnswer;
    private int questionCounter;
    private int numberOfQuestions;
    private int checkedRbID;


    QuizViewModel quizViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w("QuizActivity","on creaaaaaaaaaaaaaaaaaaaaaaate");


        super.onCreate(savedInstanceState);

        // set layout
        setContentView(R.layout.activity_quiz);

        // set support action
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));


        // recover the id of the quiz relative to the selected module
        Intent intent = getIntent();
        final int moduleID = intent.getIntExtra(QuizActivity.EXTRA_MOD_ID,0);
        final int quizID = intent.getIntExtra(QuizActivity.EXTRA_QUIZ_ID,0);
        final int numOfQuestions = intent.getIntExtra(QuizActivity.EXTRA_NUM_QUESTIONS,0);

        // find views
        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
        radioButtonList = new ArrayList<RadioButton>() {};
        radioButtonList.add(rb1);
        radioButtonList.add(rb2);
        radioButtonList.add(rb3);
        radioButtonList.add(rb4);

        buttonConfirmNext = findViewById(R.id.button_confirm_next);
        textColorDefaultRb = rb1.getTextColors();

        //reset colors of options
        resetColorOfOptions();

        // Get instance of view model, passing the quizID
        this.quizViewModel = new ViewModelProvider(this, new MyQuizViewModelFactory(this.getApplication(),moduleID,quizID,numOfQuestions)).get(QuizViewModel.class);




        // observe current question
        quizViewModel.getCurrentQuizQuestion().observe(this, new Observer<QuizQuestion>() {
            @Override
            public void onChanged(QuizQuestion currentQuizQuestion) {
                Log.w("QuizActivity","on changed di getCurrentQuizQuestion");
                // save locally the question counter and the number of questions
                questionCounter = quizViewModel.getQuestionCounter();
                numberOfQuestions = quizViewModel.getNumberOfQuestions();

                // load current question and options in the view
                showCurrentQuestion(currentQuizQuestion);

            }
        });




        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // triggered if the user is clicking "confirm"
                if (!answered) {
                    //the if is intered if at least one option is checked
                    if (getCheckedRadioButtonId()!=-1) {
                        rbSelected = findViewById(getCheckedRadioButtonId());
                        answered = true;
                        clearCheck();
                        updateScore();
                        showSolution();
                        updateButton();
                    } else {
                        Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }


                // triggered if the user is clicking "next" ( the program will observe achange in currentQuizQuestion and trigger the onChanged method
                Log.w("QuizActivity","triggered onclick, you just cliked \"next\"");
                clearCheck();
                quizViewModel.incrementQuestionCounter();
                quizViewModel.updatemCurrentQuizQuestion(quizViewModel.getQuestionCounter());

            }
        });

    }

    private int getCheckedRadioButtonId() {
        for (int i = 0; i < radioButtonList.size(); i++) {
            if(radioButtonList.get(i).isChecked()){
                return radioButtonList.get(i).getId();
            }
        }
        return -1;

    }


    private void showCurrentQuestion(QuizQuestion currentQuestion) {
        resetColorOfOptions();
        // to be sure... let's get again the reference of the question counter and the number of questions
        questionCounter = quizViewModel.getQuestionCounter();
        Log.w("QuizActivity:","questionCounter:"+questionCounter);
        numberOfQuestions = quizViewModel.getNumberOfQuestions();

        if(questionCounter <= numberOfQuestions) {
            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb1.setMovementMethod(new ScrollingMovementMethod());
            rb2.setText(currentQuestion.getOption2());
            rb2.setMovementMethod(new ScrollingMovementMethod());
            rb3.setText(currentQuestion.getOption3());
            rb3.setMovementMethod(new ScrollingMovementMethod());
            rb4.setText(currentQuestion.getOption4());
            rb4.setMovementMethod(new ScrollingMovementMethod());
            correctAnswer = currentQuestion.getCorrectOption();
            textViewQuestionCount.setText("Question " + questionCounter + "/" + numberOfQuestions);
            answered = false;
            buttonConfirmNext.setText("Confirm");

        }else{
            finishQuiz();
        }
    }







        private void resetColorOfOptions(){
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rb4.setTextColor(textColorDefaultRb);
        clearCheck();
    }

    private void clearCheck() {
        if(checkedRbID!=0){
            ((RadioButton)findViewById(checkedRbID)).setChecked(false);
        }

    }


    private void updateScore(){
        if(rbSelected.getText().toString().equals(correctAnswer)){
            score++;
            textViewScore.setText("Score: " + score);
        }
    }




    private void showSolution() {

        // case: correct
        if(rbSelected.getText().toString().equals(correctAnswer)){
            rbSelected.setTextColor(Color.GREEN);
            return;
        }
        // case: incorrect
        rbSelected.setTextColor(Color.RED);
        rbCorrect = findCorrectRb();
        rbCorrect.setTextColor(Color.GREEN);

    }

    private RadioButton findCorrectRb(){
        RadioButton temp;
        for (int i = 0; i < radioButtonList.size(); i++) {
            temp = findViewById(radioButtonList.get(i).getId());
            if(temp.getText().toString().equals(correctAnswer)){
                return temp;
            }
        }
        return null;
    }

    private void updateButton(){
        if (questionCounter < numberOfQuestions) {
            buttonConfirmNext.setText("Next");
        } else {
            buttonConfirmNext.setText("Finish");
        }
    }

    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
        Log.w("ehi","ehi");
    }

    public void checkCheck(View view) {
        clearCheck();
        RadioButton rb = (RadioButton)findViewById(view.getId());
        checkedRbID = rb.getId();
        rb.setChecked(true);
    }

    public void startCorrectModule(View view) {

        Intent intent = new Intent(QuizActivity.this, ModuleActivity.class);
        intent.putExtra(ModuleActivity.EXTRA_MODULEID,quizViewModel.getModuleID());
        startActivity(intent);
    }
    /*
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

     */
}