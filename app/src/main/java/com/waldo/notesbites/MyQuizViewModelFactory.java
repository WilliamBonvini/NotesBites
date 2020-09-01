package com.waldo.notesbites;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MyQuizViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private int quizID;


    public MyQuizViewModelFactory(Application application, int quizID) {
        mApplication = application;
        this.quizID = quizID;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new QuizViewModel(mApplication, quizID);
    }
}