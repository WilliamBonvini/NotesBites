package com.waldo.notesbites;

import java.util.ArrayList;

import javax.security.auth.SubjectDomainCombiner;

public class Subject {
    private int subjectID;
    private String name;
    private String description;
    private int imageResourceID;
    private Module[] modules;

    public Subject(int subjectID, String name, String description, int imageResourceID, Module[] modules) {
        this.subjectID = subjectID;
        this.name = name;
        this.description = description;
        this.imageResourceID = imageResourceID;
        this.modules = modules;
    }

    public int getSubjectID(){ return subjectID; }

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }


    public int getImageResourceID() {
        return imageResourceID;
    }


    public Module[] getModules() {
        return modules;
    }


    @Override
    public String toString(){
        return new com.google.gson.Gson().toJson(this);
    }
}
