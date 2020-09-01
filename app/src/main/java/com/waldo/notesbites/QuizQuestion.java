package com.waldo.notesbites;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "quiz_question_table",foreignKeys = @ForeignKey(entity = Quiz.class,
        parentColumns = "quizID",
    childColumns = "belongingQuizID",
        onDelete = CASCADE))
public class QuizQuestion {
    @PrimaryKey(autoGenerate = true)
    private int quizQuestionID;
    private String question;
    private int priority;
    @ColumnInfo(name = "belongingQuizID")
    private int belongingQuizID;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctOption;

    public QuizQuestion(String question, int priority, int belongingQuizID, String option1, String option2, String option3, String option4, String correctOption){
        this.question = question;
        this.priority = priority;
        this.belongingQuizID = belongingQuizID;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctOption = correctOption;
    }

    public void setQuizQuestionID(int quizQuestionID){
        this.quizQuestionID = quizQuestionID;
    }
    public int getQuizQuestionID(){return quizQuestionID;}
    public String getQuestion(){return question;}
    public int getPriority(){
        return priority;
    }
    public int getBelongingQuizID(){
        return belongingQuizID;
    }
    public String getOption1(){
        return option1;
    }
    public String getOption2(){
        return option2;
    }
    public String getOption3(){
        return option3;
    }
    public String getOption4(){
        return option4;
    }
    public String getCorrectOption(){
        return correctOption;
    }

}
