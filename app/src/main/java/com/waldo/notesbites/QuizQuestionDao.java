package com.waldo.notesbites;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuizQuestionDao {

    @Insert
    void insert(QuizQuestion quizQuestion);

    @Update
    void update(QuizQuestion quizQuestion);

    @Delete
    void delete(QuizQuestion quizQuestion);

    @Query("SELECT name FROM module_table WHERE moduleID = :moduleID")
    LiveData<String> getModuleNameByModuleID(int moduleID);

    @Query("SELECT * FROM quiz_question_table WHERE belongingQuizID = :quizID")
    LiveData<List<QuizQuestion>> getQuestionsByQuizID(int quizID);


    @Query("SELECT * FROM quiz_question_table WHERE belongingQuizID=:quizID AND priority=:priority")
    LiveData<QuizQuestion> getCurrentQuizQuestionByQuizIDAndPriority(int quizID, int priority);
}