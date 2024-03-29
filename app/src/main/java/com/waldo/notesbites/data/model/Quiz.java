package com.waldo.notesbites.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "quiz_table",foreignKeys = @ForeignKey(entity = Module.class,
        parentColumns = "moduleID",
        childColumns = "belongingModuleID",
        onDelete = CASCADE))
public class Quiz {
    @ColumnInfo(name = "quizID")
    @PrimaryKey(autoGenerate = true)
    private int quizID;
    private int belongingModuleID;
    private int correctQuestions;

    public Quiz(int belongingModuleID, int correctQuestions){
        this.belongingModuleID = belongingModuleID;
        this.correctQuestions = correctQuestions;
    }

    public void setQuizID(int quizID){
        this.quizID = quizID;
    }
    public int getQuizID(){
        return quizID;
    }
    public int getBelongingModuleID(){
        return belongingModuleID;
    }
    public int getCorrectQuestions(){
        return correctQuestions;
    }
    
}
