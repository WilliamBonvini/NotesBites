package com.waldo.notesbites;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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

}