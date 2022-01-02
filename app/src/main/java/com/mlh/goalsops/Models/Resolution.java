package com.mlh.goalsops.Models;

public class Resolution {
    private String key;
    private String title;
    private String description;
    private String deadline;
    private long timeStamp;
    private boolean complete;

    public Resolution() {
    }

    public Resolution(String title, String description, String deadline, long timeStamp) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.timeStamp = timeStamp;
        this.complete = false;
    }

    public Resolution(String key, String title, String description, String deadline, long timeStamp) {
        this.key = key;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.timeStamp = timeStamp;
        this.complete = false;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
