package com.waldo.notesbites;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import javax.inject.Inject;


public class QuizViewModel extends AndroidViewModel{
    private final QuizRepository quizRepository;
    private int numberOfQuestions;

    private MediatorLiveData<QuizQuestion> mCurrentQuizQuestion;
    private LiveData<QuizQuestion> oldQuizQuestion;
    private LiveData<QuizQuestion> currentQuizQuestion;


    private int quizID;
    private int questionCounter;
    private int oldQuestionCounter;


    // @Inject tells Dagger how to create instances of LoginViewModel
    @Inject
    public QuizViewModel(@NonNull Application application, int quizID) {
        super(application);

        this.quizRepository = new QuizRepository(application);

        this.quizID = quizID;

        this.oldQuestionCounter = 0;


        // save in the view model the number of questions of this particular quiz
        numberOfQuestions = 4; //TODO: YUK
        Log.w("QuizViewModel", "number of questions = " + String.valueOf(numberOfQuestions));



        mCurrentQuizQuestion = new MediatorLiveData<QuizQuestion>(){};

        oldQuizQuestion = new LiveData<QuizQuestion>(){};
        currentQuizQuestion = new LiveData<QuizQuestion>() {};

        // initialize the view model to display the first question
        questionCounter = 1;
        updatemCurrentQuizQuestion(questionCounter); /// SBEEEEEEEEM AWESOME

    }


    public void updatemCurrentQuizQuestion(int questionCounter){

        if(questionCounter != oldQuestionCounter){
            Log.w("s","entered if");
            mCurrentQuizQuestion.removeSource(oldQuizQuestion);
        }


        currentQuizQuestion = quizRepository.getCurrentQuizQuestionByQuizIDAndPriority(quizID,questionCounter);
        mCurrentQuizQuestion.addSource(currentQuizQuestion, new Observer<QuizQuestion>() {
            @Override
            public void onChanged(QuizQuestion quizQuestion) {

                mCurrentQuizQuestion.setValue(quizQuestion);
            }
        });

        Log.w("s","gonna update oldStuff");
        oldQuizQuestion = currentQuizQuestion;
        oldQuestionCounter = questionCounter;

    }






    public int getQuestionCounter() {
        return questionCounter;
    }

    public void incrementQuestionCounter() {
        this.questionCounter++;
    }

    public LiveData<QuizQuestion> setCurrentQuizQuestion() {
        if (currentQuizQuestion == null) {
            currentQuizQuestion = new MutableLiveData<QuizQuestion>();

        }
        loadQuizQuestionAsynchronously();
        return currentQuizQuestion;
    }

    public LiveData<QuizQuestion> getCurrentQuizQuestion() {
        return mCurrentQuizQuestion;
    }




    private void loadQuizQuestionAsynchronously() {
        //currentQuizQuestion.setValue(quizRepository.getQuizQuestionsListByQuizID(quizID).get(questionCounter));
        quizRepository.getQuizQuestionsListByQuizID(quizID).get(questionCounter);

    }







    public LiveData<List<QuizQuestion>> getQuestionsByQuizID(int quizID) {

        return quizRepository.getQuestionsByQuizID(quizID);
    }

    public int getNumberOfQuestions(){
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions){
        this.numberOfQuestions = numberOfQuestions;
    }


}



