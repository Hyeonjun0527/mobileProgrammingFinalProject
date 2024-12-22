package com.example.final_project.domain;

public class Post {
    private String title;
    private String content;
    private String imagePath;

    public Post(String title, String content, String imagePath) {
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImagePath() {
        return imagePath;
    }
}
