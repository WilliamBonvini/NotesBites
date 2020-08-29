package com.waldo.notesbites;

import java.net.URL;
import java.util.Date;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "module_table", foreignKeys = @ForeignKey(entity = Subject.class,
        parentColumns = "subjectID",
        childColumns = "belongingSubjectID",
        onDelete = CASCADE))
public class Module {
    @PrimaryKey(autoGenerate = true)
    private int moduleID;
    private String name;
    private String description;
    private int priority;
    private String mdContent;
    private String videoURL;
    private int belongingSubjectID;
    private Date lastOpened;


    public Module(String name, String description,int priority, String mdContent, String videoURL,int belongingSubjectID) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.mdContent = mdContent;
        this.videoURL = videoURL;
        this.belongingSubjectID = belongingSubjectID;
        this.lastOpened = null;
    }

    public void setModuleID(int moduleID){
        this.moduleID = moduleID;
    }

    public void setLastOpened(Date lastOpened){this.lastOpened = lastOpened;}  // lo vuole per forza, boh :D

    public int getModuleID() {
        return moduleID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority(){
        return priority;
    }

    public String getMdContent() {
        return mdContent;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public int getBelongingSubjectID(){
        return belongingSubjectID;
    }

    public Date getLastOpened(){return lastOpened;}
}
