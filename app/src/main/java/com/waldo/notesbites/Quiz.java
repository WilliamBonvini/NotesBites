package com.waldo.notesbites;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "quiz_table",foreignKeys = @ForeignKey(entity = Module.class,
        parentColumns = "moduleID",
        childColumns = "belongingModuleID",
        onDelete = CASCADE))
public class Quiz {
    @PrimaryKey
    private int quizID;
    private int belongingModuleID;
    private int correctQuestions;
    
}
