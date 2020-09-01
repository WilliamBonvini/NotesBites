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



    // ex quiz question dao
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


    @Query("SELECT quizID AS quizID, 2 AS numberOfQuestions " +
            "FROM quiz_table " +
            "WHERE belongingModuleID = :moduleID")
    LiveData<QuizIDAndQuizQuestionCountTuple> getQuizIDAndNumberOfQuestionsByModuleID(int moduleID);


    /*@Query("SELECT belongingQuizID,COUNT(*) FROM quiz_question_table WHERE belongingQuizID = (SELECT quizID FROM quiz_table WHERE belongingModuleID = :moduleID) GROUP BY belongingQuizID")
    LiveData<QuizIDAndQuizQuestionCountTuple> getQuizIDAndNumberOfQuestionsByModuleID(int moduleID);
*/


    //(SELECT COUNT(*) AS quizQuestionCount FROM quiz_question_table WHERE belongingQuizID = " +
    //                            "(SELECT quizID FROM quiz_table WHERE belongingModuleID = :moduleID))

}