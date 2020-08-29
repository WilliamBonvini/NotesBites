package com.waldo.notesbites;

import java.net.URL;

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
    private String belongingSubject;

    public Module(String name, String description,int priority, String mdContent, String videoURL,String belongingSubject) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.mdContent = mdContent;
        this.videoURL = videoURL;
        this.belongingSubject = belongingSubject;
    }

    public void setModuleID(int moduleID){
        this.moduleID = moduleID;
    }

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

    public String getBelongingSubject(){
        return belongingSubject;
    }
}
