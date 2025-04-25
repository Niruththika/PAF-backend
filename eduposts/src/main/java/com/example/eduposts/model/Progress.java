package com.example.eduposts.model;

import jakarta.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class Progress {
    private String status;
    private int completed;
    private String notes;
    private Date reminder;

    public Progress() {
        this.status = "Not Started";
        this.completed = 0;
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getReminder() {
        return reminder;
    }

    public void setReminder(Date reminder) {
        this.reminder = reminder;
    }
}