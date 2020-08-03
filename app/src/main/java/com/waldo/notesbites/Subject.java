package com.waldo.notesbites;

import java.util.ArrayList;

import javax.security.auth.SubjectDomainCombiner;

public class Subject {
    private String name;
    private String description;
    private int imageResourceID;
    private Module[] modules;

    public Subject(String name, String description, int imageResourceID, Module[] modules) {
        this.name = name;
        this.description = description;
        this.imageResourceID = imageResourceID;
        this.modules = modules;
    }

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
