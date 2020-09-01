package com.waldo.notesbites;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


public class StartingQuizViewModel extends AndroidViewModel {
    private QuizRepository quizRepository;

    public StartingQuizViewModel(@NonNull Application application) {
        super(application);
        quizRepository = new QuizRepository(application);
    }
    public LiveData<String> getModuleNameByModuleID(int moduleID){
         return quizRepository.getModuleNameByID(moduleID);
    }

    public LiveData<QuizIDAndQuizQuestionCountTuple> getQuizIDAndNumberOfQuestionsByModuleID(int moduleID) {
        return quizRepository.getQuizIDAndNumberOfQuestionsByModuleID(moduleID);
    }
}
