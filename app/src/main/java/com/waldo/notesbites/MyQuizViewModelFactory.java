package com.waldo.notesbites;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MyQuizViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private int moduleID;
    private int quizID;
    private int numOfQuestions;


    public MyQuizViewModelFactory(Application application,int moduleID, int quizID,int numOfQuestions) {
        mApplication = application;
        this.moduleID = moduleID;
        this.quizID = quizID;
        this.numOfQuestions = numOfQuestions;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new QuizViewModel(mApplication, moduleID,quizID,numOfQuestions);
    }
}