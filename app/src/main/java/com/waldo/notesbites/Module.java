package com.waldo.notesbites;

public class Module {
    private String title;
    private String description;
    private int imageResourceID;
    private int readableContentID;
    private int videoContentID;
    private int quizID;



    public Module(String title,String description,int imageResourceID) {
        this.title = title;
        this.description = description;
        this.imageResourceID = imageResourceID;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceID() {
        return imageResourceID;
    }

    public int getReadableContentID() {
        return readableContentID;
    }

    public int getVideoContentID() {
        return videoContentID;
    }

    public int getQuizID() {
        return quizID;
    }
}
