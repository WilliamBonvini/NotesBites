package com.waldo.notesbites;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
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

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;
    private ColorStateList textColorDefaultRb;
    private List<Question> questionList;
    private int questionCounter = 0;
    public int questionCountTotal;
    private QuizQuestion currentQuestion;
    private int score;
    private boolean answered;
    private long backPressedTime;
    private String correctAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));

        Intent intent = getIntent();
        final int quizID = intent.getIntExtra("QuizID",0);
        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);
        textColorDefaultRb = rb1.getTextColors();

        showNextQuestion(quizID);
        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion(quizID);
                }
            }
        });

    }

    private void showNextQuestion(int quizID) {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();
        QuizViewModel quizViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(QuizViewModel.class);
        quizViewModel.getQuestionsByQuizID(quizID).observe(this, new Observer<List<QuizQuestion>>() {
            @Override
            public void onChanged(List<QuizQuestion> quizQuestions) {
                questionCountTotal = quizQuestions.size();
                if(questionCounter < questionCountTotal) {
                    currentQuestion = quizQuestions.get(questionCounter);
                    textViewQuestion.setText(currentQuestion.getQuestion());
                    rb1.setText(currentQuestion.getOption1());
                    rb2.setText(currentQuestion.getOption2());
                    rb3.setText(currentQuestion.getOption3());
                    rb4.setText(currentQuestion.getOption4());
                    correctAnswer = currentQuestion.getCorrectOption();
                    questionCounter++;
                    textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
                    answered = false;
                    buttonConfirmNext.setText("Confirm");

                }else{
                    finishQuiz();
                }


            }
        });

    }

    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;
        if(answerNr == 1){
            if(currentQuestion.getOption1().equals(correctAnswer)){
                score++;
                textViewScore.setText("Score: " + score);
            }
        }else if(answerNr == 2){
            if(currentQuestion.getOption2().equals(correctAnswer)){
                score++;
                textViewScore.setText("Score: " + score);
            }
        }else if(answerNr == 3){
            if(currentQuestion.getOption3().equals(correctAnswer)){
                score++;
                textViewScore.setText("Score: " + score);
            }
        }else if(answerNr == 4){
            if(currentQuestion.getOption4().equals(correctAnswer)){
                score++;
                textViewScore.setText("Score: " + score);
            }
        }
        showSolution(answerNr);
    }

    private void showSolution(int answerNr) {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);
        switch (answerNr) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 1 is correct");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 2 is correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 3 is correct");
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 4 is correct");
                break;
        }
        if (questionCounter < questionCountTotal) {
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