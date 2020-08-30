package com.waldo.notesbites;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

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

    public LiveData<Integer> getQuizIDByModuleID(int moduleID) {
        return quizDao.getQuizID_liveData_byModuleID(moduleID);
    }

    public LiveData<List<QuizQuestion>> getQuestionsByQuizID(int quizID) {
        return quizQuestionDao.getQuestionsByQuizID(quizID);
    }
}
