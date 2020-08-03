package com.waldo.notesbites;

public class Module {
    private String title;
    private String content;

    public Module(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return title;
    }
}
