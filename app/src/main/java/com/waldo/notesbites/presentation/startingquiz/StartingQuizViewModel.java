package com.waldo.notesbites.presentation.startingquiz;

import android.app.Application;

import com.waldo.notesbites.data.utils.QuizIDAndQuizQuestionCountTuple;
import com.waldo.notesbites.data.repository.QuizRepository;

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


    public LiveData<Integer> getCorrectQuestionsByModuleID(int moduleID) {
        return quizRepository.getCorrectQuestionsByModuleID(moduleID);
    }

    public void updateCorrectQuestionsByModuleID(int moduleID,int highscoreNew) {
        quizRepository.updateCorrectQuestionsByModuleID(moduleID,highscoreNew);
    }
}
