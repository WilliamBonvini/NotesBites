package com.waldo.notesbites;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "quiz_question_table",foreignKeys = @ForeignKey(entity = Quiz.class,
        parentColumns = "quizID",
    childColumns = "belongingQuizID",
        onDelete = CASCADE))
public class QuizQuestion {
    @PrimaryKey
    private int quizQuestionID;
    private String question;
    private int priority;
    private int belongingQuizID;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctOption;


}
