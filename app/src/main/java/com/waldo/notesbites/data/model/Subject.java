package com.waldo.notesbites.data.model;

import java.util.ArrayList;

import javax.security.auth.SubjectDomainCombiner;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "subject_table")
public class Subject {
    @PrimaryKey(autoGenerate = true)
    private int subjectID;
    private String name;
    private String description;
    private int imageResourceID;
    private String overview;
    private boolean selected;

    public Subject(String name, String description, int imageResourceID, String overview, boolean selected) {
        this.name = name;
        this.description = description;
        this.imageResourceID = imageResourceID;
        this.overview = overview;
        this.selected = selected;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public int getSubjectID() {
        return subjectID;
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

    public String getOverview() {
        return overview;
    }

    public boolean isSelected() {
        return selected;
    }
}
