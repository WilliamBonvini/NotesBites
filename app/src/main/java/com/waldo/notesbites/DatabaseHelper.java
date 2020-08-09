package com.waldo.notesbites;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.Console;
import java.util.logging.ConsoleHandler;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "notesbites"; // the name of our database
    private static final int DB_VERSION = 2; // the version of the database

    DatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION); // factory is set to null because it is an advanced feature we could cover later in the project
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        updateMyDatabase(db,0,DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        updateMyDatabase(db,oldVersion,newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
        if(oldVersion<1){
            db.execSQL("CREATE TABLE SUBJECT("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "NAME TEXT,"
                    + "DESCRIPTION TEXT,"
                    + "IMAGE_RESOURCE_ID INTEGER,"
                    + "SELECTED NUMERIC);");
            db.execSQL("CREATE TABLE MODULE("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "NAME TEXT,"
                    + "DESCRIPTION TEXT,"
                    + "IMAGE_RESOURCE_ID INTEGER,"
                    + "BELONGING_SUBJECT INTEGER,"
                    + "RANK INTEGER,"
                    + "READABLE_CONTENT TEXT);"
            );
            // insert data for AI
            insertSubject(db,"AI","Amigoni is so funny!!!",R.drawable.ai,0);
            insertModule(db,"Introduction to AI - directly loaded from db","the most fun module ever!!",R.drawable.intro,"AI",1,"blablabla, this is the content");
            insertModule(db,"Peppa pig likes AI","didn't you know that???",R.drawable.m1,"AI",2,"blablabla, this is the content");

            // insert data for IOT
            insertSubject(db,"IOT","IOT is sick broo!!!",R.drawable.iot,0);
            insertModule(db,"Introduction to IOT","well, you gotta start from somewhere!!",R.drawable.intro,"IOT",1,"stuff, this is the readable content of introduction to IOT!");

            insertSubject(db,"ANN2DL","Matteucci used to have long hair!!!",R.drawable.ann2dl,0);
            insertModule(db,"Introduction to ANN2DL, yeeeey","you won't understand shit! no worries!!!",R.drawable.intro,"ANN2DL",1,"dabudidabuda!!!!");

        }
        if(oldVersion==2){
            //code to be added if one day we'll change the schema of the db
            /*
            Example:
            db.execSQL("ALTER TABLE SUBJECT ADD COLUMN FAVORITE NUMERIC;")
             */
        }
    }

    public static void insertSubject(SQLiteDatabase db,
                                     String name,
                                     String description,
                                     int resourceID,
                                     int selected){
        // insert data using the insert() method
        // usually you should create one ContentValue object for each row of data you want to insert
        ContentValues subjectValues = new ContentValues();
        subjectValues.put("NAME",name);
        subjectValues.put("DESCRIPTION",description);
        subjectValues.put("IMAGE_RESOURCE_ID",resourceID);
        subjectValues.put("SELECTED",selected);
        db.insert("SUBJECT",null,subjectValues);

    }
    public static void insertModule(SQLiteDatabase db,
                                    String name,
                                    String description,
                                    int resourceID,
                                    String belongingSubject,
                                    int rank,
                                    String readableContent){
        // insert data using the insert() method
        // usually you should create one ContentValue object for each row of data you want to insert
        ContentValues moduleValues = new ContentValues();
        moduleValues.put("NAME",name);
        moduleValues.put("DESCRIPTION",description);
        moduleValues.put("IMAGE_RESOURCE_ID",resourceID);

        // create a cursor that will point only to the tuple containing the wanted subject
        Cursor cursor = db.query("SUBJECT",
                new String[]{"_id"},
                "NAME = ?",
                new String[]{belongingSubject},
                null,null,null);
        // retrieve subjectID
        if(cursor.moveToFirst()) {
            int belongingSubjectID = cursor.getInt(0);
            cursor.close();
            System.out.println("jecco:"+ belongingSubject);

            moduleValues.put("BELONGING_SUBJECT",belongingSubjectID);

            moduleValues.put("RANK",rank);
            moduleValues.put("READABLE_CONTENT",readableContent);

            // insert tuple in db
            db.insert("MODULE",null,moduleValues);
        }



    }





}
