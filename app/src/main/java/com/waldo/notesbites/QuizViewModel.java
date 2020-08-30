package com.waldo.notesbites;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class QuizViewModel extends AndroidViewModel {
    private QuizRepository quizRepository;

    public QuizViewModel(@NonNull Application application) {
        super(application);
        quizRepository = new QuizRepository(application);
    }

    public LiveData<List<QuizQuestion>> getQuestionsByQuizID(int quizID) {
        return quizRepository.getQuestionsByQuizID(quizID);
    }
}
