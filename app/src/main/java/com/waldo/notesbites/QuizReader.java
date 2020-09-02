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
    private File quizFile;

    public QuizReader()  {

    }

    public void setQuiz(){
        ClassLoader cl = getClass().getClassLoader();
        assert cl != null;
        this.quizFile = new File(cl.getResource("C:\\Users\\Willi\\Desktop\\GitHub\\NotesBites\\app\\src\\main\\assets\\aaams\\quiz.txt").getFile());
    }

    public void populateDB(){

        BufferedReader reader = null;
        try {

            InputStream targetStream = new FileInputStream(quizFile);
            reader = new BufferedReader(
                    new InputStreamReader(targetStream));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                Log.w("reader",mLine);
            }
        } catch (
                IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }


    }






}
