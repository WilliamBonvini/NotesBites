package com.waldo.notesbites;

import android.app.Application;

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
         LiveData<String> result = quizRepository.getModuleNameByID(moduleID);
         return result;
    }
}
