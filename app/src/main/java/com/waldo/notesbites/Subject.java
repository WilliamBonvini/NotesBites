package com.waldo.notesbites;

import javax.security.auth.SubjectDomainCombiner;

public class Subject {
    private String name;
    private String description;
    private int imageResourceID;


    public static  final Subject[] subjects = {
            new Subject("Artificial Intelligence","Amigoni is kind of a stick in the butt",R.drawable.ai),
            new Subject("Internet of Things","I've no idea how to describe it",R.drawable.iot),
            new Subject("Artificial Neural Networks","Matteucci is fun",R.drawable.ann2dl)

    }

    public Subject(String name, String description, int imageResourceID) {
        this.name = name;
        this.description = description;
        this.imageResourceID = imageResourceID;
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

    @Override
    public String toString() {
        return this.name;
    }
}
