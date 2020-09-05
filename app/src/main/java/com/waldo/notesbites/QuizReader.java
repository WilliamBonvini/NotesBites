package com.waldo.notesbites;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class QuizReader {
    private String questionContent;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correct;
    private QuizDao quizDao;
    private int moduleID;
    private int priority;
    private int quizID;
    public QuizReader(QuizDao quizDao,int moduleID){
        this.quizDao = quizDao;
        this.moduleID = moduleID;
        quizDao.insert(new Quiz(moduleID, 0));
        quizID = quizDao.getQuizIDbyModuleID(moduleID);
    }

    public void setQuestionConfig(int priority,String questionContent){
        this.priority = priority;
        this.questionContent = questionContent;
    }

    public void readString(String string){
        String[] arr = string.split("\n");
        question = arr[0];
        option1  = arr[1];
        option2  = arr[2];
        option3  = arr[3];
        option4  = arr[4];
        correct = arr[Integer.parseInt(arr[5])];
    }

    public void insertInDB(){

        quizDao.insert(new QuizQuestion(question, priority, quizID,  option1, option2, option3, option4,correct));

    }



    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }



    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuizContent() {
        return questionContent;
    }

}
