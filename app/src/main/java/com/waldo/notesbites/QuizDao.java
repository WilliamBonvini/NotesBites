package com.waldo.notesbites;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import dagger.Binds;
import dagger.Provides;

@Dao
public interface QuizDao {


    @Insert
    void insert(Quiz quiz);

    @Update
    void update(Quiz quiz);

    @Delete
    void delete(Quiz quiz);

    @Query("SELECT quizID FROM quiz_table WHERE belongingModuleID = :moduleID")
    int getQuizIDbyModuleID(int moduleID);

    @Query("SELECT quizID FROM quiz_table WHERE belongingModuleID = :moduleID")
    LiveData<Integer> getQuizID_liveData_byModuleID(int moduleID);

    @Query("SELECT * from quiz_question_table WHERE belongingQuizID=:quizID")

    List<QuizQuestion>  getQuizQuestionsListByQuizID(int quizID);
}