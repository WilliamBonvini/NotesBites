package com.waldo.notesbites;

import android.app.Application;

import androidx.lifecycle.LiveData;

public class QuizRepository {
    private QuizDao quizDao;
    private QuizQuestionDao quizQuestionDao;

    public QuizRepository (Application application){
        NBDatabase database = NBDatabase.getInstance(application);
        quizDao = database.quizDao();
        quizQuestionDao = database.quizQuestionDao();
    }

    public LiveData<String> getModuleNameByID(int moduleID){
        return quizQuestionDao.getModuleNameByModuleID(moduleID);
    }
}
