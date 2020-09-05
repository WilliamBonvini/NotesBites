package com.waldo.notesbites.data.utils;

import androidx.room.ColumnInfo;

public class QuizIDAndQuizQuestionCountTuple {
    @ColumnInfo(name = "quizID")
    public Integer quizID;
    @ColumnInfo(name = "numberOfQuestions")
    public Integer numberOfQuestions;

    public QuizIDAndQuizQuestionCountTuple(Integer quizID, Integer numberOfQuestions) {
        this.quizID = quizID;
        this.numberOfQuestions = numberOfQuestions;
    }


    public Integer getQuizID() {
        return quizID;
    }

    public void setQuizID(Integer quizID) {
        this.quizID = quizID;
    }

    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(Integer numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }
}
